package ie.ncirl.sspproject.dataprocess

import java.sql.Timestamp
import java.util.Properties

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.streaming.util.serialization.JSONKeyValueDeserializationSchema

class SSPFlinkApp {
}

object SSPFlinkApp {
  def main(args: Array[String]): Unit = {
    val kafkaServer = "kafka:9092"
    val kafkaTopics: String = "telecom_trento"

    val properties: Properties = new Properties()
    properties.setProperty("bootstrap.servers", kafkaServer)

    val kafkaConsumer: FlinkKafkaConsumer[ObjectNode] = new FlinkKafkaConsumer(
      kafkaTopics,
      new JSONKeyValueDeserializationSchema(false),
      properties)

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val messageStream = env.addSource(kafkaConsumer)

    messageStream.map((value: ObjectNode) => value.get("field").asInstanceOf[TelecomRecord])

    env.execute
  }
}

case class TelecomRecord
(
  cell_id: String,
  timestamp: Timestamp,
  area_code: Int,
  calls_in: Double,
  calls_out: Double,
  sms_in: Double,
  sms_out: Double,
  internet_activity: Double
)