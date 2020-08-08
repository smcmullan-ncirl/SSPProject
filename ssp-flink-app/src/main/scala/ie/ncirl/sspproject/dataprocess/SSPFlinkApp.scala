package ie.ncirl.sspproject.dataprocess

import java.util.{Date, Objects, Properties}

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.apache.flink.api.common.functions.{MapFunction, RuntimeContext}
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.streaming.connectors.elasticsearch.{ElasticsearchSinkFunction, RequestIndexer}
import org.apache.flink.streaming.connectors.elasticsearch7.ElasticsearchSink
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.util.Collector
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
    kafkaConsumer.setStartFromLatest()

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime)

    val messageStream: DataStream[String] = env.addSource(kafkaConsumer)

    val telecomRecordStream: DataStream[TelecomRecord] = messageStream.map(
      new MapFunction[String, TelecomRecord] {
        override def map(jsonString: String): TelecomRecord = {
          val mapper: ObjectMapper = new ObjectMapper()
          mapper.registerModule(DefaultScalaModule)
          mapper.readValue(jsonString, classOf[TelecomRecord])
        }
      }
    )

    val telecomHourlyAggs = telecomRecordStream
      .keyBy("hourly_timestamp", "area_code")
      .window(TumblingProcessingTimeWindows.of(Time.seconds(60)))
      .process(new ProcessWindowFunction[TelecomRecord, TelecomAgg, Tuple, TimeWindow]() {
        override def process(key: Tuple, context: Context, elements: Iterable[TelecomRecord], out: Collector[TelecomAgg]): Unit = {
          var total_calls_in: Double = 0
          var total_calls_out: Double = 0
          var total_sms_in: Double = 0
          var total_sms_out: Double = 0

          while(elements.iterator.hasNext) {
            val rec = elements.iterator.next

            total_calls_in += rec.calls_in
            total_calls_out += rec.calls_out
            total_sms_in += rec.sms_in
            total_sms_out += rec.sms_out
          }

          out.collect(
            TelecomAgg(
              key.getField(1),
              key.getField(2),
              total_calls_in,
              total_calls_out,
              total_sms_in,
              total_sms_out)
          )
        }
      }
      )

    val httpHosts = new java.util.ArrayList[HttpHost]
    httpHosts.add(new HttpHost(esServer, esPort, "http"))

    val esSinkBuilderHourly = new ElasticsearchSink.Builder[TelecomAgg](
      httpHosts,
      new ElasticsearchSinkFunction[TelecomAgg] {
        def process(element: TelecomAgg, ctx: RuntimeContext, indexer: RequestIndexer) {
          val mapper: ObjectMapper = new ObjectMapper()
          val jsonData = mapper.writeValueAsString(element)

          val rqst: IndexRequest = Requests.indexRequest
            .index(esIndex + "_hourly")
            .source(jsonData, XContentType.JSON)

          indexer.add(rqst)
        }
      }
    )

    esSinkBuilderHourly.setBulkFlushMaxActions(1)

    telecomHourlyAggs.addSink(esSinkBuilderHourly.build())

    telecomHourlyAggs.print

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
    timestamp_str: Date,
    hourly_timestamp: Long,
    hourly_timestamp_str: Date,
    daily_timestamp: Long,
    daily_timestamp_str: Date,
    weekly_timestamp: Long,
    weekly_timestamp_str: Date
  ) extends Serializable

  case class TelecomAgg
  (
    timestamp: Long,
    area_code: Int,
    calls_in: Double,
    calls_out: Double,
    sms_in: Double,
    sms_out: Double
  )
}



