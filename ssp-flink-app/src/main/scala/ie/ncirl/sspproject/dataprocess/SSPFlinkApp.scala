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
import org.slf4j.LoggerFactory

class SSPFlinkApp {
}

object SSPFlinkApp {
  private val LOGGER = LoggerFactory.getLogger(classOf[SSPFlinkApp])

  val Hourly = "hourly_"
  val Daily = "daily_"
  val Weekly = "weekly_"

  def main(args: Array[String]): Unit = {
    // Read in the config.properties file
    val properties: Properties = new Properties()
    properties.load(Objects.requireNonNull(getClass.getResourceAsStream("/config.properties")))

    val kafkaServer = properties.getProperty("kafka.server")
    val kafkaTopics = properties.getProperty("kafka.topics")
    val kafkaEarliestOffset = properties.getProperty("kafka.earliest.offset").toBoolean

    val esServer = properties.getProperty("es.server")
    val esPort = properties.getProperty("es.port").toInt
    val esIndex = properties.getProperty("es.index")

    val timeWindowSecs = properties.getProperty("time.window.secs")

    val kafkaProperties: Properties = new Properties()
    kafkaProperties.setProperty("bootstrap.servers", kafkaServer)

    val kafkaConsumer: FlinkKafkaConsumer[String] = new FlinkKafkaConsumer(
      kafkaTopics,
      new SimpleStringSchema(),
      kafkaProperties)

    if (kafkaEarliestOffset) {
      kafkaConsumer.setStartFromEarliest()
    } else {
      kafkaConsumer.setStartFromLatest()
    }


    def buildAggstream(eventStream: DataStream[TelecomRecord], interval: String) = {
      eventStream
        .keyBy(interval + "timestamp", "area_code")
        .window(TumblingProcessingTimeWindows.of(Time.seconds(timeWindowSecs.toLong)))
        .process(
          new ProcessWindowFunction[TelecomRecord, TelecomAgg, Tuple, TimeWindow]() {
            override def process(key: Tuple, context: Context, recs: Iterable[TelecomRecord], out: Collector[TelecomAgg]): Unit = {
              LOGGER.info(s"Processing ${timeWindowSecs}sec window for $key with ${recs.size} events for $interval")

              val total_calls_in: Double = recs.map(_.calls_in).sum
              val total_calls_out: Double = recs.map(_.calls_out).sum
              val total_sms_in: Double = recs.map(_.sms_in).sum
              val total_sms_out: Double = recs.map(_.sms_out).sum

              val timestamp: Long = key.getField(0)
              val area_code: Int = key.getField(1)

              val outAgg = TelecomAgg(
                timestamp,
                area_code,
                total_calls_in,
                total_calls_out,
                total_sms_in,
                total_sms_out
              )

              out.collect(outAgg)
            }
          }
        )
    }

    def buildEsSink(interval: String): ElasticsearchSink[TelecomAgg] = {
      val httpHosts = new java.util.ArrayList[HttpHost]
      httpHosts.add(new HttpHost(esServer, esPort, "http"))

      val esSinkBuilderHourly = new ElasticsearchSink.Builder[TelecomAgg](
        httpHosts,
        new ElasticsearchSinkFunction[TelecomAgg] {
          def process(aggRec: TelecomAgg, ctx: RuntimeContext, indexer: RequestIndexer) {
            val mapper: ObjectMapper = new ObjectMapper()
            mapper.registerModule(DefaultScalaModule)

            val jsonData = mapper.writeValueAsString(aggRec)

            val rqst: IndexRequest = Requests.indexRequest
              .index(interval + esIndex)
              .source(jsonData, XContentType.JSON)

            indexer.add(rqst)
          }
        }
      )

      esSinkBuilderHourly.setBulkFlushMaxActions(1)
      esSinkBuilderHourly.build()
    }

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

    if (LOGGER.isTraceEnabled()) {
      telecomRecordStream.print
    }

    val telecomHourlyAggsStream = buildAggstream(telecomRecordStream, Hourly)
    telecomHourlyAggsStream.addSink(buildEsSink(Hourly))
    telecomHourlyAggsStream.print

    val telecomDailyAggsStream = buildAggstream(telecomRecordStream, Daily)
    telecomDailyAggsStream.addSink(buildEsSink(Daily))
    telecomDailyAggsStream.print

    val telecomWeeklyAggsStream = buildAggstream(telecomRecordStream, Weekly)
    telecomWeeklyAggsStream.addSink(buildEsSink(Weekly))
    telecomWeeklyAggsStream.print

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



