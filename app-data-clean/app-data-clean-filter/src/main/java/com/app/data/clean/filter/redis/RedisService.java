package com.app.data.clean.filter.redis;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.LineHandler;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostConstruct
    public void init() {
        FileUtil.readUtf8Lines(new File("D:\\Desktop\\sys_company_id.txt"), new LineHandler() {
            private int index = 0;

            @Override
            public void handle(String s) {
                String[] arr = StrUtil.split(s, "\t");
                String cinfo = arr[1];
                String md5 = arr[2];
                if (StrUtil.isNotBlank(cinfo) && StrUtil.isNotBlank(md5)) {
                    stringRedisTemplate.opsForValue().set(md5, cinfo);
                    index++;
                    System.out.println(index);
                }
            }
        });

    }
}
