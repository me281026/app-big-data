/**
  * Copyright 2019 bejson.com 
  */
package com.app.data.clean.entity;

import lombok.Data;

import java.util.Date;

/**
 * Auto-generated: 2019-05-08 22:34:39
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Recruits {

    private String recruitWorkExp;
    private String recruitRegion;
    private Date recruitPubDate;
    private String recruitPay;
    private String recruitNum;
    private String recruitJob;

    public String getRecruitWorkExp() {
        return recruitWorkExp;
    }

    public void setRecruitWorkExp(String recruitWorkExp) {
        this.recruitWorkExp = recruitWorkExp;
    }

    public String getRecruitRegion() {
        return recruitRegion;
    }

    public void setRecruitRegion(String recruitRegion) {
        this.recruitRegion = recruitRegion;
    }

    public Date getRecruitPubDate() {
        return recruitPubDate;
    }

    public void setRecruitPubDate(Date recruitPubDate) {
        this.recruitPubDate = recruitPubDate;
    }

    public String getRecruitPay() {
        return recruitPay;
    }

    public void setRecruitPay(String recruitPay) {
        this.recruitPay = recruitPay;
    }

    public String getRecruitNum() {
        return recruitNum;
    }

    public void setRecruitNum(String recruitNum) {
        this.recruitNum = recruitNum;
    }

    public String getRecruitJob() {
        return recruitJob;
    }

    public void setRecruitJob(String recruitJob) {
        this.recruitJob = recruitJob;
    }
}