import kafka.serializer.StringDecoder
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
object appInHBase {

	val ks="192.168.20.130:9092,192.168.20.130:9093,192.168.20.130:9094"

	def main(args: Array[String]): Unit = {
		//创建sparkConf
		val sparkConf: SparkConf = new SparkConf().setAppName("appInHBase").setMaster("local[4]")
		//创建sparkContext
		val sparkContext = new SparkContext(sparkConf)
		//设置警告级别
		sparkContext.setLogLevel("WARN")
		//创建streamingContext  Seconds设置窗口大小（秒）
		val streamingContext = new StreamingContext(sparkContext,Seconds(16))
		//streamingContext.checkpoint("./sskd_ck")
		//准备kafka参数
		val kafkaParams=Map("metadata.broker.list"->ks,"group.id"->"inhbasehb")
		//准备topics
		//val topics=Set("InsertHBase")
		val topics=Set("InsertHBase")
		//获取kafka数据
		val dstream: InputDStream[(String, String)] = KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](streamingContext,kafkaParams,topics)
		//获取topic中的数据
		val line: DStream[String] = dstream.map(_._2)
		//调用数据处理代码
		try{
			val data: DStream[Unit] = line.map(HBaseDML.hbinjson(_))
			data.print()
		}catch {
			case e: ArithmeticException => println("写入hbase失败~"+line+"原因："+e)
		}
		//开启
		streamingContext.start()
		streamingContext.awaitTermination()
	}
}
