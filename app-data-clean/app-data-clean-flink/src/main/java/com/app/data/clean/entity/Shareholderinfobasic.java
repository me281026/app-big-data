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
public class Shareholderinfobasic {

    private String reportSocialCreditCode;
    private String reportRegStatus;
    private String reportCompanyTel;
    private String reportAddress;
    private String reportPeopleNums;
    private String reportCompanyEmail;
    private String ifnotchangeshares;
    private String investmentInf;


    public String getReportSocialCreditCode() {
        return reportSocialCreditCode;
    }

    public void setReportSocialCreditCode(String reportSocialCreditCode) {
        this.reportSocialCreditCode = reportSocialCreditCode;
    }

    public String getReportRegStatus() {
        return reportRegStatus;
    }

    public void setReportRegStatus(String reportRegStatus) {
        this.reportRegStatus = reportRegStatus;
    }

    public String getReportCompanyTel() {
        return reportCompanyTel;
    }

    public void setReportCompanyTel(String reportCompanyTel) {
        this.reportCompanyTel = reportCompanyTel;
    }

    public String getReportAddress() {
        return reportAddress;
    }

    public void setReportAddress(String reportAddress) {
        this.reportAddress = reportAddress;
    }

    public String getReportPeopleNums() {
        return reportPeopleNums;
    }

    public void setReportPeopleNums(String reportPeopleNums) {
        this.reportPeopleNums = reportPeopleNums;
    }

    public String getReportCompanyEmail() {
        return reportCompanyEmail;
    }

    public void setReportCompanyEmail(String reportCompanyEmail) {
        this.reportCompanyEmail = reportCompanyEmail;
    }

    public String getIfnotchangeshares() {
        return ifnotchangeshares;
    }

    public void setIfnotchangeshares(String ifnotchangeshares) {
        this.ifnotchangeshares = ifnotchangeshares;
    }

    public String getInvestmentInf() {
        return investmentInf;
    }

    public void setInvestmentInf(String investmentInf) {
        this.investmentInf = investmentInf;
    }
}