package ie.ncirl.diaproject.dataprocess

import ie.ncirl.diaproject.dataprocess.measurement.PingMeasurement
import org.apache.spark.SparkConf
import org.apache.spark.sql.catalyst.ScalaReflection
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, SparkSession}

object DIASparkApp {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("DIASparkApp")
      .set("spark.sql.shuffle.partitions", "2")

    val spark = SparkSession
      .builder
      .config(conf)
      .getOrCreate

    val df = spark
      .read
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "ping")
      .option("startingOffsets", "earliest")
      .load()
      .selectExpr("CAST(value AS STRING) AS jsonString")

    df.show(false)

    import spark.implicits._

    val measurementSchema = ScalaReflection.schemaFor[PingMeasurement].dataType.asInstanceOf[StructType]

    val df2 = df.select(from_json($"jsonString", schema=measurementSchema) as "measurement")

    df2.show(false)

    val df3 = flattenDataframe(df2)

    df3.show(false)

    df3.write.csv("output.csv")

    spark.close
  }

  def flattenDataframe(df: DataFrame): DataFrame = {
    val fields = df.schema.fields
    val fieldNames = fields.map(x => x.name)

    for (i <- 0 to fields.length - 1) {
      val field = fields(i)
      val fieldType = field.dataType
      val fieldName = field.name
      fieldType match {
        case _: ArrayType =>
          val fieldNamesExcludingArray = fieldNames.filter(_ != fieldName)
          val fieldNamesAndExplode = fieldNamesExcludingArray ++ Array(s"explode_outer($fieldName) as $fieldName")
          val explodedDf = df.selectExpr(fieldNamesAndExplode: _*)
          return flattenDataframe(explodedDf)
        case structType: StructType =>
          val childFieldNames = structType.fieldNames.map(childName => fieldName + "." + childName)
          val newFieldNames = fieldNames.filter(_ != fieldName) ++ childFieldNames
          val renamedCols = newFieldNames.map(x => (col(x.toString()).as(x.toString().replace(".", "_"))))
          val explodeDf = df.select(renamedCols: _*)
          return flattenDataframe(explodeDf)
        case _ => // no nesting here
      }
    }

    df
  }
}
