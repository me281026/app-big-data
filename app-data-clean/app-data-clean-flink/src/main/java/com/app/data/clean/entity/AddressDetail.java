package com.app.data.clean.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDetail implements Serializable {

    private String province;
    private String city;
    private String district;
    private String country;
    private String area_id;
    private String city_id;
    private String province_id;
    private String compLng;
    private String compLat;
    private String towncode;
    private String ctiycode;
    private String township;
    /**
     * 坐标类型
     */
    private String coordsys;
    /**
     * 坐标
     */
    private String location;


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getCompLng() {
        return compLng;
    }

    public void setCompLng(String compLng) {
        this.compLng = compLng;
    }

    public String getCompLat() {
        return compLat;
    }

    public void setCompLat(String compLat) {
        this.compLat = compLat;
    }

    public String getTowncode() {
        return towncode;
    }

    public void setTowncode(String towncode) {
        this.towncode = towncode;
    }

    public String getCtiycode() {
        return ctiycode;
    }

    public void setCtiycode(String ctiycode) {
        this.ctiycode = ctiycode;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public String getCoordsys() {
        return coordsys;
    }

    public void setCoordsys(String coordsys) {
        this.coordsys = coordsys;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
