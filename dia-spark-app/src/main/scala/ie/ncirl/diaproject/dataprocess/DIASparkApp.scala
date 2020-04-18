package ie.ncirl.diaproject.dataprocess

import java.util.{Objects, Properties}

import ie.ncirl.diaproject.dataprocess.measurement.Measurement
import ie.ncirl.diaproject.dataprocess.measurement.http.HttpMeasurement
import ie.ncirl.diaproject.dataprocess.measurement.ping.PingMeasurement
import ie.ncirl.diaproject.dataprocess.measurement.tcpthroughput.TcpThroughputMeasurement
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.catalyst.ScalaReflection
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.slf4j.LoggerFactory

class DIASparkApp {
}

object DIASparkApp {
  private val logger = LoggerFactory.getLogger(classOf[DIASparkApp])

  def main(args: Array[String]): Unit = {
    // Read in the config.properties file
    val properties: Properties = new Properties()
    properties.load(Objects.requireNonNull(getClass.getResourceAsStream("/config.properties")))

    val kafkaServer = properties.getProperty("kafka.server")
    val kafkaTopics = properties.getProperty("kafka.topics")

    // It is worth changing this property to the number of CPUs you have available across your cluster
    val sparkPartitions = properties.getProperty("spark.partitions")

    // Set the Spark cluster configuration
    val conf: SparkConf = new SparkConf()
      .setAppName("DIASparkApp")
      .set("spark.sql.shuffle.partitions", sparkPartitions)
      .set("spark.debug.maxToStringFields", "100")

    val spark = SparkSession
      .builder
      .config(conf)
      .getOrCreate

    // Produce a DataFrame with all the records from all the subscribed to Kafka topics
    // The DataFrame should have a single column of type String called jsonString
    val rawDF = spark
      .read
      .format("kafka")
      .option("kafka.bootstrap.servers", kafkaServer)
      .option("subscribe", kafkaTopics)
      .option("startingOffsets", "earliest")
      .load()
      .selectExpr("CAST(value AS STRING) AS jsonString")

    if (!rawDF.isEmpty) {
      // Create a generic Measurement schema StructType
      val measurementSchema = ScalaReflection.schemaFor[Measurement].dataType.asInstanceOf[StructType]

      // Create a new DataFrame containing the JSON String in one column and the parsed JSON as a StructType column
      // called measurement in the other
      val measurementDF = rawDF.select(col("jsonString"),
        from_json(col("jsonString"), schema = measurementSchema) as "measurement")

      if (!measurementDF.isEmpty) {
        // Create a new DataFrame which flattens the measurement StructType to individual columns
        // The jsonString column should still be there
        val explodedMeasurementDF = JSONUtils.flattenDataFrame(measurementDF)

        // Extract the distinct measurement types in the overall data set and iterate through them
        val types = explodedMeasurementDF.select("measurement_type").distinct.collectAsList()

        val iter = types.iterator()

        while(iter.hasNext) {
          val measurementType = iter.next().getString(0)
          logger.info(s"Processing $measurementType measurement type")

          // Generate a flattened dataframe with all columns for the specific measurement type
          val explodedTypedMeasurementDF: DataFrame = genTypedMeasurementDF(explodedMeasurementDF, measurementType)

          genMeasurementAggs(explodedTypedMeasurementDF)

          genMeasurementTypeAggs(measurementType)

          // DEBUG: Write out the data frame to a measurement specific TSV file
//          explodedTypedMeasurementDF
//            .repartition(1)
//            .write
//            .format("csv")
//            .mode("overwrite")
//            .option("sep", "\t")
//            .option("header", "true")
//            .save(s"${measurementType}_output.tsv")
        }
      }
    }

    spark.close
  }

  private def genTypedMeasurementDF(explodedMeasurementDF: DataFrame, measurementType: String) = {
    // Get the detailed Schema for the specific measurement type
    val typedMeasurementSchema: StructType = createMeasurementSchema(measurementType)

    // Create a DataFrame with just the specific measurement type as rows and a single measurement column
    // of type StructType containing the measurement details
    val typedMeasurementDF = explodedMeasurementDF
      .filter(col("measurement_type") === measurementType)
      .select(from_json(col("jsonString"), schema = typedMeasurementSchema) as "measurement")

    // Create a new DataFrame which flattens the measurement StructType to individual columns
    val explodedTypedMeasurementDF = JSONUtils.flattenDataFrame(typedMeasurementDF)
    explodedTypedMeasurementDF
  }

  private def createMeasurementSchema(measurementType: String): StructType = {
    var measurementSchema: StructType = null

    measurementType match {
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

  private def genMeasurementAggs(explodedTypedMeasurementDF: DataFrame): Unit = {
    // Start doing some interesting aggregations here - these are generic to all measurement types
    explodedTypedMeasurementDF
      .groupBy(col("measurement_device_properties_carrier"),
        col("measurement_device_properties_network_type"))
      .count()
      .orderBy(col("measurement_device_properties_carrier"),
        col("measurement_device_properties_network_type"))
      .withColumnRenamed("measurement_device_properties_carrier", "Carrier")
      .withColumnRenamed("measurement_device_properties_network_type", "Network Type")
      .show(false)

    explodedTypedMeasurementDF
      .groupBy(col("measurement_device_properties_device_info_manufacturer"),
        col("measurement_device_properties_device_info_model"))
      .count()
      .orderBy(col("measurement_device_properties_device_info_manufacturer"),
        col("measurement_device_properties_device_info_model"))
      .withColumnRenamed("measurement_device_properties_device_info_manufacturer", "Manufacturer")
      .withColumnRenamed("measurement_device_properties_device_info_model", "Model")
      .show(false)

    explodedTypedMeasurementDF
      .agg(max("measurement_device_properties_battery_level"))
      .withColumnRenamed("max(measurement_device_properties_battery_level)", "Max Battery Level")
      .show(false)

    explodedTypedMeasurementDF
      .agg(min("measurement_device_properties_battery_level"))
      .withColumnRenamed("min(measurement_device_properties_battery_level)", "Min Battery Level")
      .show(false)

    explodedTypedMeasurementDF
      .agg(avg("measurement_device_properties_battery_level"))
      .withColumnRenamed("avg(measurement_device_properties_battery_level)", "Avg Battery Level")
      .show(false)

    explodedTypedMeasurementDF
      .agg(max("measurement_device_properties_rssi"))
      .withColumnRenamed("max(measurement_device_properties_rssi)", "Max RSSI")
      .show(false)

    explodedTypedMeasurementDF
      .agg(min("measurement_device_properties_rssi"))
      .withColumnRenamed("min(measurement_device_properties_rssi)", "Min RSSI")
      .show(false)

    explodedTypedMeasurementDF
      .agg(avg("measurement_device_properties_rssi"))
      .withColumnRenamed("avg(measurement_device_properties_rssi)", "Avg RSSI")
      .show(false)
  }

  private def genMeasurementTypeAggs(measurementType: String): Unit = {
    // TBD: Do some more interesing aggregations on a per measurement type basis
    measurementType match {
      case "ping" =>
      case "traceroute" =>
      case "http" =>
      case "dns_lookup" =>
      case "udp_burst" =>
      case "tcpthroughput" =>
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
  }

}
