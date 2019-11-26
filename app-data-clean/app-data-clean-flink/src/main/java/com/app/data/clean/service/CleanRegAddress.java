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


public class CleanRegAddress extends AbstractCleanData implements Serializable {

    private final static Logger logger = LoggerFactory.getLogger(CleanRegAddress.class);


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
        return "reg_address_detail";
    }

    @Override
    public Object cleanData(JSONObject jsonObject) throws Exception {
        JSONObject company_allj = this.source.getJSONObject("company_all");
        if (company_allj == null)
            return null;

        CompanyAll companyAll = company_allj.toJavaObject(CompanyAll.class);
        //经营地址
        AddressDetail regAddressDetail = jsonObject.getObject("reg_address_detail", AddressDetail.class);
        if (regAddressDetail != null && regAddressDetail.getCompLat() != null && regAddressDetail.getCompLng() != null) {
            regAddressDetail = geoToAddress.fromLocation(regAddressDetail);
        } else if (regAddressDetail != null && regAddressDetail.getLocation() != null) {
            regAddressDetail = geoToAddress.fromLocation(regAddressDetail);
        } else if (company_allj.containsKey("reg_address")) {
            String reg_address = company_allj.getString("reg_address");
//            String regAddress = company_allj.getString("reg_address");
            try {
                regAddressDetail = readSocialCreditCode(regAddressDetail, company_allj);
                if (regAddressDetail == null) {
                    //地址为空时,判断是否使用公司名称替代城市
                    String company_name = null;
                    if (reg_address.length() <= 15) {
                        company_name  = company_allj.getString("company_name");
                    }
                    regAddressDetail = geoToAddress.fromAddress(company_name, reg_address);
                } else {
                    regAddressDetail = geoToAddress.fromAddress(regAddressDetail.getCity_id(), reg_address);
                }
            } catch (Exception e) {
                logger.error("地址清洗失败", e);
                //SendMailUtil.send("zww@app-tech.com", "Clean Exception 地址清洗失败", e.getMessage());

            }
        }
        geoToAddress.fromIds(company_allj, companyAll, regAddressDetail);
//        jsonObject.put("company_all", companyAll);
        return regAddressDetail;
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
