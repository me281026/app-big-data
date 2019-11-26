package com.app.data.clean.entity;


import java.io.Serializable;
import java.util.List;

public class ListKeyMan implements Serializable {

    private List<KeyMan> keymans;


    public List<KeyMan> getKeymans() {
        return keymans;
    }

    public void setKeymans(List<KeyMan> keymans) {
        this.keymans = keymans;
    }

}
