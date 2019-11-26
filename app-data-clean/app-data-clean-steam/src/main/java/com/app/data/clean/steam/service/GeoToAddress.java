package com.app.data.clean.steam.service;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.data.clean.core.entity.AddressDetail;
import com.app.data.clean.core.entity.CompanyAll;
import jodd.http.HttpRequest;
import jodd.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeoToAddress {

    private final static Logger logger = LoggerFactory.getLogger(GeoToAddress.class);

    @Value("${data.api.address}")
    private String addressUrl;

    @Value("${data.api.findRegeoByLocation}")
    private String findRegeoByLocationUrl;

    @Value("${data.api.getCompanyInfoIds}")
    private String getCompanyInfoIdsUrl;

    private void putLatLong(AddressDetail detail, String location) {
        if (location.contains(",")) {
            String[] split = location.split(",");
            if (split.length == 2) {
                detail.setCompLng(split[0]);
                detail.setCompLat(split[1]);
            }
        }
    }

    private double[] parseLatLong(String location) {
        String[] split = location.split(",");
        double[] ret = new double[2];
        if (split.length == 2) {
            ret[0] = Double.parseDouble(split[0]);
            ret[1] = Double.parseDouble(split[1]);
        }
        return ret;
    }


    /**
     * 通过高德的地址信息补充资料
     *
     * @param address
     * @return
     */
    public AddressDetail fromAddress(String city, String address) {
        if (StringUtil.isBlank(address))
            return null;
        if (address.length() < 5)
            return null;
        HttpRequest httpRequest = HttpRequest.get(addressUrl).query("address", address);
        if (StringUtil.isNotBlank(city)) {
            httpRequest.query("city", city);
        }
        JSONObject jsonObject = JSONObject.parseObject(httpRequest.send().bodyText());
        AddressDetail rejo = new AddressDetail();
        if ("1".equals(jsonObject.get("status"))) {
            JSONArray jsonArray = jsonObject.getJSONArray("geocodes");
            try {
                JSONObject geocode = jsonArray.getJSONObject(0);
                if (geocode != null) {
                    rejo = geocode.toJavaObject(AddressDetail.class);
//                    rejo.setProvince(geocode.getString("province"));
//                    rejo.setCity(geocode.getString("city"));
//                    rejo.setCounty(geocode.getString("county"));
//                    rejo.setDistrict(geocode.getString("district"));
                    rejo.setArea_id(geocode.getString("adcode"));
                    putLatLong(rejo, geocode.getString("location"));
                }
            } catch (Exception e) {
                logger.error("根据地址信息清洗地址错误:{}", address, e);
            }
        }
        return rejo;
    }


    public AddressDetail fromLocation(AddressDetail addressDetail) {
        if (addressDetail == null)
            return new AddressDetail();
        if ("baidu".equalsIgnoreCase(addressDetail.getCoordsys())) {
            double[] ll = this.parseLatLong(addressDetail.getLocation());
            ll = com.app.data.clean.steam.service.LatLngConvertor.baidu2AMap(ll[0], ll[1]);
            addressDetail.setCompLng(ll[0] + "");
            addressDetail.setCompLat(ll[1] + "");
            addressDetail.setLocation(cutLngLat(addressDetail.getCompLng(), addressDetail.getCompLat()));
        }
        if (StringUtil.isBlank(addressDetail.getLocation())) {
            if (StringUtil.isAllBlank(addressDetail.getCompLng(), addressDetail.getCompLat()))
                addressDetail.setLocation(cutLngLat(addressDetail.getCompLng(), addressDetail.getCompLat()));
        }
        return fromLocation(addressDetail, addressDetail.getLocation());
    }

    private String cutLngLat(String lng, String lat) {
        return lng + "," + lat;
    }


    private AddressDetail fromLocation(AddressDetail rejo, String location) {
        HttpRequest httpRequest = HttpRequest.get(findRegeoByLocationUrl).query("location", location);
        JSONObject jsonObject = JSONObject.parseObject(httpRequest.send().bodyText());
        rejo.setCoordsys("amap");
        if ("1".equals(jsonObject.get("status"))) {
            try {
                JSONObject geocode = jsonObject.getJSONObject("regeocode");
                JSONObject addressComponent = geocode.getJSONObject("addressComponent");
                if (addressComponent != null) {
                    JSONObject obj = JSONObject.parseObject(JSON.toJSONString(rejo));
                    obj.putAll(addressComponent);
                    rejo = obj.toJavaObject(AddressDetail.class);
                    rejo.setArea_id(addressComponent.getString("adcode"));
                    putLatLong(rejo, location);
//                    JSONArray businessAreas = addressComponent.getJSONArray("businessAreas");
//                    if (businessAreas != null && businessAreas.size() > 0) {
//                        rejo.setArea_id(businessAreas.getJSONObject(0).getString("id"));
//                    }
                }

            } catch (Exception e) {
                logger.error("根据经纬度清洗地址错误", e);
            }
        }
        return rejo;
    }


    public void fromIds(JSONObject companyAllJsonObject, CompanyAll companyAll, AddressDetail addressDetail) {
        try {
            HttpRequest httpRequest = HttpRequest.post(getCompanyInfoIdsUrl);
            httpRequest.form("reg_status", companyAll.getReg_status());
            httpRequest.form("industry", companyAll.getIndustry());
            httpRequest.form("reg_capital_type", companyAll.getReg_capital_type());
            if (addressDetail != null)
                httpRequest.form("area_id", addressDetail.getArea_id());
            final String body = httpRequest.timeout(3000).send().body();
            final JSONObject result = JSONObject.parseObject(body);
            JSONObject zyhjson = result.getJSONObject("result");
            if (!zyhjson.containsKey("industry_id")) {
                return;
            }
            companyAllJsonObject.put("industry_id", zyhjson.getString("industry_id"));
            companyAllJsonObject.put("capital_type_id", zyhjson.getString("capital_type_id"));
            companyAllJsonObject.put("regist_status_id", zyhjson.getString("regist_status_id"));
            addressDetail.setProvince_id(zyhjson.getString("province_id"));
            addressDetail.setCity_id(zyhjson.getString("city_id"));
        } catch (Exception e) {
            logger.error("获取行业信息错误", e);
        }
    }

//    public static void main(String[] args) {
//        JSONObject sub = new JSONObject();
//        sub.put("ab", "sub");
//        JSONObject sub2 = new JSONObject();
//        sub2.put("ab2", "sub2");
//        JSONObject b1 = new JSONObject();
//        b1.put("test1", "1");
//        b1.put("test2", "2");
//        b1.put("sub", sub);
//        JSONObject b2 = new JSONObject();
////        b2.put("test1", "b1");
//        b2.put("test2", "b2");
//        b2.put("test3", "b3");
//        b2.put("sub", sub2);
//        b1.fluentPutAll(b2);
//        System.out.println(b1.toJSONString());
//    }
}
