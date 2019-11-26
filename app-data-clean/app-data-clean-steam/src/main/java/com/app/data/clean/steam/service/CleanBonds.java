package com.app.data.clean.steam.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.data.clean.steam.data.AbstractCleanData;
import org.springframework.stereotype.Service;

@Service
public class CleanBonds extends AbstractCleanData {


    @Override
    public String getKey() {
        return "bonds";
    }

    @Override
    public JSONObject cleanData(JSONObject jsonObject) throws Exception {
        return null;
    }

    @Override
    public JSONArray cleanArray(JSONArray jsonArray) throws Exception {
        //bonds--->bond_details
        JSONArray bonds = jsonArray;
        JSONArray bondsValue = new JSONArray();
        for (Object bd : bonds) {
            JSONObject bds = JSONObject.parseObject(bd.toString());
            JSONObject bdsj = JSONObject.parseObject(bds.getString("bond_details"));
            JSONObject newbdsj = new JSONObject();

            //id bond_id
            String id = bdsj.getString("id");
            newbdsj.put("bond_id", id);

            //bondName bond_name
            String bondName = bdsj.getString("bondName");
            newbdsj.put("bond_name", bondName);

            //bondNum bond_num
            String bondNum = bdsj.getString("bondNum");
            newbdsj.put("bond_num", bondNum);

            //publisherName bond_publisher_name
            String publisherName = bdsj.getString("publisherName");
            newbdsj.put("bond_publisher_name", publisherName);

            //bondType bond_type
            String bondType = bdsj.getString("bondType");
            newbdsj.put("bond_type", bondType);

            //publishTime bond_publish_time
            String publishTime = bdsj.getString("publishTime");
            newbdsj.put("bond_publish_time", getTime(publishTime));

            //publishExpireTime bond_publish_expire_time
            String publishExpireTime = bdsj.getString("publishExpireTime");
            newbdsj.put("bond_publish_expire_time", getTime(publishExpireTime));

            //bondTimeLimit bond_time_limit
            String bondTimeLimit = bdsj.getString("bondTimeLimit");
            newbdsj.put("bond_time_limit", bondTimeLimit);

            //bondTradeTime bond_trade_time
            String bondTradeTime = bdsj.getString("bondTradeTime");
            newbdsj.put("bond_trade_time", getTime(bondTradeTime));

            //calInterestType bond_cal_interest_type
            String calInterestType = bdsj.getString("calInterestType");
            newbdsj.put("bond_cal_interest_type", calInterestType);

            //bondStopTime bond_stop_time
            String bondStopTime = bdsj.getString("bondStopTime");
            newbdsj.put("bond_stop_time", getTime(bondStopTime));

            //creditRatingGov bond_credit_rating_gov
            String creditRatingGov = bdsj.getString("creditRatingGov");
            newbdsj.put("bond_credit_rating_gov", creditRatingGov);

            //debtRating bond_debt_rating
            String debtRating = bdsj.getString("debtRating");
            newbdsj.put("bond_debt_rating", debtRating);

            //faceValue bond_face_value
            String faceValue = bdsj.getString("faceValue");
            newbdsj.put("bond_face_value", faceValue);

            //refInterestRate bond_ref_interest_rate
            String refInterestRate = bdsj.getString("refInterestRate");
            newbdsj.put("bond_ref_interest_rate", refInterestRate);

            //faceInterestRate bond_face_interest_rate
            String faceInterestRate = bdsj.getString("faceInterestRate");
            newbdsj.put("bond_face_interest_rate", faceInterestRate);

            //realIssuedQuantity bond_real_issued_quantity
            String realIssuedQuantity = bdsj.getString("realIssuedQuantity");
            newbdsj.put("bond_real_issued_quantity", realIssuedQuantity);

            //planIssuedQuantity bond_plan_issued_quantity
            String planIssuedQuantity = bdsj.getString("planIssuedQuantity");
            newbdsj.put("bond_plan_issued_quantity", planIssuedQuantity);

            //issuedPrice bond_issued_price
            String issuedPrice = bdsj.getString("issuedPrice");
            newbdsj.put("bond_issued_price", issuedPrice);

            //interestDiff bond_interest_diff
            String interestDiff = bdsj.getString("interestDiff");
            newbdsj.put("bond_interest_diff", interestDiff);

            //payInterestHZ bond_pay_interest_hz
            String payInterestHZ = bdsj.getString("payInterestHZ");
            newbdsj.put("bond_pay_interest_hz", payInterestHZ);

            //startCalInterestTime bond_startd_interest_time
            String startCalInterestTime = bdsj.getString("startCalInterestTime");
            newbdsj.put("bond_startd_interest_time", getTime(startCalInterestTime));

            //exeRightType bond_exe_right_type
            String exeRightType = bdsj.getString("exeRightType");
            newbdsj.put("bond_exe_right_type", exeRightType);

            //exeRightTime bond_exe_right_time
            String exeRightTime = bdsj.getString("exeRightTime");
            newbdsj.put("bond_exe_right_time", getTime(exeRightTime));

            //escrowAgent bond_escrow_agent
            String escrowAgent = bdsj.getString("escrowAgent");
            newbdsj.put("bond_escrow_agent", escrowAgent);

            //flowRange bond_flow_range
            String flowRange = bdsj.getString("flowRange");
            newbdsj.put("bond_flow_range", flowRange);

            //remark bond_remark bond_remark
            String remark = bdsj.getString("remark");
            newbdsj.put("bond_remark", remark);

            //tip bond_tip
            String tip = bdsj.getString("tip");
            newbdsj.put("bond_tip", tip);

            //createTime bond_create_time
            String createTime = bdsj.getString("createTime");
            newbdsj.put("bond_create_time", getTime(createTime));

            //updateTime bond_update_time
            String updateTime = bdsj.getString("updateTime");
            newbdsj.put("bond_update_time", getTime(updateTime));

            //isDelete bond_is_delete
            String isDelete = bdsj.getString("isDelete");
            newbdsj.put("bond_is_delete", isDelete);

            bds.put("bond_details", newbdsj);
            bondsValue.add(bds);
        }
        return bondsValue;
    }
}
