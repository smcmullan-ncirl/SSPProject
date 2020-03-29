// https://spark.apache.org/docs/latest/structured-streaming-kafka-integration.html
// https://spark.apache.org/docs/latest/streaming-kafka-0-10-integration.html
// https://docs.databricks.com/spark/latest/dataframes-datasets/complex-nested-data.html

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._

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

    val taskParametersSchema = StructType(Seq(
    ))

    val taskSchema = StructType(Seq(
      StructField("count", StringType, true),
      StructField("filter", StringType, true),
      StructField("parameters", taskParametersSchema, true),
      StructField("created", StringType, true),
      StructField("start_time", StringType, true),
      StructField("interval_sec", StringType, true),
      StructField("priority", StringType, true),
      StructField("tag", StringType, true),
      StructField("end_time", StringType, true),
      StructField("type", StringType, true),
      StructField("id", StringType, true)
    ))

    val parametersSchema = StructType(Seq(
    ))

    val devicePropertiesSchema = StructType(Seq(
    ))

    val valuesSchema = StructType(Seq(
    ))

    val measurementSchema = StructType(Seq(
      StructField("task", taskSchema, true),
      StructField("parameters", parametersSchema, true),
      StructField("success", StringType, true),
      StructField("timestamp", StringType, true),
      StructField("device_properties", devicePropertiesSchema, true),
      StructField("values", valuesSchema, true),
      StructField("type", StringType, true),
      StructField("id", StringType, true)
    ))

    import spark.implicits._

    val df2 = df.selectExpr("CAST(value AS STRING) AS jsonString")
      .select(from_json($"jsonString", schema=measurementSchema) as "measurement")

    flattenDataframe(df2).show(false)

    spark.close
  }

  def flattenDataframe(df: DataFrame): DataFrame = {

    val fields = df.schema.fields
    val fieldNames = fields.map(x => x.name)
    val length = fields.length

    for (i <- 0 to fields.length - 1) {
      val field = fields(i)
      val fieldtype = field.dataType
      val fieldName = field.name
      fieldtype match {
        case arrayType: ArrayType =>
          val fieldNamesExcludingArray = fieldNames.filter(_ != fieldName)
          val fieldNamesAndExplode = fieldNamesExcludingArray ++ Array(s"explode_outer($fieldName) as $fieldName")
          // val fieldNamesToSelect = (fieldNamesExcludingArray ++ Array(s"$fieldName.*"))
          val explodedDf = df.selectExpr(fieldNamesAndExplode: _*)
          return flattenDataframe(explodedDf)
        case structType: StructType =>
          val childFieldnames = structType.fieldNames.map(childname => fieldName + "." + childname)
          val newfieldNames = fieldNames.filter(_ != fieldName) ++ childFieldnames
          val renamedcols = newfieldNames.map(x => (col(x.toString()).as(x.toString().replace(".", "_"))))
          val explodedf = df.select(renamedcols: _*)
          return flattenDataframe(explodedf)
        case _ =>
      }
    }

    df
  }

}
