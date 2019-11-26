import kafka.serializer.StringDecoder
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils

object SaprkStreamingKafkaDirect {
	//正式环境
	val ks="192.168.20.130:9092,192.168.20.130:9093,192.168.20.130:9094"
	//测试环境
	//val ks="hs0:9092,hs1:9092,hs2:9092"
	def main(args: Array[String]): Unit = {
		//创建sparkConf
		val sparkConf: SparkConf = new SparkConf().setAppName("SaprkStreamingKafkaDirect").setMaster("local[8]")
		//创建sparkContext
		val sparkContext = new SparkContext(sparkConf)
		//设置警告级别
		sparkContext.setLogLevel("WARN")
		//创建streamingContext  Seconds设置窗口大小（秒）
		val streamingContext = new StreamingContext(sparkContext,Seconds(1))
		//streamingContext.checkpoint("./sskd_ck")
		//准备kafka参数
		val kafkaParams=Map("metadata.broker.list"->ks,"group.id"->"test29")
		//准备topics
		val topics=Set("appflow")
		//获取kafka数据
		val dstream: InputDStream[(String, String)] = KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](streamingContext,kafkaParams,topics)
		//获取topic中的数据
		val line: DStream[String] = dstream.map(_._2)
		//调用数据处理代码
		val data: DStream[String] = line.map(DataClean.getData2(_))
		val value: DStream[Unit] = data.map(KFK.sendData(ks,_))
		data.print()
		//开启
		streamingContext.start()
		streamingContext.awaitTermination()
	}
}
