package com.app.data.clean.data;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import jodd.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public abstract class AbstractCleanData implements com.app.data.clean.data.ICleanData {

    private Logger logger = LoggerFactory.getLogger(AbstractCleanData.class);


    protected JSONObject source;

    //    private String key;
//    private boolean isArray = false;
    public void clean(JSONObject source) throws Exception {
        this.source = source;
        if (this.getKey() == null) {
            try {
                this.cleanData(source);
            } catch (Exception e) {
                throw e;
            }
        } else {
            Object arr = null;
            Object obj = null;
            if (source.containsKey(this.getKey())) {
                try {
                    try {
                        JSONArray arr2 = source.getJSONArray(this.getKey());
                        arr = this.cleanArray(arr2);
                        putValue(this.getKey(), source, arr);
                    } catch (Exception e) {
                        JSONObject obj2 = source.getJSONObject(this.getKey());
                        obj = this.cleanData(obj2);
                        putValue(this.getKey(), source, obj);
                    }
                } catch (Exception e) {
                    throw e;
                }
            } else {
                try {
                    obj = this.cleanData(new JSONObject());
                    putValue(this.getKey(), source, obj);
                } catch (Exception e) {
                    throw e;
                }
            }
        }
    }

    private void putValue(String key, JSONObject source, Object val) {
        if (val == null) {
            return;
        }
        if (key == null) {
            return;
        }
        if (source != val) {
            source.put(key, val);
        } else {
            String temp = JSON.toJSONString(val, SerializerFeature.DisableCircularReferenceDetect);
            source.put(key, JSON.parseObject(temp));
        }
    }


    public abstract String getKey();

    public abstract Object cleanData(JSONObject jsonObject) throws Exception;

    public abstract Object cleanArray(JSONArray jsonArray) throws Exception;

    protected String getTime(String time) {
        Long l = null;
        try {
            l = Long.parseLong(time);
        } catch (Exception e) {
            System.out.println("时间-->暂无信息");
        }
        if (l == null) {
            return time;
        } else {
            return DateUtil.format(new Date(l), "yyyy-MM-dd");
        }
    }

    protected void putDefaultValue(JSONObject jsonObject, String key, String value) {
        if (jsonObject.containsKey(key)) {
            jsonObject.put(key, value);
        } else if (StringUtil.isBlank(jsonObject.getString(key))) {
            jsonObject.put(key, value);
        }
    }
}
