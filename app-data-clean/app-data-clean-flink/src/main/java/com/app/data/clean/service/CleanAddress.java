package com.app.data.clean.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.data.clean.data.AbstractCleanData;
import com.app.data.clean.entity.AddressDetail;
import com.app.data.clean.entity.CompanyAll;
import jodd.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CleanAddress extends AbstractCleanData implements Serializable {

    private final static Logger logger = LoggerFactory.getLogger(CleanAddress.class);


    private GeoToAddress geoToAddress = new GeoToAddress();

    private Properties adcodes = new Properties();

    @PostConstruct
    public void init() {
        try {
            adcodes = PropertiesLoaderUtils.loadProperties(new ClassPathResource("adcode.properties"));
        } catch (IOException e) {
            logger.error("读取城市清单错误", e);
        }
    }


    @Override
    public String getKey() {
        return "address_detail";
    }

    @Override
    public Object cleanData(JSONObject jsonObject) throws Exception {
        JSONObject company_allj = this.source.getJSONObject("company_all");
        if (company_allj == null)
            return null;

        //货币类型判断
        if (!company_allj.containsKey("reg_capital_type") || company_allj.getString("reg_capital_type") == "-" || company_allj.getString("reg_capital_type") == "") {
            putDefaultValue(company_allj, "reg_capital_type", "人民币");
        } else {
            String reg_capital_type = company_allj.getString("reg_capital_type");
            if (reg_capital_type.contains("美元")) {
                putDefaultValue(company_allj, "reg_capital_type", "美元");
            } else if (reg_capital_type.contains("人民币")) {
                putDefaultValue(company_allj, "reg_capital_type", "人民币");
            } else if (reg_capital_type.contains("港元")) {
                putDefaultValue(company_allj, "reg_capital_type", "港元");
            }

        }

        //putDefaultValue(company_allj, "reg_capital_type", "人民币");

        CompanyAll companyAll = company_allj.toJavaObject(CompanyAll.class);
        //经营地址
        AddressDetail addressDetail = jsonObject.getObject("address_detail", AddressDetail.class);
        if (addressDetail != null && addressDetail.getCompLat() != null && addressDetail.getCompLng() != null) {
            addressDetail = geoToAddress.fromLocation(addressDetail);
        } else if (addressDetail != null && addressDetail.getLocation() != null) {
            addressDetail = geoToAddress.fromLocation(addressDetail);
        } else if (company_allj.containsKey("address")) {
            String address = company_allj.getString("address");
//            String regAddress = company_allj.getString("reg_address");
            try {
                addressDetail = readSocialCreditCode(addressDetail, company_allj);
                if (addressDetail == null) {
                    //地址为空时,判断是否使用公司名称替代城市
                    String company_name = null;
                    if (address.length() <= 15) {
                        company_name  = company_allj.getString("company_name");
                    }
                    addressDetail = geoToAddress.fromAddress(company_name, address);
                } else {
                    addressDetail = geoToAddress.fromAddress(addressDetail.getCity_id(), address);
                }
            } catch (Exception e) {
                logger.error("地址清洗失败", e);
                //SendMailUtil.send("zww@app-tech.com", "Clean Exception 地址清洗失败", e.getMessage());

            }
        } /*else if (company_allj.containsKey("reg_address")) {
            //当包含reg_address的时候就去获取注册地址信息
            String reg_address = company_allj.getString("reg_address");


        }*/
        geoToAddress.fromIds(company_allj, companyAll, addressDetail);
//        jsonObject.put("company_all", companyAll);
        return addressDetail;
    }

    private AddressDetail readSocialCreditCode(AddressDetail addressDetail, JSONObject company_allj) {
        //统一信用代码解析
        String social_credit_code = company_allj.getString("social_credit_code");
        String registration_code = company_allj.getString("registration_code");
        String adcode = null;
        if (StringUtil.isNotBlank(social_credit_code)) {
            if (social_credit_code.length() > 8)
                adcode = StringUtil.substring(social_credit_code, 2, 8);
        }
        if (StringUtil.isNotBlank(registration_code)) {
            if (registration_code.length() > 6)
                adcode = StringUtil.substring(registration_code, 0, 6);
        }
        if (StringUtil.isBlank(adcode)) {
            return addressDetail;
        }
        if (addressDetail == null)
            addressDetail = new AddressDetail();
        if (StringUtil.isNotBlank(adcode))
            addressDetail.setArea_id(adcode);
        String citycode = adcodes.getProperty(adcode, null);
        if (StringUtil.isNotBlank(citycode))
            addressDetail.setCity_id(citycode);
        return addressDetail;
    }

    public static void main(String[] args) {
        System.out.println(StringUtil.substring("310117003628127", 0, 6));
    }


    /**
     * 解析地址
     *
     * @param address
     * @return
     * @author lin
     */
    public static AddressDetail addressResolution(AddressDetail addressDetail, String address) {
        String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m = Pattern.compile(regex).matcher(address);
        String province = null, city = null, county = null, town = null, village = null;
        if (addressDetail == null)
            addressDetail = new AddressDetail();
        while (m.find()) {
            province = m.group("province");
            addressDetail.setProvince(province == null ? "" : province.trim());
            city = m.group("city");
            addressDetail.setCity(city == null ? "" : city.trim());
            county = m.group("county");
            addressDetail.setCountry(county == null ? "" : county.trim());
            town = m.group("town");
            addressDetail.setTownship(town == null ? "" : town.trim());
        }
        return addressDetail;
    }


    @Override
    public Object cleanArray(JSONArray jsonArray) throws Exception {
        return null;
    }


}
