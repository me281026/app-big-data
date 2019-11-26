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
public class Changes {

    private String changeDate;
    private String changeAfter;
    private String changeBefore;
    private String changeItem;

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangeAfter() {
        return changeAfter;
    }

    public void setChangeAfter(String changeAfter) {
        this.changeAfter = changeAfter;
    }

    public String getChangeBefore() {
        return changeBefore;
    }

    public void setChangeBefore(String changeBefore) {
        this.changeBefore = changeBefore;
    }

    public String getChangeItem() {
        return changeItem;
    }

    public void setChangeItem(String changeItem) {
        this.changeItem = changeItem;
    }
}