package com.tads.dac.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringToMD5 {
    private StringToMD5(){}
    
    public static String toMD5(String password) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
            sb.append(String.format("%02x", b & 0xff));
        return sb.toString();
    }
}
