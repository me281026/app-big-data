package com.app.data.clean.steam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CleanApplication {
    public static void main(String[] args) {
        SpringApplication.run(CleanApplication.class, args);
    }
}
