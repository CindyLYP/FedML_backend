package com.fl.server.object.tools;

import java.util.Random;

public class Randm {

    public int randomNum(int t){
        Random rand = new Random();
        return rand.nextInt(t);
    }
    public static final int randomPort(){
        //String t = String.valueOf(System.currentTimeMillis());
        //String port = "1"+t.substring(t.length()-4,t.length());
        return 10000+new Random().nextInt(9999);
    }
    public static final String outCsvPath(){

        return String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
    }
}
