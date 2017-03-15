package com.golive.xess.merchant.utils;

import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by YangChun .
 * on 2017/3/11.
 */

public class Base64Util {
    public static String encode(String s) {
        if (s == null)
            return null;
        return new String(Base64.encode(s.getBytes(), Base64.DEFAULT));
    }

    public static String decode(String s) {
        if (s == null)
            return null;
        try {
            return new String(Base64.decode(s, Base64.DEFAULT));
        } catch (Exception e) {
            return null;
        }
    }
}
