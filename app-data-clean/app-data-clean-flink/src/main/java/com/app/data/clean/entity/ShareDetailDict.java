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
public class ShareDetailDict {

    private String special;
    private String data;
    private String vipMessage;
    private String state;

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getVipMessage() {
        return vipMessage;
    }

    public void setVipMessage(String vipMessage) {
        this.vipMessage = vipMessage;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}