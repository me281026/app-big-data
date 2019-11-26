/**
 * Copyright 2019 bejson.com
 */
package com.app.data.clean.entity;


import lombok.Data;

/**
 * Auto-generated: 2019-05-08 22:34:39
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Businesses {

    private String businessName;
    private String businessType;
    private String businessDesc;
    private String businessImageSrc;


    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessDesc() {
        return businessDesc;
    }

    public void setBusinessDesc(String businessDesc) {
        this.businessDesc = businessDesc;
    }

    public String getBusinessImageSrc() {
        return businessImageSrc;
    }

    public void setBusinessImageSrc(String businessImageSrc) {
        this.businessImageSrc = businessImageSrc;
    }
}