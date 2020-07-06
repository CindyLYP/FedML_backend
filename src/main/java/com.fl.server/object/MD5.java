package com.fl.server.object;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MD5 {
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest md5=MessageDigest.getInstance("MD5");
        Base64.Encoder base64en = Base64.getEncoder();
        return base64en.encodeToString(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
    }

    public static boolean Check(String password, String result) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        return EncoderByMd5(password).equals(result);
    }
}
