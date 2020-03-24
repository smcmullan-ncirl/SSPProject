// https://spark.apache.org/docs/latest/structured-streaming-kafka-integration.html
// https://spark.apache.org/docs/latest/streaming-kafka-0-10-integration.html

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.OutputMode

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
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "ping")
      .load

    df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")

    val query = df
      .writeStream
      .outputMode(OutputMode.Append)
      .format("console")
      .option("checkpointLocation", "/tmp")
      .start

    query.awaitTermination

    spark.close
  }
}
