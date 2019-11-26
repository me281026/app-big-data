package error.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class KafkaConsumer {

    private Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);


    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @KafkaListener(topics = {"online_error_data"})
    public void receive(String message) {
        logger.info("原数据:" + message);
        JSONObject obj = JSON.parseObject(message);
        try {
            mongoTemplate.save(obj, "test");
            kafkaTemplate.send("online_daily_data", message);
//            Map<String, Object> map = mongoTemplate.findById(obj.getString("_id"), Map.class);
//            mongoTemplate.remove(map);
        } catch (Exception e) {
            //更新  online_error_data
            logger.info("数据清洗错误,错误数据：" + message + "\n" + e);
            kafkaTemplate.send("online_error_data", message);
        }
    }
}
