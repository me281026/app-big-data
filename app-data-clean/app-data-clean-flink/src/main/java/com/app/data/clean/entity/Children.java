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
public class Children {

    private String shType;
    private String amount;
    private String parentName;
    private String regCapital;
    private List<String> children;
    private String actualHolding;
    private String name;
    private String id;
    private String type;
    private String percent;
    private Long cid;
    private String info;

    public String getShType() {
        return shType;
    }

    public void setShType(String shType) {
        this.shType = shType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    public String getActualHolding() {
        return actualHolding;
    }

    public void setActualHolding(String actualHolding) {
        this.actualHolding = actualHolding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}