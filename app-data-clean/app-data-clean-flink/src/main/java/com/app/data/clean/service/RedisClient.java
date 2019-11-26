package com.app.data.clean.service;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;


public class RedisClient implements KryoSerializable, Serializable {

    public static JedisPool jedisPool;

    public String host = "192.168.20.140";



    public RedisClient(){
        Runtime.getRuntime().addShutdownHook(new CleanWorkThread());
        //jedisPool = new JedisPool(jedisPoolConfig(),this.host,6379);
        jedisPool = new JedisPool(new GenericObjectPoolConfig(),this.host,6379);

    }

    @Override
    public void write(Kryo kryo, Output output) {
        kryo.writeObject(output,host);
    }

    @Override
    public void read(Kryo kryo, Input input) {
        host = kryo.readObject(input,String.class);
        this.jedisPool = new JedisPool(new GenericObjectPoolConfig(),host);

    }


    static class CleanWorkThread extends Thread {
        @Override
        public void run() {
            if (null != jedisPool ) {
                jedisPool.destroy();
                jedisPool = null;
            }
        }
    }

    public void closeConnect() {
        if (null != jedisPool) {
            jedisPool.destroy();
            jedisPool = null;
        }
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public void returnResource(Jedis jedis) {
        jedisPool.returnResource(jedis);
    }

/*    public String getId(String md5_id) {
        return getJedis().get(md5_id);
    } */

  /*  public JedisPoolConfig jedisPoolConfig(){
        //1 获得连接池配置对象，设置配置项
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数
        config.setMaxTotal(30);
        //最大空闲连接数
        config.setMaxIdle(10);
        //获取Jedis连接的最大等待时间（50秒）
        config.setMaxWaitMillis(3 * 1000);
        //在获取Jedis连接时，自动检验连接是否可用
        config.setTestOnBorrow(true);
        //在将连接放回池中前，自动检验连接是否有效
        config.setTestOnReturn(true);
        //自动测试池中的空闲连接是否都是可用连接
        config.setTestWhileIdle(true);
        return config;
    }*/




}
