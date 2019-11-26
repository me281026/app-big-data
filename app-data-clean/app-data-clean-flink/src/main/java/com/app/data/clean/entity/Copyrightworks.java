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
public class Copyrightworks {

    private String worksName;
    private String worksOverDate;
    private String worksRegDate;
    private String worksType;
    private String worksRegCode;
    private String worksPubDate;

    public String getWorksName() {
        return worksName;
    }

    public void setWorksName(String worksName) {
        this.worksName = worksName;
    }

    public String getWorksOverDate() {
        return worksOverDate;
    }

    public void setWorksOverDate(String worksOverDate) {
        this.worksOverDate = worksOverDate;
    }

    public String getWorksRegDate() {
        return worksRegDate;
    }

    public void setWorksRegDate(String worksRegDate) {
        this.worksRegDate = worksRegDate;
    }

    public String getWorksType() {
        return worksType;
    }

    public void setWorksType(String worksType) {
        this.worksType = worksType;
    }

    public String getWorksRegCode() {
        return worksRegCode;
    }

    public void setWorksRegCode(String worksRegCode) {
        this.worksRegCode = worksRegCode;
    }

    public String getWorksPubDate() {
        return worksPubDate;
    }

    public void setWorksPubDate(String worksPubDate) {
        this.worksPubDate = worksPubDate;
    }
}