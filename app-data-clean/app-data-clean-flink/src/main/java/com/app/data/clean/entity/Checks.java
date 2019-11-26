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
public class Checks {

    private String checkType;
    private String checkOperater;
    private String checkDate;
    private String checkResult;

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCheckOperater() {
        return checkOperater;
    }

    public void setCheckOperater(String checkOperater) {
        this.checkOperater = checkOperater;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }
}