package com.app.data.clean.entity;

import java.io.Serializable;

public class KeyMan implements Serializable {

    private Long keymanCompanyNum;
    private String keymanImg;
    private String keymanName;
    private String keymanLink;
    private String keymanJob;
    //状态,0表示不需要操作,1表示insert,2表示update,3表示用户自定义不需要修改
    private Integer status = 0;

    private Long company_id;
    private String company_name;

    private String human_c_id;

    private String province_id;
    private String city_id;
    private String area_id;
    private String comp_lat;
    private String comp_lng;

    //股东为1,主要人员为2
    private String people_type;

    public String getPeople_type() {
        return people_type;
    }

    public void setPeople_type(String people_type) {
        this.people_type = people_type;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getComp_lat() {
        return comp_lat;
    }

    public void setComp_lat(String comp_lat) {
        this.comp_lat = comp_lat;
    }

    public String getComp_lng() {
        return comp_lng;
    }

    public void setComp_lng(String comp_lng) {
        this.comp_lng = comp_lng;
    }

    public String getHuman_c_id() {
        return human_c_id;
    }

    public void setHuman_c_id(String human_c_id) {
        this.human_c_id = human_c_id;
    }

    public Long getKeymanCompanyNum() {
        return keymanCompanyNum;
    }

    public void setKeymanCompanyNum(Long keymanCompanyNum) {
        this.keymanCompanyNum = keymanCompanyNum;
    }

    public String getKeymanImg() {
        return keymanImg;
    }

    public void setKeymanImg(String keymanImg) {
        this.keymanImg = keymanImg;
    }

    public String getKeymanName() {
        return keymanName;
    }

    public void setKeymanName(String keymanName) {
        this.keymanName = keymanName;
    }

    public String getKeymanLink() {
        return keymanLink;
    }

    public void setKeymanLink(String keymanLink) {
        this.keymanLink = keymanLink;
    }

    public String getKeymanJob() {
        return keymanJob;
    }

    public void setKeymanJob(String keymanJob) {
        this.keymanJob = keymanJob;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }
}
