package com.app.data.clean.steam.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.data.clean.steam.data.AbstractCleanData;
import org.springframework.stereotype.Service;

@Service
public class CleanLandPurchase extends AbstractCleanData {

    @Override
    public String getKey() {
        return "land_purchase";
    }


    @Override
    public JSONObject cleanData(JSONObject jsonObject) throws Exception {
        return null;
    }

    @Override
    public JSONArray cleanArray(JSONArray jsonArray) throws Exception {
        //land_purchase--->buy_land_details
        JSONArray land_purchase = jsonArray;
        JSONArray land_purchaseValue = new JSONArray();
        for (Object lp : land_purchase) {
            JSONObject lps = JSONObject.parseObject(lp.toString());
            JSONObject lpsj = JSONObject.parseObject(lps.getString("buy_land_details"));
            JSONObject newlpsj = new JSONObject();

            //id land_id
            String id = lpsj.getString("id");
            newlpsj.put("land_id", id);

            //adminRegion land_admin_region
            String adminRegion = lpsj.getString("adminRegion");
            newlpsj.put("land_admin_region", adminRegion);

            //elecSupervisorNo land_elec_supervisor_no
            String elecSupervisorNo = lpsj.getString("elecSupervisorNo");
            newlpsj.put("land_elec_supervisor_no", elecSupervisorNo);

            //signedDate land_signed_date
            String signedDate = lpsj.getString("signedDate");
            newlpsj.put("land_signed_date", signedDate);

            //totalArea land_total_area
            String totalArea = lpsj.getString("totalArea");
            newlpsj.put("land_total_area", totalArea);

            //location land_location
            String location = lpsj.getString("location");
            newlpsj.put("land_location", location);

            //assignee land_assignee
            String assignee = lpsj.getString("assignee");
            newlpsj.put("land_assignee", assignee);

            //parentCompany land_parent_company
            String parentCompany = lpsj.getString("parentCompany");
            newlpsj.put("land_parent_company", parentCompany);

            //purpose land_purpose
            String purpose = lpsj.getString("purpose");
            newlpsj.put("land_purpose", purpose);

            //supplyWay land_supply_way
            String supplyWay = lpsj.getString("supplyWay");
            newlpsj.put("land_supply_way", supplyWay);

            //minVolume land_min_volume
            String minVolume = lpsj.getString("minVolume");
            newlpsj.put("land_min_volume", minVolume);

            //maxVolume land_max_volume
            String maxVolume = lpsj.getString("maxVolume");
            newlpsj.put("land_max_volume", maxVolume);

            //dealPrice land_deal_price
            String dealPrice = lpsj.getString("dealPrice");
            newlpsj.put("land_deal_price", dealPrice);

            //startTime land_start_time
            String startTime = lpsj.getString("startTime");
            newlpsj.put("land_start_time", startTime);

            //linkUrl land_link_url
            String linkUrl = lpsj.getString("linkUrl");
            newlpsj.put("land_link_url", linkUrl);

            //endTime land_end_time
            String endTime = lpsj.getString("endTime");
            newlpsj.put("land_end_time", endTime);

            //createTime land_create_time
            String createTime = lpsj.getString("createTime");
            newlpsj.put("land_create_time", createTime);

            //updateTime land_update_time
            String updateTime = lpsj.getString("updateTime");
            newlpsj.put("land_update_time", updateTime);

            lps.put("buy_land_details", newlpsj);
            land_purchaseValue.add(lps);
        }
        return land_purchaseValue;
    }

}
