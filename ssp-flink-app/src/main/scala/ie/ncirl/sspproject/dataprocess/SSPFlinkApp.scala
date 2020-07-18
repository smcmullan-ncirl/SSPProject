package ie.ncirl.sspproject.dataprocess

import java.util.{Objects, Properties}

import org.apache.flink.api.common.functions.RuntimeContext
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.flink.streaming.api.datastream.DataStreamSource
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.elasticsearch.{ElasticsearchSinkFunction, RequestIndexer}
import org.apache.flink.streaming.connectors.elasticsearch7.ElasticsearchSink
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.streaming.util.serialization.JSONKeyValueDeserializationSchema
import org.apache.http.HttpHost
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.Requests

class SSPFlinkApp {
}

object SSPFlinkApp {
  def main(args: Array[String]): Unit = {
    // Read in the config.properties file
    val properties: Properties = new Properties()
    properties.load(Objects.requireNonNull(getClass.getResourceAsStream("/config.properties")))

    val kafkaServer = properties.getProperty("kafka.server")
    val kafkaTopics = properties.getProperty("kafka.topics")

    val esServer = properties.getProperty("es.server")
    val esPort = properties.getProperty("es.port").toInt
    val esIndex = properties.getProperty("es.index")

    val kafkaProperties: Properties = new Properties()
    kafkaProperties.setProperty("bootstrap.servers", kafkaServer)

    val kafkaConsumer: FlinkKafkaConsumer[ObjectNode] = new FlinkKafkaConsumer(
      kafkaTopics,
      new JSONKeyValueDeserializationSchema(false),
      kafkaProperties)

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val messageStream: DataStreamSource[ObjectNode] = env.addSource(kafkaConsumer)

    val httpHosts = new java.util.ArrayList[HttpHost]
    httpHosts.add(new HttpHost(esServer, esPort, "http"))

    val esSinkBuilder = new ElasticsearchSink.Builder[ObjectNode](
      httpHosts,
      new ElasticsearchSinkFunction[ObjectNode] {
        def process(element: ObjectNode, ctx: RuntimeContext, indexer: RequestIndexer) {
          val json = new java.util.HashMap[String, String]
          json.put("data", element.get("value").asText())

          val rqst: IndexRequest = Requests.indexRequest
            .index(esIndex)
            .source(json)

          indexer.add(rqst)
        }
      }
    )

    esSinkBuilder.setBulkFlushMaxActions(1)

    messageStream.addSink(esSinkBuilder.build())

    env.execute
  }
}

