package com.app.data.clean.flink;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.app.data.clean.entity.CrawlerData;
import com.app.data.clean.service.*;
import jodd.util.StringUtil;
import org.apache.flink.api.common.functions.MapFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.UUID;

public class FlinkAppMap implements MapFunction<String, Object>, Serializable {



    private static final long serialVersionUID = 2186704587450967314L;

    private Logger logger = LoggerFactory.getLogger(FlinkAppMap.class);

    private String targetTopic = "online_return_data";

    private String errorTopic = "online_error_data";

    private String servers;

    private String groupId;

    private String autoOffsetReset;



    private CleanShareholderinfoall cleanShareholderinfoall;

    private CleanFaithlessDetails cleanFaithlessDetails;

    private CleanPunishcreditchina cleanPunishcreditchina;

    private CleanBonds cleanBonds;

    private CleanLandPurchase cleanLandPurchase;

    private CleanImportandexports cleanImportandexports;

    private CleanCompanyAll cleanCompanyAll;

    private CleanAddress cleanAddress ;



    public FlinkAppMap() {
        cleanImportandexports = new CleanImportandexports();
        cleanCompanyAll = new CleanCompanyAll();
        cleanAddress = new CleanAddress();
        cleanLandPurchase = new CleanLandPurchase();
        cleanBonds = new CleanBonds();
        cleanPunishcreditchina = new CleanPunishcreditchina();
        cleanFaithlessDetails = new CleanFaithlessDetails();
        cleanShareholderinfoall = new CleanShareholderinfoall();
    }


    /*    private StreamExecutionEnvironment executionEnvironment = null;

    //创建propertise参数
    private Properties properties = null;*/


    public Object map(String message) {

        //处理数据
        String cleanDate = "";
        logger.info("{}-原数据:{}", targetTopic, message);
        try {
            cleanDate = getData(message);

            logger.info("清洗数据完成数据:{}", cleanDate);
            //kafkaTemplate.send(targetTopic, cleanDate);


            logger.info("数据发出成功");
        } catch (LevelRuntimeException e) {
            logger.info("{}", e.getMessage());
        } catch (Exception e) {
            //更新  online_error_data
            //kafkaTemplate.send(errorTopic, message);
            logger.error("数据清洗错误~,错误数据：", e);

        }
        return cleanDate;

    }



    public String getData(String value) throws Exception {
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(value);
        } catch (Exception e) {
            logger.error("JSON数据格式错误", e);
            throw e;
        }
        try {
            CrawlerData crawlerData = cleanId(jsonObject);
//      开始解析
//        jsonObject.put("grab_time", DateUtil.formatDate(new Date()));
            cleanShareholderinfoall.clean(jsonObject);
            cleanFaithlessDetails.clean(jsonObject);
            cleanPunishcreditchina.clean(jsonObject);
            cleanBonds.clean(jsonObject);
            cleanLandPurchase.clean(jsonObject);
            cleanImportandexports.clean(jsonObject);
            cleanCompanyAll.clean(jsonObject);
            cleanAddress.clean(jsonObject);
            //清洗完成标记时间
            crawlerData.getSign().setEnd_clean_time(DateTime.now());
            crawlerData.getSign().setClean_cost_time(DateUtil.between(crawlerData.getSign().getStart_clean_time(), crawlerData.getSign().getEnd_clean_time(), DateUnit.MS));
            jsonObject.put("sign", crawlerData.getSign());
            logger.info("数据数据时间{}毫秒", crawlerData.getSign().getClean_cost_time());
            jsonObject.put("grab_time", crawlerData.getGrabTime());
            //通过下划线输出字段
            SerializeConfig config = new SerializeConfig();
            config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
            return JSONObject.toJSONString(jsonObject, config, SerializerFeature.DisableCircularReferenceDetect);
        } catch (Exception e) {
            //如果出错从redis中删除id
            String id = jsonObject.getString("company_info_id");
            //stringRedisTemplate.delete(id);
            throw e;
        }
    }


    private CrawlerData cleanId(JSONObject jsonObject) throws Exception {
        try {
            CrawlerData crawlerData = jsonObject.toJavaObject(CrawlerData.class);
            final String id = cleanLevelOne(crawlerData.getMd5Id(), crawlerData.getSign().getData_level());
            crawlerData.setCompanyInfoId(id);
            jsonObject.put("company_info_id", id);
            crawlerData.getSign().setStart_clean_time(DateTime.now());
            return crawlerData;
        } catch (LevelRuntimeException e) {
            throw e;
        } catch (Exception e) {
            logger.error("没有头文件错误", e);
            throw e;
        }
    }

    public String cleanLevelOne(String md5_id, Integer dataLevel) {
        final String rowkey = StringUtil.remove(UUID.randomUUID().toString(), "-").toLowerCase();
        /*String id = stringRedisTemplate.opsForValue().get(md5_id);
        if (StringUtil.isNotBlank(id)) {
            if (dataLevel == 1) {
//                throw new LevelRuntimeException("数据等级为1级的数据已经存在不需要清洗");
            }
        } else {
            stringRedisTemplate.opsForValue().setIfAbsent(md5_id, rowkey);
        }
        id = stringRedisTemplate.opsForValue().get(md5_id);*/
        return rowkey;
    }













     /*
    public CrawlerData cleanId(JSONObject jsonObject) throws Exception {
        try {
            CrawlerData crawlerData = jsonObject.toJavaObject(CrawlerData.class);
            //System.out.println( crawlerData.getSign().getData_leavl());
            final String id = cleanLevelOne(crawlerData.getMd5_id(), crawlerData.getSign().getData_leavl());
            crawlerData.setCompany_info_id(id);
            jsonObject.put("company_info_id", id);
            crawlerData.getSign().setStart_clean_time(DateTime.now());
            return crawlerData;
        } catch (LevelRuntimeException e) {
            throw e;
        } catch (Exception e) {
            logger.error("没有头文件错误", e);
            throw e;
        }
    }

    public String cleanLevelOne(String md5_id, Integer dataLevel) {
        final String rowkey = StringUtil.remove(UUID.randomUUID().toString(), "-").toLowerCase();
        String id = stringRedisTemplate.opsForValue().get(md5_id);
        if (StringUtil.isNotBlank(id)) {
            if (dataLevel == 1) {
                throw new LevelRuntimeException("数据等级为1级的数据已经存在不需要清洗");
            }
        } else {
            stringRedisTemplate.opsForValue().setIfAbsent(md5_id, rowkey);
        }
        id = stringRedisTemplate.opsForValue().get(md5_id);
        return id;
    }


    *//*public static final class RedisDataMapper implements RedisMapper<Tuple2<String,String>> {


        //设置数据使用的数据结构 String 并设置key的名称
        @Override
        public RedisCommandDescription getCommandDescription() {
            return new RedisCommandDescription(RedisCommand.SET, null);
        }

        @Override
        public String getKeyFromData(Tuple2<String,String> data) {
            return data.f0;
        }

        @Override
        public String getValueFromData(Tuple2<String,String> data) {
            return data.f1;
        }
    }


    public StreamExecutionEnvironment getEnvirment() {
        //获取streamExecuteEnvirment对象
        executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        //设置checkpoint
        executionEnvironment.enableCheckpointing(1000);
        executionEnvironment.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        return executionEnvironment;

    }

    public Properties getProperties() {

        //创建propertise参数
        properties = new Properties();
        //kafka地址
        properties.put("bootstrap.servers", "192.168.20.140:9092,192.168.20.141:9093,192.168.20.142:9094");
        //properties.put("bootstrap.servers", "192.168.40.230:9092,192.168.40.231:9093,192.168.40.232:9094");

        //group.id：组名 不同组名可以重复消费。例如你先使用了组名A消费了kafka的1000条数据，
        //但是你还想再次进行消费这1000条数据，并且不想重新去产生，那么这里你只需要更改组名就可以重复消费了。
        properties.put("group.id", groupId);
        //enable.auto.commit：是否自动提交，默认为true
        properties.put("enable.auto.commit", "true");
        //auto.commit.interval.ms: 从poll(拉)的回话处理时长。
        properties.put("auto.commit.interval.ms", "1000");
        //session.timeout.ms:超时时间。
        properties.put("session.timeout.ms", "30000");
        properties.put("auto.offset.reset", autoOffsetReset);
        //key.serializer: 键序列化，默认org.apache.kafka.common.serialization.StringDeserializer。
        properties.put("key.deserializer", StringDeserializer .class.getName());
        //value.deserializer:值序列化，默认org.apache.kafka.common.serialization.StringDeserializer。
        properties.put("value.deserializer", StringDeserializer.class.getName());
        return properties;
    }
*/

}
