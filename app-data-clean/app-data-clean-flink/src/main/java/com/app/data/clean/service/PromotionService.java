package com.app.data.clean.service;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class PromotionService implements Serializable {

    public String dealPromotionInfo(String info) {
        JSONObject jsonObject = JSONObject.parseObject(info);
        Object sign = jsonObject.remove("sign");
        return jsonObject.toJSONString();
    }


}
