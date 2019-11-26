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
public class Sharedtls {

    private List<SharedtlValue> sharedtlValue;
    private String sharedtlName;
    public void setSharedtlValue(List<SharedtlValue> sharedtlValue) {
         this.sharedtlValue = sharedtlValue;
     }
     public List<SharedtlValue> getSharedtlValue() {
         return sharedtlValue;
     }

    public void setSharedtlName(String sharedtlName) {
         this.sharedtlName = sharedtlName;
     }
     public String getSharedtlName() {
         return sharedtlName;
     }



}