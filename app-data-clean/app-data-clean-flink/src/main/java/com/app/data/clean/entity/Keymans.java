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
public class Keymans {

    private String keymanCompanyNum;
    private String keymanImg;
    private String keymanName;
    private String keymanLink;
    private String keymanJob;

    public Keymans() {

    }

    public Keymans(String keymanCompanyNum, String keymanImg, String keymanName, String keymanLink, String keymanJob) {
        this.keymanCompanyNum = keymanCompanyNum;
        this.keymanImg = keymanImg;
        this.keymanName = keymanName;
        this.keymanLink = keymanLink;
        this.keymanJob = keymanJob;
    }

    public String getKeymanCompanyNum() {
        return keymanCompanyNum;
    }

    public void setKeymanCompanyNum(String keymanCompanyNum) {
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
}