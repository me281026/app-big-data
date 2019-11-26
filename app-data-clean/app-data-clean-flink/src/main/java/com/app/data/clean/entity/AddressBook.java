package com.app.data.clean.entity;

import java.util.Date;

public class AddressBook {

    private Long id;
    private Long user_id;
    private String human_id;
    private String user_name;
    private String mobile;
    private String job_title;
    private String status;
    private String company_id;
    private String company_name;
    private Integer industry_id;
    private Date created_time;

    public AddressBook() {
    }

    public AddressBook(Long id, Long user_id, String human_id, String user_name, String mobile, String job_title, String status, String company_id, String company_name, Integer industry_id, Date created_time) {
        this.id = id;
        this.user_id = user_id;
        this.human_id = human_id;
        this.user_name = user_name;
        this.mobile = mobile;
        this.job_title = job_title;
        this.status = status;
        this.company_id = company_id;
        this.company_name = company_name;
        this.industry_id = industry_id;
        this.created_time = created_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getHuman_id() {
        return human_id;
    }

    public void setHuman_id(String human_id) {
        this.human_id = human_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Integer getIndustry_id() {
        return industry_id;
    }

    public void setIndustry_id(Integer industry_id) {
        this.industry_id = industry_id;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }
}
