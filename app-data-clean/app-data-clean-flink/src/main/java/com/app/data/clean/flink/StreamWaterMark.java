package com.app.data.clean.flink;

import com.alibaba.fastjson.JSONObject;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StreamWaterMark implements AssignerWithPeriodicWatermarks<String>, Serializable {


    public long currentMaxTimestamp = 0L;
    public static final long maxOutOfOrderness = 10000L;//最大允许的乱序时间是10s
    Watermark a = null;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Nullable
    @Override
    public Watermark getCurrentWatermark() {
        a = new Watermark(currentMaxTimestamp - maxOutOfOrderness);
        return a;
    }

    @Override
    public long extractTimestamp(String string, long l) {
        JSONObject jsonObject = JSONObject.parseObject(string);
        String time = jsonObject.getString("time_stamp");
        long timestamp = 0;
        try {
            timestamp = format.parse(time).getTime();
        }catch (ParseException e) {
            e.printStackTrace();
        }
        currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp);
        return timestamp;
    }


}
