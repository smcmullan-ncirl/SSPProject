package ie.ncirl.sspproject.dataprocess

import java.util.{Objects, Properties}

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
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
    val kafkaTopicEndingOffset = properties.getProperty("kafka.topic.ending.offset")

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

    // Produce a DataFrame with all the records from all the subscribed to Kafka topics
    // The DataFrame should have a single column of type String called jsonString
    val rawDF = spark
      .read
      .format("kafka")
      .option("kafka.bootstrap.servers", kafkaServer)
      .option("subscribe", kafkaTopics)
      .option("startingOffsets", kafkaTopicStartingOffset)
      .option("endingOffsets", kafkaTopicEndingOffset)
      .load()
      .selectExpr("CAST(value AS STRING) AS csvString")

    if (!rawDF.isEmpty) {
      LOGGER.info("Row count: {}", rawDF.count())
    }

    spark.close
  }

}
