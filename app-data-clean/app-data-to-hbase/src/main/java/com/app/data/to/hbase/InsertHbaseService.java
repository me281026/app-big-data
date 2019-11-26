package com.app.data.to.hbase;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InsertHbaseService {

    private Logger logger = LoggerFactory.getLogger(InsertHbaseService.class);


    @KafkaListener(topics = {"${data.topic.target}"})
    public void receive(String value) {
        try {
            String rowkey = (JSONObject.parseObject(value)).getString("company_info_id");
            HBaseDML.insertJson("companyinfo", rowkey, "info", value);
            logger.info(rowkey + "，地址：" + (JSONObject.parseObject(value)).getString("address_detail"));
        } catch (Exception e) {
            logger.info("数据转json失败：" + value);
        }
    }


}
