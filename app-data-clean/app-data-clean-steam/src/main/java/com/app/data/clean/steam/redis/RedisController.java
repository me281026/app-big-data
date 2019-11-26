package com.app.data.clean.steam.redis;

import com.app.data.clean.steam.kafka.KafkaConsumer;
import com.app.data.clean.steam.kafka.KafkaDataToClean;
import com.app.data.clean.steam.service.DataClean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/data")
@RestController
public class RedisController {

    @Autowired
    private DataClean dataClean;

    @Autowired
    private KafkaConsumer kafkaConsumer;


    @RequestMapping("/clean")
    public String clean(@RequestBody String data) {
        try {
            return dataClean.getData(data);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping("/cleanToDb")
    public String cleanToDb(@RequestBody String data) {
        try {
//            String result = dataClean.getData(data);
            kafkaConsumer.receive(data);
            return "scuess";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


}
