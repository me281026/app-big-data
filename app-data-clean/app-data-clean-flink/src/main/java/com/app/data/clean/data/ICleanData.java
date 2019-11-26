package com.app.data.clean.data;

import com.alibaba.fastjson.JSONObject;

public interface ICleanData {

    void clean(JSONObject source) throws Exception;

}
