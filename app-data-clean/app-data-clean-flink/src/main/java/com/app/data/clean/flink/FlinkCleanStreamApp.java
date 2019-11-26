package com.app.data.clean.flink;


import com.app.data.clean.entity.*;
import com.app.data.clean.service.*;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer010;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.flink.util.OutputTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class FlinkCleanStreamApp {

    private static Logger logger = LoggerFactory.getLogger(FlinkCleanStreamApp.class);

    //map数据
    //private static String sourceTopic = "online_rtmap_data";
    //dbwork合作信息

    private static String sourceTopic = "online_rt_data";

    //及时爬数据
    //private static String sourceTopic = "online_rt_data";
    //private static String sourceTopic = "online_rt_data";

    //private static String targetTopic = "online_rtclean_data";
    //private static String targetTopic = "test_addressbook01";
    private static String targetTopic = "online_rtclean_data";

    private static String errorTopic ="online_error_data";

//    private static String addressBookTopic ="online_address_book";
//    private static String CooperativeTopic ="online_clean_cooperative";
    //private static String errorTopic ="reg_address_error";

    private static String servers = "192.168.20.140:9092,192.168.20.141:9092,192.168.20.142:9092";
    //private static String servers = "kafka1:9092,kafka2:9092,kafka3:9092";

    private static String groupId = "clean_data";
    //private static String groupId = "flink_task";
    //private static String groupId = "flink_task_TEST";
    //private static String groupId = "clean_data";
    //private static String groupId = "map_task";
    //private static String groupId = "map_point";

    private static String autoOffsetReset = "earliest";
    //private static String autoOffsetReset = "latest";
    //private static String autoOffsetReset = "earliest";

    //邢台惠敬电子商务有限公司

    private static Properties properties;


    public static void main(String[] args) {
        //获取streamExecuteEnvirment对象
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        //设置checkpoint
        executionEnvironment.enableCheckpointing(5000);
        executionEnvironment.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);
        //一致性保持
        executionEnvironment.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);

        //创建propertise参数
        properties = new Properties();
        //kafka地址
        properties.put("bootstrap.servers", servers);
        //properties.put("bootstrap.servers", "192.168.40.230:9092,192.168.40.231:9093,192.168.40.232:9094");

        //group.id：组名 不同组名可以重复消费。例如你先使用了组名A消费了kafka的1000条数据，
        //但是你还想再次进行消费这1000条数据，并且不想重新去产生，那么这里你只需要更改组名就可以重复消费了。
        properties.put("group.id", groupId);
        /*//enable.auto.commit：是否自动提交，默认为true
        properties.put("enable.auto.commit", "true");
        //auto.commit.interval.ms: 从poll(拉)的回话处理时长。
        properties.put("auto.commit.interval.ms", "1000");
        //session.timeout.ms:超时时间。
        properties.put("session.timeout.ms", "30000");
        //max.poll.records:一次最大拉取的条数。
        //auto.offset.reset：消费规则，默认earliest 。
        //earliest: 当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费 。
        //latest: 当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据 。
        //none: topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常。

*/

        properties.put("auto.offset.reset", autoOffsetReset);
        //key.serializer: 键序列化，默认org.apache.kafka.common.serialization.StringDeserializer。
        //properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringSerializer");
        //value.deserializer:值序列化，默认org.apache.kafka.common.serialization.StringDeserializer。
        //properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringSerializer");



        logger.info("flink开始消费数据");
        //flink消费Kafka数据
        DataStreamSource<String> dataStreamSource = executionEnvironment.
                addSource(new FlinkKafkaConsumer010<String>(sourceTopic, new SimpleStringSchema(), properties));
        //dataStreamSource.getExecutionConfig().setParallelism(48);

        //添加异步数据流redis
        //DataStreamSource<Tuple2<String, String>> redisStreamSource = executionEnvironment.addSource(new RedisDataReader());
        //dataStreamSource.(new AsyncInterfaceData())


        //设置WaterMarks
        //SingleOutputStreamOperator<String> Operator = dataStreamSource.assignTimestampsAndWatermarks(new StreamWaterMark());

        //数据清洗和完成插入HBase

        cleanData(executionEnvironment,dataStreamSource);

        logger.info("flink清洗完成");
    }



    private static final OutputTag<String> online_error_data = new OutputTag<String>("online_error_data") {};
    private static final OutputTag<String> online_rtclean_data = new OutputTag<String>("online_rtclean_data") {};
    //private static final OutputTag<String> reg_address_mysql = new OutputTag<String>("reg_address_mysql") {};
//    private static final OutputTag<String> bs_addressbook = new OutputTag<String>("bs_addressbook") {};
//    private static final OutputTag<String> cooperative = new OutputTag<String>("cooperative") {};

    //数据处理
    public static void cleanData(StreamExecutionEnvironment executionEnvironment, DataStreamSource<String> dataStreamSource) {



        //添加自定义的序列化
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(CleanShareholderinfoall.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(CleanFaithlessDetails.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(CleanPunishcreditchina.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(CleanBonds.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(CleanLandPurchase.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(CleanImportandexports.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(CleanCompanyAll.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(CleanAddress.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(GeoToAddress.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(AddressDetail.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(CrawlerData.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(KeyMan.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(Keymans.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(Shareholders.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(DealData.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(CompanyAll.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(CompanyAll.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(AddressBookInfo.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(PersonInfo.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(PromotionService.class, StreamSerializer.class);
        executionEnvironment.getConfig().registerTypeWithKryoSerializer(Cooperative.class, StreamSerializer.class);

        //SingleOutputStreamOperator<Object> map = dataStreamSource.rebalance().map(new FlinkappMap());
        //SingleOutputStreamOperator<Object> map = dataStreamSource.rebalance().process(new FlinkOutputMap());

        //添加异步IO
        SingleOutputStreamOperator<String> streamOperator = AsyncDataStream
                .unorderedWait(dataStreamSource, new AsyncInterfaceData(), 8000, TimeUnit.MILLISECONDS, 5000);

        /*SingleOutputStreamOperator<String> dataCleanStream = streamOperator.map(new MapFunction<String, String>() {
            @Override
            public String map(String cleanDate) throws Exception {
                //进行通讯录的信息处理,存在通讯录唯一ID时候
                JSONObject dealData = JSONObject.parseObject(cleanDate);
                if (cleanDate.contains("shareholders") && !cleanDate.contains("keymans")) {
                    dealData.put("addressBook_type", "onlyshareholders");
                } else if (cleanDate.contains("keymans") && !cleanDate.contains("shareholders")) {
                    dealData.put("addressBook_type", "onlykeymans");
                } else if (cleanDate.contains("keymans") && cleanDate.contains("shareholders")) {
                    dealData.put("addressBook_type", "bothall");
                } else {
                    dealData.put("addressBook_type", "nothing");
                }
                return dealData.toJSONString();
            }
        });*/

        SingleOutputStreamOperator<Object> dataCleanStream = streamOperator.rebalance().process(new FlinkOutputMap()).name("DataCleanStream");
               /* .coGroup(redisStreamSource)
                .where(message -> JSONObject.parseObject(message).getString("md5_id"))
                .equalTo(keyValue -> keyValue.f0)
                .window(ProcessingTimeSessionWindows.withGap(Time.seconds(100)))
                .apply(new CoGroupFunction<String, Tuple2<String, String>, Object>() {
                    @Override
                    public void coGroup(Iterable<String> iterable, Iterable<Tuple2<String, String>> iterable1, Collector<Object> collector) throws Exception {
                        Iterator<String> iterator = iterable.iterator();
                        JSONObject jsonObject = null;
                        while (iterator.hasNext()) {
                            //当判断的数据是一样的时候
                            String message = iterator.next();
                            jsonObject = JSONObject.parseObject(message);
                            Iterator<Tuple2<String, String>> iterator1 = iterable1.iterator();
                            while (iterator1.hasNext()) {
                                Tuple2<String, String> keyValue = iterator1.next();
                                jsonObject.put(keyValue.f0, keyValue.f1);
                            }
                        }
                        collector.collect(jsonObject.toJSONString());
                    }
                })*/

        // 设置执行并行度
        dataCleanStream.getExecutionConfig().setParallelism(1);
        //侧流输出kafka
//        DataStream<String> addressbook_data = dataCleanStream.getSideOutput(bs_addressbook);
        DataStream<String> sideOutput_err = dataCleanStream.getSideOutput(online_error_data);
        DataStream<String> sideOutput_suc = dataCleanStream.getSideOutput(online_rtclean_data);
//        DataStream<String> cooperative_info = dataCleanStream.getSideOutput(cooperative);
        //DataStream<String> mysql_reg_address = dataCleanStream.getSideOutput(reg_address_mysql);


        //持久化正常清洗数据
        logger.info("正常清洗数据持久化HBase,同时发送到kafka");
        dataCleanStream.addSink(new FlinkSink()).name("ToHBase");
        sideOutput_suc.addSink(new FlinkKafkaProducer010<String>(targetTopic,new SimpleStringSchema(),properties)).name("ToCleanKfafka");
        //错误数据结果写回kafka,参数分别是：写入topic，序列化器，kafka配置
        logger.info("错误数据发送回kafka");
        sideOutput_err.addSink(new FlinkKafkaProducer010<String>(errorTopic,new SimpleStringSchema(),properties)).name("ToErrorKafka");
        //把通讯录数据写入kafka
//        cooperative_info.addSink(new FlinkKafkaProducer010<String>(CooperativeTopic,new SimpleStringSchema(),properties)).name("Cooperative");
        //把通讯录数据写入kafka

//        addressbook_data.addSink(new FlinkKafkaProducer010<String>(addressBookTopic,new SimpleStringSchema(),properties)).name("addressBook");

        //注册地址信息写入MySQL
        //mysql_reg_address.addSink(new MySQLWriter()).name("ToMySQL");

        //添加异步IO,将这一部分数据处理写入MySQL的通讯录中
        /*SingleOutputStreamOperator<String> addressBookOperator = AsyncDataStream
                .unorderedWait(addressbook_data, new AsyncMySQLData(), 6000, TimeUnit.MILLISECONDS, 400);

        //写入通讯录
        addressBookOperator.addSink(new AddressBookWriter()).name("AddressBook");*/

        try {
            executionEnvironment.execute("DataCleanStream.....");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }



}
