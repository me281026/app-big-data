package com.app.data.clean.steam.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.data.clean.steam.data.AbstractCleanData;
import org.springframework.stereotype.Service;

@Service
public class CleanPunishcreditchina extends AbstractCleanData {


    @Override
    public String getKey() {
        return "punishcreditchina";
    }

    @Override
    public JSONObject cleanData(JSONObject jsonObject) throws Exception {
        return null;
    }

    @Override
    public JSONArray cleanArray(JSONArray jsonArray) throws Exception {
        //punishcreditchina
        JSONArray punishcreditchina = jsonArray;
        JSONArray punishcreditchinaValue = new JSONArray();
        for (Object pd : punishcreditchina) {
            JSONObject pdj = JSONObject.parseObject(pd.toString());
            JSONObject newpdj = new JSONObject();

            //result pun_credit_result
            String result = pdj.getString("result");
            newpdj.put("pun_credit_result", result);

            //areaName pun_credit_area
            String areaName = pdj.getString("areaName");
            newpdj.put("pun_credit_area", areaName);

            //typeSecond pun_credit_type2
            String typeSecond = pdj.getString("typeSecond");
            newpdj.put("pun_credit_type2", typeSecond);

            //reason pun_credit_reason
            String reason = pdj.getString("reason");
            newpdj.put("pun_credit_reason", reason);

            //punishNumber pun_credit_code
            String punishNumber = pdj.getString("punishNumber");
            newpdj.put("pun_credit_code", punishNumber);

            //evidence pun_credit_evidence
            String evidence = pdj.getString("evidence");
            newpdj.put("pun_credit_evidence", evidence);

            //punishStatus pun_credit_status
            String punishStatus = pdj.getString("punishStatus");
            newpdj.put("pun_credit_status", punishStatus);

            //decisionDate pun_credit_date
            String decisionDate = pdj.getString("decisionDate");
            newpdj.put("pun_credit_date", decisionDate);

            //type pun_credit_type
            String type = pdj.getString("type");
            newpdj.put("pun_credit_type", type);

            //departmentName pun_credit_operater
            String departmentName = pdj.getString("departmentName");
            newpdj.put("pun_credit_operater", departmentName);

            //punishName
            String punishName = pdj.getString("punishName");
            newpdj.put("pun_credit_name", punishName);

            punishcreditchinaValue.add(newpdj);
        }
        return punishcreditchinaValue;
    }
}
