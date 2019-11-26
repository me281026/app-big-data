/**
 * Copyright 2019 bejson.com
 */
package com.app.data.clean.entity;


import lombok.Data;

import java.util.Date;

@Data
public class Sign {

    private String crawler_id;
    private String host_ip;
    private String agent_ip;
    private String crawler_type;
    private String crawler_source;
    private String crawler_name;
    private String crawler_web;
    private String crawler_version;
    private String words_version;
    private Integer data_level = 1;
    private Date start_clean_time;
    private Date end_clean_time;
    private Long clean_cost_time;
    private String bigData;

    public String getCrawler_id() {
        return crawler_id;
    }

    public void setCrawler_id(String crawler_id) {
        this.crawler_id = crawler_id;
    }

    public String getHost_ip() {
        return host_ip;
    }

    public void setHost_ip(String host_ip) {
        this.host_ip = host_ip;
    }

    public String getAgent_ip() {
        return agent_ip;
    }

    public void setAgent_ip(String agent_ip) {
        this.agent_ip = agent_ip;
    }

    public String getCrawler_type() {
        return crawler_type;
    }

    public void setCrawler_type(String crawler_type) {
        this.crawler_type = crawler_type;
    }

    public String getCrawler_source() {
        return crawler_source;
    }

    public void setCrawler_source(String crawler_source) {
        this.crawler_source = crawler_source;
    }

    public String getCrawler_name() {
        return crawler_name;
    }

    public void setCrawler_name(String crawler_name) {
        this.crawler_name = crawler_name;
    }

    public String getCrawler_web() {
        return crawler_web;
    }

    public void setCrawler_web(String crawler_web) {
        this.crawler_web = crawler_web;
    }

    public String getCrawler_version() {
        return crawler_version;
    }

    public void setCrawler_version(String crawler_version) {
        this.crawler_version = crawler_version;
    }

    public String getWords_version() {
        return words_version;
    }

    public void setWords_version(String words_version) {
        this.words_version = words_version;
    }

    public Integer getData_level() {
        return data_level;
    }

    public void setData_level(Integer data_level) {
        this.data_level = data_level;
    }

    public Date getStart_clean_time() {
        return start_clean_time;
    }

    public void setStart_clean_time(Date start_clean_time) {
        this.start_clean_time = start_clean_time;
    }

    public Date getEnd_clean_time() {
        return end_clean_time;
    }

    public void setEnd_clean_time(Date end_clean_time) {
        this.end_clean_time = end_clean_time;
    }

    public Long getClean_cost_time() {
        return clean_cost_time;
    }

    public void setClean_cost_time(Long clean_cost_time) {
        this.clean_cost_time = clean_cost_time;
    }

    public String getBigData() {
        return bigData;
    }

    public void setBigData(String bigData) {
        this.bigData = bigData;
    }
}