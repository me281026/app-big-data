/**
 * Copyright 2019 bejson.com
 */
package com.app.data.clean.core.entity;


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
}