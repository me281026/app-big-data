package com.app.data.clean.steam.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumer {

    private Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Value("${data.topic.target}")
    private String targetTopic;

    @Value("${data.topic.error}")
    private String errorTopic;

    @Autowired
    private com.app.data.clean.steam.kafka.KafkaDataToClean kafkaDataToClean;

    @KafkaListener(topics = {"${data.topic.source}"})
    public void receive(String message) {
        kafkaDataToClean.clean(message, targetTopic, errorTopic);
    }
}
