package com.fl.server.pojo;

import java.util.ArrayList;
import java.util.HashMap;

public class Model {
    private int id;
    private String name;
    private String params;
    private ArrayList<String> params_list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public ArrayList<String> getParams_list() {
        return params_list;
    }

    public void setParams_list(ArrayList<String> params_list) {
        this.params_list = params_list;
    }

    public void stringToList(){
        for(String s:this.params.split("#"))
            this.params_list.add(s);
    }
}
