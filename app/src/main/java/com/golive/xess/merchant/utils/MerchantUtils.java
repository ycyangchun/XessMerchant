package com.golive.xess.merchant.utils;

/**
 * Created by YangChun .
 * on 2017/4/1.
 * 统一管理
 */

public class MerchantUtils {
   /* public static String deviceNo ;
    public static String storeUid ;
    public static String password ;
    public static String storeNo;
    public static String mac;
    public static String updateDevice;
    public static String lhqId;
     public static String onlineNo;
    public static String kidneyBean;*/



    public static String getOnlineNo() {
        return SharedPreferencesUtils.getString("onlineNo");
    }

    public static void setOnlineNo(String onlineNo) {
        SharedPreferencesUtils.put("onlineNo",onlineNo);
    }

    public static String getKidneyBean() {
        return SharedPreferencesUtils.getString("kidneyBean");
    }

    public static void setKidneyBean(String kidneyBean) {
        SharedPreferencesUtils.put("kidneyBean",kidneyBean);
    }

    public static String getUpdateDevice() {//是否上传过设备信息
        return  SharedPreferencesUtils.getString("updateDevice");
    }

    public static void setUpdateDevice(String updateDevice) {
        SharedPreferencesUtils.put("updateDevice",updateDevice);
    }

    public static String getLhqId() {
        return SharedPreferencesUtils.getString("lhqId");
    }

    public static void setLhqId(String lhqId) {
        SharedPreferencesUtils.put("lhqId",lhqId);
    }

    public static String getDeviceNo() {
        return SharedPreferencesUtils.getString("deviceNo");
    }

    public static void setDeviceNo(String deviceNo) {
        SharedPreferencesUtils.put("deviceNo",deviceNo);
    }

    public static String getStoreUid() {
        return SharedPreferencesUtils.getString("storeUid");
    }

    public static void setStoreUid(String storeUid) {
        SharedPreferencesUtils.put("storeUid",storeUid);
    }

    public static String getPassword() {
        return SharedPreferencesUtils.getString("password");
    }

    public static void setPassword(String password) {
        SharedPreferencesUtils.put("password",password);
    }

    public static String getStoreNo() {
        return SharedPreferencesUtils.getString("storeNo");
    }

    public static void setStoreNo(String storeNo) {
        SharedPreferencesUtils.put("storeNo",storeNo);
    }

    public static String getMac() {
        return SharedPreferencesUtils.getString("mac");
    }

    public static void setMac(String mac) {
        SharedPreferencesUtils.put("mac",mac);
    }
}
