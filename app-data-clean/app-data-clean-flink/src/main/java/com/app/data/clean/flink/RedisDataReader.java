package com.app.data.clean.flink;

import com.app.data.clean.service.RedisClient;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import redis.clients.jedis.Jedis;

import java.util.Set;


public class RedisDataReader extends RichSourceFunction<Tuple2<String,String>> {

    private RedisClient redisClient;
    private Jedis jedis;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        redisClient = new RedisClient();
        jedis = redisClient.getJedis();

    }

    @Override
    public void run(SourceContext<Tuple2<String,String>> sourceContext) throws Exception {
        Set<String> keys = jedis.keys("*");

        Tuple2<String, String> tuple2 = new Tuple2<>();
        for (String key:keys) {
            String value = jedis.get(key);
            tuple2.setFields(key, value);
        }
        sourceContext.collect(tuple2);
    }

    @Override
    public void cancel() {
        if (null != jedis) {
            jedis = null;
        }
    }
}
