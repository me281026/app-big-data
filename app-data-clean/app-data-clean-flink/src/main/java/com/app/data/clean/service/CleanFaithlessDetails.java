package com.app.data.clean.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.data.clean.data.AbstractCleanData;

import java.io.Serializable;

public class CleanFaithlessDetails extends AbstractCleanData implements Serializable {



    @Override
    public String getKey() {
        return "faithless_details";
    }

    @Override
    public JSONObject cleanData(JSONObject jsonObject) throws Exception {
        return null;
    }

    @Override
    public JSONArray cleanArray(JSONArray jsonArray) throws Exception {
        JSONArray faithless_details = jsonArray;
        JSONArray faithless_detailsValue = new JSONArray();
        for (Object fd : faithless_details) {
            JSONObject fdj = JSONObject.parseObject(fd.toString());
            JSONObject newfdj = new JSONObject();

            //iname faithless_name
            String iname = fdj.getString("iname");
            newfdj.put("faithless_name", iname);

            //businessentity faithless_legal_person
            String businessentity = fdj.getString("businessentity");
            newfdj.put("faithless_legal_person", businessentity);

            //gistid gist_id
            String gistid = fdj.getString("gistid");
            newfdj.put("gist_id", gistid);

            //areaname area_name
            String areaname = fdj.getString("areaname");
            newfdj.put("area_name", areaname);

            //cardnum card_num
            String cardnum = fdj.getString("cardnum");
            newfdj.put("card_num", cardnum);

            //courtname court_name
            String courtname = fdj.getString("courtname");
            newfdj.put("court_name", courtname);

            //publishdate faithless_publish_date
            String publishdate = fdj.getString("publishdate");
            newfdj.put("faithless_publish_date", getTime(publishdate));

            //type faithless_type
            String type = fdj.getString("type");
            newfdj.put("faithless_type", type);

            //gistunit gist_unit
            String gistunit = fdj.getString("gistunit");
            newfdj.put("gist_unit", gistunit);

            //duty faithless_duty
            String duty = fdj.getString("duty");
            newfdj.put("faithless_duty", duty);

            //performance faithless_status
            String performance = fdj.getString("performance");
            newfdj.put("faithless_status", performance);

            //regdate faithless_reg_date
            String regdate = fdj.getString("regdate");
            newfdj.put("faithless_reg_date", getTime(regdate));


            //casecode faithless_case_code
            String casecode = fdj.getString("casecode");
            newfdj.put("faithless_case_code", casecode);

            //disrupttypename disrupt_type_name
            String disrupttypename = fdj.getString("disrupttypename");
            newfdj.put("disrupt_type_name", disrupttypename);

            faithless_detailsValue.add(newfdj);
        }
        return faithless_detailsValue;
    }
}
