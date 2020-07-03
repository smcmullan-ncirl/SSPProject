package ie.ncirl.sspproject.dataprocess

import java.util.{Objects, Properties}

import org.apache.spark.SparkConf
import org.apache.spark.sql.functions.{col, window}
import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.types.{DoubleType, TimestampType}
import org.apache.spark.sql.{Column, SparkSession}
import org.slf4j.LoggerFactory

class SSPSparkApp {
}

object SSPSparkApp {
  private val LOGGER = LoggerFactory.getLogger(classOf[SSPSparkApp])

  def main(args: Array[String]): Unit = {
    // Read in the config.properties file
    val properties: Properties = new Properties()
    properties.load(Objects.requireNonNull(getClass.getResourceAsStream("/config.properties")))

    val kafkaServer = properties.getProperty("kafka.server")
    val kafkaTopics = properties.getProperty("kafka.topics")
    val kafkaTopicStartingOffset = properties.getProperty("kafka.topic.starting.offset")

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

    // DEBUG mode
    // This is for running the Spark application within the IDE for debug purposes
    // You also need to remove the "provided" scope from the spark-core and spark-sql libraries in the Maven pom.xml
    val runIDE = properties.getProperty("run.ide").toBoolean

    if (runIDE) {
      conf.setMaster("local[*]")
    }

    val spark = SparkSession
      .builder
      .config(conf)
      .getOrCreate

    val streamDF = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", kafkaServer)
      .option("subscribe", kafkaTopics)
      .option("startingOffsets", kafkaTopicStartingOffset)
      .load()
      .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .selectExpr(
        "split(value,',')[0] as cell_id",
        "split(value,',')[1] as timestamp",
        "split(value,',')[2] as calls_in",
        "split(value,',')[3] as calls_out",
        "split(value,',')[4] as sms_in",
        "split(value,',')[5] as sms_out",
        "split(value,',')[6] as internet_activity"
      )
      .withColumn("timestamp", col("timestamp").cast(TimestampType))
      .withColumn("calls_in", col("calls_in").cast(DoubleType))
      .withColumn("calls_out", col("calls_out").cast(DoubleType))
      .withColumn("sms_in", col("sms_in").cast(DoubleType))
      .withColumn("sms_out", col("sms_out").cast(DoubleType))
      .withColumn("internet_activity", col("internet_activity").cast(DoubleType))

    val windowedDF = streamDF
      .groupBy(window(new Column("timestamp"), "5 seconds"))
      .sum("calls_in")

    val query = windowedDF
      .writeStream
      .outputMode(OutputMode.Complete)
      .format("console")
      .option("checkpointLocation", "/tmp")
      .start

    query.awaitTermination

    spark.close
  }

}
