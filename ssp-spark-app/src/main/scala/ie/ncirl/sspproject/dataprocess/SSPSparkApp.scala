/*
  Stephen McMullan x19139497@student.ncirl.ie

  SSP Spark Data Processing Application

  Processes stream from Kafka, aggregates and persists aggregate records to Elasticsearch
*/

package ie.ncirl.sspproject.dataprocess

import java.sql.Timestamp
import java.util.{Objects, Properties}

import org.apache.spark.SparkConf
import org.apache.spark.sql.catalyst.ScalaReflection
import org.apache.spark.sql.functions.{col, from_json, sum}
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.slf4j.LoggerFactory

class SSPSparkApp {
}

object SSPSparkApp {
  private val LOGGER = LoggerFactory.getLogger(classOf[SSPSparkApp])

  val Hourly = "hourly"
  val Daily = "daily"
  val Weekly = "weekly"

  def main(args: Array[String]): Unit = {
    // Read in the config.properties file
    val properties: Properties = new Properties()
    properties.load(Objects.requireNonNull(getClass.getResourceAsStream("/config.properties")))

    val kafkaServer = properties.getProperty("kafka.server")
    val kafkaTopics = properties.getProperty("kafka.topics")
    val kafkaTopicStartingOffset = properties.getProperty("kafka.topic.starting.offset")
    val kafkaMaxOffsetsPerTrigger = properties.getProperty("kafka.max.offsets.per.trigger")

    val esServer = properties.getProperty("es.server")
    val esPort = properties.getProperty("es.port")
    val esIndex = properties.getProperty("es.index")

    val timeWindowSecs = properties.getProperty("time.window.secs")

    // Aggregation time enablement
    val enableHourlyAgg = properties.getProperty("enable.hourly.agg").toBoolean
    val enableDailyAgg = properties.getProperty("enable.daily.agg").toBoolean
    val enableWeeklyAgg = properties.getProperty("enable.weekly.agg").toBoolean

    import scala.collection.JavaConverters._
    val props = properties.asScala
    props.foreach((e: (String, String)) => LOGGER.info(s"${e._1} : ${e._2}"))

    // The number of CPUs you have available across your cluster
    // Make sure it reflects the SPARK_WORKER_CORES environment setting in docker-compose.yml
    val sparkPartitions = System.getenv("SPARK_WORKER_CORES")
    LOGGER.info("Setting parallelism to {}", sparkPartitions)

    // Set the Spark cluster configuration
    val conf: SparkConf = new SparkConf()
      .setAppName("SSPSparkApp")
      .set("spark.sql.shuffle.partitions", sparkPartitions)
      .set("spark.es.nodes", esServer)
      .set("spark.es.port", esPort)

    // DEBUG mode
    // This is for running the Spark application within the IDE for debug purposes
    // You also need to remove the "provided" scope from the spark-core and spark-sql libraries in the Maven pom.xml
    val runIDE = properties.getProperty("run.ide").toBoolean

    if (runIDE) {
      conf.setMaster("local[*]")
    }


    // Aggregate the TelecomRecords by an aggregate key based on the hourly, daily or weekly timestamp
    // Reduce to a TelecomAgg record with the aggregation keys and the total counts
    def genAggs(streamDF: DataFrame, interval: String) = {
      streamDF
        .groupBy(interval + "_timestamp", "area_code" )
        .agg(
          sum("calls_in").alias("total_calls_in"),
          sum("calls_out").alias("total_calls_out"),
          sum("sms_in").alias("total_sms_in"),
          sum("sms_out").alias("total_sms_out")
        )
    }

    // Write the aggregate records to Elasticsearch using a customer Foreach sink
    // Unfortunately the standard Elasticsearch sink only supports Append mode with a Watermark which
    // doesn't work well with historical data
    def writeAggSink(aggStream: DataFrame, interval: String) = {
      aggStream
        .writeStream
        .trigger(Trigger.ProcessingTime(timeWindowSecs + " seconds"))
        .outputMode(OutputMode.Append)
        .format("org.elasticsearch.spark.sql")
        .option("checkpointLocation", "/tmp/checkpoint" + interval)
        .start(interval + esIndex)

        aggStream
          .orderBy(col(interval + "_timestamp").desc, col("area_code"))
          .writeStream
          .trigger(Trigger.ProcessingTime(timeWindowSecs + " seconds"))
          .outputMode(OutputMode.Complete)
          .format("console")
          .start
    }

    // Initialise the Spark Stream Environment
    val spark = SparkSession
      .builder
      .config(conf)
      .getOrCreate

    val telecomRecordSchema = ScalaReflection.schemaFor[TelecomRecord].dataType.asInstanceOf[StructType]

    // Add the Kafka source
    val streamDF = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", kafkaServer)
      .option("subscribe", kafkaTopics)
      .option("startingOffsets", kafkaTopicStartingOffset)
      .option("maxOffsetsPerTrigger", kafkaMaxOffsetsPerTrigger)
      .option("failOnDataLoss", "false")
      .load()
      .select(from_json(col("value").cast("string"), telecomRecordSchema).alias("telecom_record"))
      .select("telecom_record.*")

    // In TRACE mode also write out the aggregate records to the console
    if (LOGGER.isTraceEnabled()) {
      streamDF.printSchema

      streamDF
        .writeStream
        .format("console")
        .start
    }

    // Produce separate stream for Hourly, Daily and Weekly aggregates

    // Map and Reduce the TelecomRecord data stream to TelecomAgg aggregate records
    // then write streams To Elasticsearch and Console output
    if (enableHourlyAgg) {
      val aggDF1 = genAggs(streamDF, Hourly)
      writeAggSink(aggDF1, Hourly)
    }

    if (enableDailyAgg) {
      val aggDF2 = genAggs(streamDF, Daily)
      writeAggSink(aggDF2, Daily)
    }

    if (enableWeeklyAgg) {
      val aggDF3 = genAggs(streamDF, Weekly)
      writeAggSink(aggDF3, Weekly)
    }

    spark.streams.awaitAnyTermination

    spark.close
  }
}

// Schema class for Telecom Records (10 min aggregates)
case class TelecomRecord
(
  cell_id: String,
  timestamp: Long,
  area_code: String,
  calls_in: Double,
  calls_out: Double,
  sms_in: Double,
  sms_out: Double,
  internet_activity: Double,
  timestamp_str: String,
  hourly_timestamp: Timestamp,
  hourly_timestamp_str: String,
  daily_timestamp: Timestamp,
  daily_timestamp_str: String,
  weekly_timestamp: Timestamp,
  weekly_timestamp_str: String
)

// Schema class for Telecom Agg Records (Hourly/Daily/Weekly aggregates)
case class TelecomAgg
(
  timestamp: String,
  area_code: String,
  total_calls_in: Double,
  total_calls_out: Double,
  total_sms_in: Double,
  total_sms_out: Double
)
