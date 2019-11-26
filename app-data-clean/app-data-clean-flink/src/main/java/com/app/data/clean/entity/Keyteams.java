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
public class Keyteams {

    private String keyteamDesc;
    private String keyteamName;
    private String keyteamImageSrc;

    public String getKeyteamDesc() {
        return keyteamDesc;
    }

    public void setKeyteamDesc(String keyteamDesc) {
        this.keyteamDesc = keyteamDesc;
    }

    public String getKeyteamName() {
        return keyteamName;
    }

    public void setKeyteamName(String keyteamName) {
        this.keyteamName = keyteamName;
    }

    public String getKeyteamImageSrc() {
        return keyteamImageSrc;
    }

    public void setKeyteamImageSrc(String keyteamImageSrc) {
        this.keyteamImageSrc = keyteamImageSrc;
    }
}