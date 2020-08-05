package com.fl.server.object.tools;

public class Randm {
    public static final int randomPort(){
        String t = String.valueOf(System.currentTimeMillis());
        String port = "1"+t.substring(t.length()-4,t.length());
        return Integer.parseInt(port);
    }
    public static final String outCsvPath(){
        return String.valueOf(System.currentTimeMillis());
    }
}
