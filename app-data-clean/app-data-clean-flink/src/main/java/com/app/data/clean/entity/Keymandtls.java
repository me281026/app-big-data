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
public class Keymandtls {

    private String keymandtlName;
    private List<KeymandtlValue> keymandtlValue;

    public String getKeymandtlName() {
        return keymandtlName;
    }

    public void setKeymandtlName(String keymandtlName) {
        this.keymandtlName = keymandtlName;
    }

    public List<KeymandtlValue> getKeymandtlValue() {
        return keymandtlValue;
    }

    public void setKeymandtlValue(List<KeymandtlValue> keymandtlValue) {
        this.keymandtlValue = keymandtlValue;
    }
}