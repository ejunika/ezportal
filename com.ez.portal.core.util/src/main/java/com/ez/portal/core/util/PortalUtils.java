package com.ez.portal.core.util;

import java.security.MessageDigest;

public class PortalUtils {
    
    public static final Byte SUPER_USER = 1;
    public static final Byte DEAN = 2;
    public static final Byte PRINCIPAL = 3;
    public static final Byte FACULTY = 4;
    public static final Byte STUDENT = 5;
    public static final Byte GARDIAN = 6;
    public static final Byte ACCOUNTENT = 7;
    public static final Byte CLERK = 8;
    public static final Byte PEON = 9;
    
    public static String getHashedString(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] bytes = str.getBytes();
        messageDigest.reset();
        messageDigest.update(bytes);
        byte md[] = messageDigest.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < md.length; i++) {
            String hex = Integer.toHexString(0xFF & md[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString().toUpperCase();
    }
    
    public static String getPasswordHash(String password) {
        MessageDigest md = null;
        StringBuffer hexString = null;
        if (password != null) {
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (Exception e) {
                e.printStackTrace();
            }
            byte[] defaultBytes = password.getBytes();
            md.reset();
            md.update(defaultBytes);
            byte messageDigest[] = md.digest();
            hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
        }
        return hexString.toString();
    }
}
