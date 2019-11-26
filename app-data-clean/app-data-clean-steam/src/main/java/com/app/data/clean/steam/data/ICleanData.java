package com.app.data.clean.steam.data;

import com.alibaba.fastjson.JSONObject;

public interface ICleanData {

    void clean(JSONObject source) throws Exception;

}
