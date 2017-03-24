package com.golive.xess.merchant.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

/**
 *
 */
public class AppUtil {
    /**
     * 获取app版本名
     */
    public static String getAppVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取应用程序版本名称信息
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取app版本号
     */
    public static int getAppVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 设备SDK版本
     * Android API等级（22、23 ...）
     */
    public static int getBuildLevel() {
        return android.os.Build.VERSION.SDK_INT;
    }
    /**
     * 设备系统版本
     * Android 版本（4.4、5.0、5.1 ...）
     */
    public static String getBuildVersion() {
        return android.os.Build.VERSION.RELEASE;
    }
    /**
     * deviceId
     */
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        if (deviceId == null) {
            return "";
        } else {
            return deviceId;
        }
    }
    /**
     * 蓝牙mac地址
     */
    public static String getMacByBlue() {
        try {
            BluetoothAdapter btAda = BluetoothAdapter.getDefaultAdapter();
            //开启蓝牙
//            if (btAda.isEnabled() == false) {
//                if (btAda.enable()) {
//                    while (btAda.getState() == BluetoothAdapter.STATE_TURNING_ON
//                            || btAda.getState() != BluetoothAdapter.STATE_ON) {
//                        try {
//                            Thread.sleep(100);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
            String blue = btAda.getAddress();
            blue = blue != null ? blue :"";
            return blue;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    /**
     * wifi网络mac
     */
    public static String getMacByWifi(Context context){
        try {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            return info.getMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    /**
     * 屏幕尺寸
     */
    public static String getDisplay(Context context){
        int height = DisplayUtils.getScreenHeight(context);
        int width = DisplayUtils.getScreenWidth(context);
        if(height > width) {
            return height + "X" + width;
        } else {
            return width + "X" + height;
        }
    }
    /**
     * 设备型号
     * Build.PRODUCT//手机制造商
     */
    public static String getPhoneProduct() {
        return android.os.Build.PRODUCT;
    }
    /**
     * 设备厂商
     * Build.BRAND//系统定制商
     */
    public static String getPhoneBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 设备名称
     * Build.MODEL//手机型号(MI XXX)
     */
    public static String getPhoneModel() {
        return android.os.Build.MODEL;
    }
}
