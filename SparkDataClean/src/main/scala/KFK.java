import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import redis.clients.jedis.Jedis;

import java.util.Properties;

public class KFK {

    private static Jedis jedis = null;

    public static KafkaProducer getProducer(String ks){
        Properties properties=new Properties();
        properties.put("bootstrap.servers",ks);
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        //生产者
        KafkaProducer kafkaProducer=new KafkaProducer<String, String>(properties);
        return kafkaProducer;
    }

    public static void sendData(String ks,String cleanDate){

        KafkaProducer kafkaProducer = getProducer(ks);
        //jedis = RedisUtil.getJedis();
        //JSONObject jsonObject=null;
        try {
            //jsonObject = JSONObject.parseObject(cleanDate);
            kafkaProducer.send(new ProducerRecord<String, String>("hbkptest001",cleanDate));
            System.out.println(cleanDate);
        }catch (Exception e){
            System.out.println("转换jsonobject失败~");
        }

        /*    JSONObject ids = new JSONObject();
            String rowkey=jsonObject.getString("company_info_id");
            ids.put("id",rowkey);
            String md5_id = jsonObject.getString("md5_id");
            ids.put("md5",md5_id);

            //给redis的key加锁，NX表示只有当key不存在是才进行set
            jedis.setnx(md5_id, rowkey);
        try{
            //检测重复数据
            if(jedis.get(md5_id).equals(rowkey)){
                //id发回kafka
                kafkaProducer.send(new ProducerRecord<String, String>("appreturnid",ids.toJSONString()));
                //数据发回kafka
                kafkaProducer.send(new ProducerRecord<String, String>("appreturndata",cleanDate));
                //hbase消费数据的kafka
                kafkaProducer.send(new ProducerRecord<String, String>("InsertHBase",cleanDate));

                //System.out.println("pachong:"+value);
            }else {
                //更新hbase数据
                String id = jedis.get(md5_id);
                jsonObject.put("company_info_id", id);
                kafkaProducer.send(new ProducerRecord<String, String>("appreturndata",jsonObject.toJSONString()));
                //hbase消费数据的kafka
                kafkaProducer.send(new ProducerRecord<String, String>("InsertHBase",jsonObject.toJSONString()));
            }
        }catch (Exception e){
            System.out.println("insertHbaseKFK错误~："+cleanDate);
        }*/
    }
}
