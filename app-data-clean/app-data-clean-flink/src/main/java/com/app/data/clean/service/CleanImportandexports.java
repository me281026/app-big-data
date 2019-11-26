package com.app.data.clean.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.data.clean.data.AbstractCleanData;

import java.io.Serializable;


public class CleanImportandexports extends AbstractCleanData implements Serializable {

    @Override
    public String getKey() {
        return "importandexports";
    }

    @Override
    public JSONObject cleanData(JSONObject jsonObject) throws Exception {
        return null;
    }

    @Override
    public JSONArray cleanArray(JSONArray jsonArray) throws Exception {
        JSONArray importandexports = jsonArray;
        JSONArray importandexportsValue = new JSONArray();
        for (Object iae : importandexports) {
            JSONObject iaej = JSONObject.parseObject(iae.toString());
            JSONObject credit_business_datails = JSONObject.parseObject(iaej.getString("credit_business_datails"));

            String sanction = credit_business_datails.getString("sanction");
            iaej.put("sanction", sanction);

            JSONArray cra = JSONArray.parseArray(credit_business_datails.getString("creditRating"));
            for (Object x : cra) {
                JSONObject x1 = JSONObject.parseObject(x.toString());

                //creditRating credit_rating
                String creditRating = x1.getString("creditRating");
                iaej.put("credit_rating", creditRating);

                //authenticationCode
                String authenticationCode = x1.getString("authenticationCode");
                iaej.put("credit_auther_code", authenticationCode);
            }

            String baseInfo = credit_business_datails.getString("baseInfo");
            JSONObject bij = JSONObject.parseObject(baseInfo);

            JSONObject j3 = new JSONObject();

            //industryCategory industry_category
            String industryCategory = bij.getString("industryCategory");
            iaej.put("industry_category", industryCategory);

            //validityDate credit_validity_date
            String validityDate = bij.getString("validityDate");
            iaej.put("credit_validity_date", validityDate);

            //annualReport credit_annual_report
            String annualReport = bij.getString("annualReport");
            iaej.put("credit_annual_report", annualReport);

            //economicDivision economic_division
            String economicDivision = bij.getString("economicDivision");
            iaej.put("economic_division", economicDivision);

            //status credit_status
            String status = bij.getString("status");
            iaej.put("credit_status", status);

            //recordDate credit_record_date
            String recordDate = bij.getString("recordDate");
            iaej.put("credit_record_date", recordDate);

            //managementCategory management_category
            String managementCategory = bij.getString("managementCategory");
            iaej.put("management_category", managementCategory);

            //administrativeDivision administrative_division
            String administrativeDivision = bij.getString("administrativeDivision");
            iaej.put("administrative_division", administrativeDivision);

            //crCode cr_code
            String crCode = bij.getString("crCode");
            iaej.put("cr_code", crCode);

            //specialTradeArea special_trade_area
            String specialTradeArea = bij.getString("specialTradeArea");
            iaej.put("special_trade_area", specialTradeArea);

            //customsRegisteredAddress customs_registered_address
            String customsRegisteredAddress = bij.getString("customsRegisteredAddress");
            iaej.put("customs_registered_address", customsRegisteredAddress);

            //types trade_types
            String types = bij.getString("types");
            iaej.put("trade_types", types);

            iaej.put("credit_business_datails", null);
            importandexportsValue.add(iaej);
        }
        return importandexportsValue;
    }
}
