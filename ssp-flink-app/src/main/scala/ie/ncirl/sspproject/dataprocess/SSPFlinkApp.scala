package ie.ncirl.sspproject.dataprocess

import java.util.{Date, Objects, Properties}

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.apache.flink.api.common.functions.{MapFunction, ReduceFunction, RuntimeContext}
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.datastream.{DataStreamSource, SingleOutputStreamOperator}
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.connectors.elasticsearch.{ElasticsearchSinkFunction, RequestIndexer}
import org.apache.flink.streaming.connectors.elasticsearch7.ElasticsearchSink
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.http.HttpHost
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.Requests
import org.elasticsearch.common.xcontent.XContentType

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

    val kafkaConsumer: FlinkKafkaConsumer[String] = new FlinkKafkaConsumer(
      kafkaTopics,
      new SimpleStringSchema(),
      kafkaProperties)

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val messageStream: DataStreamSource[String] = env.addSource(kafkaConsumer)

    val telecomRecordStream: SingleOutputStreamOperator[TelecomRecord] = messageStream.map(
      new MapFunction[String, TelecomRecord] {
        override def map(jsonString: String): TelecomRecord = {
          val mapper: ObjectMapper = new ObjectMapper()
          mapper.registerModule(DefaultScalaModule)
          mapper.readValue(jsonString, classOf[TelecomRecord])
        }
      }
    )

    telecomRecordStream
      .timeWindowAll(Time.seconds(5))
      .reduce(new ReduceFunction[TelecomRecord] {
        override def reduce(t1: TelecomRecord, t2: TelecomRecord): TelecomRecord = {
          t1
        }
      })
      .print

    val httpHosts = new java.util.ArrayList[HttpHost]
    httpHosts.add(new HttpHost(esServer, esPort, "http"))

    val esSinkBuilder = new ElasticsearchSink.Builder[TelecomRecord](
      httpHosts,
      new ElasticsearchSinkFunction[TelecomRecord] {
        def process(element: TelecomRecord, ctx: RuntimeContext, indexer: RequestIndexer) {
          val mapper: ObjectMapper = new ObjectMapper()
          val jsonData = mapper.writeValueAsString(element)

          val rqst: IndexRequest = Requests.indexRequest
            .index(esIndex)
            .source(jsonData, XContentType.JSON)

          indexer.add(rqst)
        }
      }
    )

    esSinkBuilder.setBulkFlushMaxActions(1)

    telecomRecordStream.addSink(esSinkBuilder.build())

    env.execute
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
    timestamp_str: Date
  ) extends Serializable
}



