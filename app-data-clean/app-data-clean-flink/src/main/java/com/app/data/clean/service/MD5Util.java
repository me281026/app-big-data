package com.app.data.clean.service;

import java.security.MessageDigest;

/**
 * 采用MD5加密解密
 */
public class MD5Util {

    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 根据自己的规则进行MD5加密
     * 例如，现在需求是有字符串传入zhang，xy
     * 其中zhang是传入的字符
     * 然后需要将zhang的字符进行拆分z，和hang，
     * 最后需要加密的字段为zxyhang
     */
    public static String MD5Test(String inStr) {
        String xy = "xy";
        String finalStr = "";
        if (inStr != null) {
            String fStr = inStr.substring(0, 1);
            String lStr = inStr.substring(1, inStr.length());
            finalStr = string2MD5(fStr + xy + lStr);

        } else {
            finalStr = string2MD5(xy);
        }
        return finalStr;
    }


    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (null == charsetname || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        } catch (Exception e) {
        }
        return resultString;
    }

    private static final String hexDigIts[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    public static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigIts[d1] + hexDigIts[d2];
    }

}