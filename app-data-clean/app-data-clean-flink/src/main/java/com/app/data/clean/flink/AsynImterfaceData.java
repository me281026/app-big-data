package com.app.data.clean.flink;

import com.alibaba.fastjson.JSONObject;
import com.app.data.clean.entity.CrawlerData;
import jodd.util.StringUtil;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;
import java.util.Collections;
import java.util.UUID;

public class AsynImterfaceData extends RichAsyncFunction<String, String> implements Serializable {


    private Logger logger = LoggerFactory.getLogger(AsynImterfaceData.class);


    //private transient RedisClient client;

    public transient JedisPool jedisPool;
    public transient Jedis jedis;
    public String host = "192.168.20.140";


    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        jedisPool = new JedisPool(new GenericObjectPoolConfig(),this.host,6379);
        jedis = jedisPool.getResource();
    }

    @Override
    public void close() throws Exception {
        //连接关闭
        //client.close();
        if (null != jedis) {
            jedis.close();
        }
        if (null != jedisPool) {
            jedisPool.destroy();
        }

    }

    @Override
    public void asyncInvoke(String message, ResultFuture<String> asyncCollector) throws Exception {
        logger.info("连接redis查询...");
        // 发出异步请求，返回结果的 Future
        //System.out.println("kafkaSend"+System.currentTimeMillis()+message);
        JSONObject jsonObject = JSONObject.parseObject(message);
        CrawlerData crawlerData = jsonObject.toJavaObject(CrawlerData.class);
        final String rowkey = StringUtil.remove(UUID.randomUUID().toString(), "-").toLowerCase();
        //String md5Id = crawlerData.getMd5Id();
        String md5Id = jsonObject.getString("md5_id");
        //String md5Id = "820b42040a3280bc1bd9f525c4d58bb9";
        String id = jedis.get(md5Id);


         /*String id = stringRedisTemplate.opsForValue().get(md5_id);
        if (StringUtil.isNotBlank(id)) {
            if (dataLevel == 1) {
//                throw new LevelRuntimeException("数据等级为1级的数据已经存在不需要清洗");
            }
        } else {
            stringRedisTemplate.opsForValue().setIfAbsent(md5_id, rowkey);
        }
        id = stringRedisTemplate.opsForValue().get(md5_id);*/

        if (StringUtil.isNotBlank(id)) {
            if (crawlerData.getSign().getData_level() == 1) {
                jsonObject.put("repeat_data","1");
//                throw new LevelRuntimeException("数据等级为1级的数据已经存在不需要清洗");
            }
        } else {
            id = rowkey;
            jedis.set(md5Id,id);
        }
        //crawlerData.setCompanyInfoId(id);
        jsonObject.put("company_info_id",id);
        //String jsonString = JSONObject.toJSONString(crawlerData);
        asyncCollector.complete(Collections.singleton(jsonObject.toJSONString()));


    }


}
