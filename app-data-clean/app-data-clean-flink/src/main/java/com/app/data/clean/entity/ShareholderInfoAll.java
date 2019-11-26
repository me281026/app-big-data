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
public class ShareholderInfoAll {

    private String reportYear;
    private List<ReportValue> reportValue;


    public String getReportYear() {
        return reportYear;
    }

    public void setReportYear(String reportYear) {
        this.reportYear = reportYear;
    }

    public List<ReportValue> getReportValue() {
        return reportValue;
    }

    public void setReportValue(List<ReportValue> reportValue) {
        this.reportValue = reportValue;
    }
}