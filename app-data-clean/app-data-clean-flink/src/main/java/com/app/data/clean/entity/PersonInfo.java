package com.app.data.clean.entity;

import java.io.Serializable;

public class PersonInfo implements Serializable {

    private String keymanName;
    private String keymanJob;

    //股东为1,主要人员为2
    private String people_type;

    public String getKeymanName() {
        return keymanName;
    }

    public void setKeymanName(String keymanName) {
        this.keymanName = keymanName;
    }

    public String getKeymanJob() {
        return keymanJob;
    }

    public void setKeymanJob(String keymanJob) {
        this.keymanJob = keymanJob;
    }

    public String getPeople_type() {
        return people_type;
    }

    public void setPeople_type(String people_type) {
        this.people_type = people_type;
    }
}
