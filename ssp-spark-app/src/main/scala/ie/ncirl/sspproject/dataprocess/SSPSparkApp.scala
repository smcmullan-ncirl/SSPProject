package ie.ncirl.sspproject.dataprocess

import java.sql.Timestamp
import java.util.{Objects, Properties}

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.catalyst.ScalaReflection
import org.apache.spark.sql.functions.{col, from_json, sum}
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import org.apache.spark.sql.types.StructType
import org.slf4j.LoggerFactory

class SSPSparkApp {
}

object SSPSparkApp {
  private val LOGGER = LoggerFactory.getLogger(classOf[SSPSparkApp])

  val Hourly = "hourly_"
  val Daily = "daily_"
  val Weekly = "weekly_"

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

    // It is worth changing this property to the number of CPUs you have available across your cluster
    // Make sure it reflects the SPARK_WORKER_CORES environment setting in docker-compose.yml
    val sparkPartitions = properties.getProperty("spark.partitions")

    import scala.collection.JavaConverters._
    val props = properties.asScala
    props.foreach((e: (String, String)) => LOGGER.info(s"${e._1} : ${e._2}"))

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

    def genAggs(streamDF: DataFrame, interval: String) = {
      streamDF
        .withWatermark(interval + "timestamp", "5 minutes")
        .groupBy(interval + "timestamp", "area_code" )
        .agg(
          sum("calls_in"),
          sum("calls_out"),
          sum("sms_in"),
          sum("sms_out")
        )
    }

    def writeAggSink(aggStream: DataFrame, interval: String) = {
      aggStream
        .writeStream
        .trigger(Trigger.ProcessingTime(timeWindowSecs + " seconds"))
        .outputMode(OutputMode.Append)
        .format("org.elasticsearch.spark.sql")
        .option("checkpointLocation", "/tmp/" + interval + "checkpoint")
        .start(interval + esIndex)

      if (LOGGER.isDebugEnabled()) {
        aggStream
          .orderBy(interval + "timestamp", "area_code")
          .writeStream
          .trigger(Trigger.ProcessingTime(timeWindowSecs + " seconds"))
          .outputMode(OutputMode.Complete)
          .format("console")
          .start
      }
    }

    val spark = SparkSession
      .builder
      .config(conf)
      .getOrCreate

    val telecomRecordSchema = ScalaReflection.schemaFor[TelecomRecord].dataType.asInstanceOf[StructType]

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

    streamDF.printSchema

    // Calculate aggregates
    val aggDF1 = genAggs(streamDF, Hourly)
    val aggDF2 = genAggs(streamDF, Daily)
    val aggDF3 = genAggs(streamDF, Weekly)

    // Write streams
    writeAggSink(aggDF1, Hourly)
    writeAggSink(aggDF2, Daily)
    writeAggSink(aggDF3, Weekly)

    spark.streams.awaitAnyTermination()

    spark.close
  }
}

case class TelecomRecord
(
  cell_id: String,
  timestamp: Long,
  area_code: Int,
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