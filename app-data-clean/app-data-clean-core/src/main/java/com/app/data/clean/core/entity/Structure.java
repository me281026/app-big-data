/**
  * Copyright 2019 bejson.com 
  */
package com.app.data.clean.core.entity;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2019-05-08 22:34:39
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Structure {

    private String shType;
    private String amount;
    private String parentName;
    private String regCapital;
    private List<Children> children;
    private String actualHolding;
    private String name;
    private String id;
    private String type;
    private String percent;
    private int cid;
    private String info;

}