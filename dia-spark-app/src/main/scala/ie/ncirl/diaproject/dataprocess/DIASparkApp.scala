package ie.ncirl.diaproject.dataprocess

import java.util.{Objects, Properties}

import ie.ncirl.diaproject.dataprocess.measurement.Measurement
import ie.ncirl.diaproject.dataprocess.measurement.http.HttpMeasurement
import ie.ncirl.diaproject.dataprocess.measurement.ping.PingMeasurement
import ie.ncirl.diaproject.dataprocess.measurement.tcpthroughput.TcpThroughputMeasurement
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.catalyst.ScalaReflection
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.slf4j.LoggerFactory

class DIASparkApp {
}

object DIASparkApp {
  private val logger = LoggerFactory.getLogger(classOf[DIASparkApp])

  def main(args: Array[String]): Unit = {
    val properties: Properties = new Properties()
    properties.load(Objects.requireNonNull(getClass.getResourceAsStream("/config.properties")))

    val kafkaServer = properties.getProperty("kafka.server")
    val kafkaTopics = properties.getProperty("kafka.topics")

    val conf: SparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("DIASparkApp")
      .set("spark.sql.shuffle.partitions", "2")

    val spark = SparkSession
      .builder
      .config(conf)
      .getOrCreate

    val rawDF = spark
      .read
      .format("kafka")
      .option("kafka.bootstrap.servers", kafkaServer)
      .option("subscribe", kafkaTopics)
      .option("startingOffsets", "earliest")
      .load()
      .selectExpr("CAST(value AS STRING) AS jsonString")

    rawDF.show(false)

    if (!rawDF.isEmpty) {
      import spark.implicits._

      val measurementSchema = ScalaReflection.schemaFor[Measurement].dataType.asInstanceOf[StructType]

      val jsonDF = rawDF.select(from_json($"jsonString", schema = measurementSchema) as "measurement")

      jsonDF.show(false)

      if (!jsonDF.isEmpty) {
        val typesDF = jsonDF.select("type").distinct

        typesDF.foreach {
          row =>
            row.toSeq.foreach {
              col => {
                logger.info(s"Processing $col")

                val measurementSchema: StructType = createMeasurementSchema(col)

                val measurementDF = rawDF.select(from_json($"jsonString", schema = measurementSchema) as "measurement")

                val explodedMeasurementDF = JSONUtils.flattenDataFrame(measurementDF)

                explodedMeasurementDF.show(false)

                explodedMeasurementDF.write.csv(s"${col}_output.csv")
              }
            }
        }
      }
    }

    spark.close
  }

  def createMeasurementSchema(col: Any): StructType = {
    var measurementSchema: StructType = null

    col match {
      case "ping" => measurementSchema =
        ScalaReflection.schemaFor[PingMeasurement].dataType.asInstanceOf[StructType]
      case "traceroute" =>
      case "http" => measurementSchema =
        ScalaReflection.schemaFor[HttpMeasurement].dataType.asInstanceOf[StructType]
      case "dns_lookup" =>
      case "udp_burst" =>
      case "tcpthroughput" => measurementSchema =
        ScalaReflection.schemaFor[TcpThroughputMeasurement].dataType.asInstanceOf[StructType]
      case "context" =>
      case "myspeedtest_ping" =>
      case "myspeedtestdns_lookup" =>
      case "device_info" =>
      case "network_info" =>
      case "battery_info" =>
      case "ping_test" =>
      case "sim_info" =>
      case "state_info" =>
      case "usage_info" =>
      case "rrc" =>
      case "PageLoadTime" =>
      case "pageloadtime" =>
      case "video" =>
      case "sequential" =>
      case "quic-http" =>
      case "cronet-http" =>
      case "multipath_latency" =>
      case "multipath_http" =>
      case _ =>
    }

    measurementSchema
  }

}
