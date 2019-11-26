package com.app.data.clean.core.entity;

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

}
