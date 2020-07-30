package com.fl.server.object.tools;

import java.util.ArrayList;
import java.util.HashMap;

public  class TypeFactory {
    public static HashMap<String, Object> GenerateHMSO(){
        return new HashMap<String, Object>();
    }
    public static HashMap<String, String> GenerateHMSS(){
        return new HashMap<String, String>();
    }

    public static ArrayList<Object> GenerateALO(){
        return new ArrayList<Object>();
    }
}
