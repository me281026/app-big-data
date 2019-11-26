/**
  * Copyright 2019 bejson.com 
  */
package com.app.data.clean.entity;

import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2019-05-08 22:34:39
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Trademarks {

    private String brandAppDate;
    private List<BrandProductList> brandProductList;
    private String brandName;
    private String brandImageSrc;
    private String brandAppAddress;
    private String brandFirstTrialDate;
    private String brandAgency;
    private String brandFirstTrialNo;
    private String brandExpireTime;
    private String brandRegNoticode;
    private String brandType;
    private String brandRegCode;
    private String brandRegDate;
    private String brandApplicant;
    private String brandAppStatus;


    public String getBrandAppDate() {
        return brandAppDate;
    }

    public void setBrandAppDate(String brandAppDate) {
        this.brandAppDate = brandAppDate;
    }

    public List<BrandProductList> getBrandProductList() {
        return brandProductList;
    }

    public void setBrandProductList(List<BrandProductList> brandProductList) {
        this.brandProductList = brandProductList;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandImageSrc() {
        return brandImageSrc;
    }

    public void setBrandImageSrc(String brandImageSrc) {
        this.brandImageSrc = brandImageSrc;
    }

    public String getBrandAppAddress() {
        return brandAppAddress;
    }

    public void setBrandAppAddress(String brandAppAddress) {
        this.brandAppAddress = brandAppAddress;
    }

    public String getBrandFirstTrialDate() {
        return brandFirstTrialDate;
    }

    public void setBrandFirstTrialDate(String brandFirstTrialDate) {
        this.brandFirstTrialDate = brandFirstTrialDate;
    }

    public String getBrandAgency() {
        return brandAgency;
    }

    public void setBrandAgency(String brandAgency) {
        this.brandAgency = brandAgency;
    }

    public String getBrandFirstTrialNo() {
        return brandFirstTrialNo;
    }

    public void setBrandFirstTrialNo(String brandFirstTrialNo) {
        this.brandFirstTrialNo = brandFirstTrialNo;
    }

    public String getBrandExpireTime() {
        return brandExpireTime;
    }

    public void setBrandExpireTime(String brandExpireTime) {
        this.brandExpireTime = brandExpireTime;
    }

    public String getBrandRegNoticode() {
        return brandRegNoticode;
    }

    public void setBrandRegNoticode(String brandRegNoticode) {
        this.brandRegNoticode = brandRegNoticode;
    }

    public String getBrandType() {
        return brandType;
    }

    public void setBrandType(String brandType) {
        this.brandType = brandType;
    }

    public String getBrandRegCode() {
        return brandRegCode;
    }

    public void setBrandRegCode(String brandRegCode) {
        this.brandRegCode = brandRegCode;
    }

    public String getBrandRegDate() {
        return brandRegDate;
    }

    public void setBrandRegDate(String brandRegDate) {
        this.brandRegDate = brandRegDate;
    }

    public String getBrandApplicant() {
        return brandApplicant;
    }

    public void setBrandApplicant(String brandApplicant) {
        this.brandApplicant = brandApplicant;
    }

    public String getBrandAppStatus() {
        return brandAppStatus;
    }

    public void setBrandAppStatus(String brandAppStatus) {
        this.brandAppStatus = brandAppStatus;
    }
}