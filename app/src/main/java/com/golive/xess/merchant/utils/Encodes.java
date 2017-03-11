package com.golive.xess.merchant.utils;

import android.util.Base64;

/**
 * Created by YangChun .
 * on 2017/3/11.
 * 封装各种格式的编码解码工具类.
 * 1.Commons-Codec�?hex/base64 编码
 * 2.自制的base62 编码
 * 3.Commons-Lang的xml/html escape
 * 4.JDK提供的URLEncoder
 */


public class Encodes {
    private static final String DEFAULT_URL_ENCODING = "UTF-8";
    private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();


    /**
     * Base64编码.
     */
    public static String encodeBase64(byte[] input) {
        return Base64.encodeToString(input, Base64.DEFAULT);
    }

    /**
     * Base64编码, URL安全(将Base64中的URL非法字符'+'�?/'转为'-'�?_', 见RFC3548).
     */
    public static String encodeUrlSafeBase64(byte[] input) {
        return Base64.encodeToString(input, Base64.URL_SAFE);
    }

    /**
     * Base64解码.
     */
    public static byte[] decodeBase64(String input) {
        return Base64.decode(input, Base64.DEFAULT);
    }
}
