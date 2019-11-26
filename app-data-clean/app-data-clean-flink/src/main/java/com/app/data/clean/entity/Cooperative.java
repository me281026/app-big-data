package com.app.data.clean.entity;

import java.io.Serializable;

public class Cooperative implements Serializable {
    private String Valid_time;
    private String Cooperative_information_title;
    private String Cooperative_information_href;

    public String getValid_time() {
        return Valid_time;
    }

    public void setValid_time(String valid_time) {
        Valid_time = valid_time;
    }

    public String getCooperative_information_title() {
        return Cooperative_information_title;
    }

    public void setCooperative_information_title(String cooperative_information_title) {
        Cooperative_information_title = cooperative_information_title;
    }

    public String getCooperative_information_href() {
        return Cooperative_information_href;
    }

    public void setCooperative_information_href(String cooperative_information_href) {
        Cooperative_information_href = cooperative_information_href;
    }
}
