/**
  * Copyright 2019 bejson.com 
  */
package com.app.data.clean.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Auto-generated: 2019-05-08 22:34:39
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Shareholders implements Serializable {

    private String shareDate;
    private String shareholder;
    private String shareLink;
    private String shareCompanyNum;
    private String shareRate;
    private String shareAmount;

    public String getShareDate() {
        return shareDate;
    }

    public void setShareDate(String shareDate) {
        this.shareDate = shareDate;
    }

    public String getShareholder() {
        return shareholder;
    }

    public void setShareholder(String shareholder) {
        this.shareholder = shareholder;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

    public String getShareCompanyNum() {
        return shareCompanyNum;
    }

    public void setShareCompanyNum(String shareCompanyNum) {
        this.shareCompanyNum = shareCompanyNum;
    }

    public String getShareRate() {
        return shareRate;
    }

    public void setShareRate(String shareRate) {
        this.shareRate = shareRate;
    }

    public String getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(String shareAmount) {
        this.shareAmount = shareAmount;
    }
}