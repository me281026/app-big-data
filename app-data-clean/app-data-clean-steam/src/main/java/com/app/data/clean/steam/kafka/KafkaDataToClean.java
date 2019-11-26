package com.app.data.clean.steam.kafka;

import com.app.data.clean.steam.service.DataClean;
import com.app.data.clean.steam.service.LevelRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaDataToClean {
    private Logger logger = LoggerFactory.getLogger(KafkaDataToClean.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private DataClean dataClean;

    public void clean(String message, String targetTopic, String errorTopic) {
        logger.info("{}-原数据:{}", targetTopic, message);
        try {
            String cleanDate = dataClean.getData(message);
            logger.info("清洗数据完成数据:{}", cleanDate);
            kafkaTemplate.send(targetTopic, cleanDate);
            logger.info("数据发出成功");
        } catch (LevelRuntimeException e) {
            logger.info("{}", e.getMessage());
        } catch (Exception e) {
            //更新  online_error_data
            kafkaTemplate.send(errorTopic, message);
            logger.error("数据清洗错误~,错误数据：", e);

        }
    }
}
