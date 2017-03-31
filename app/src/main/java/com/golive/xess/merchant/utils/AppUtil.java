package com.golive.xess.merchant.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.WindowManager;

import java.io.FileInputStream;

import static java.security.AccessController.getContext;

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
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        /*
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
	    联通：130、131、132、152、155、156、185、186
	    电信：133、153、180、189、（1349卫通）
	    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
	    */
        String telRegex = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
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
     * 获取设备序列号
     */
    public static  String deviceId = "";
    public static String getDeviceId(Context context) {
        if(!TextUtils.isEmpty(deviceId)) return  deviceId;
//        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        deviceId = tm.getDeviceId();
//        deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        deviceId =  android.os.Build.SERIAL;
        return deviceId;
    }
    /**
     * 蓝牙mac地址
     */
    public static String blue = "";
    public static String getMacByBlue() {
        if(!TextUtils.isEmpty(blue)) return  blue;
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
            blue = btAda.getAddress();
            return blue;
        } catch (Exception e) {
            e.printStackTrace();
            return blue;
        }
    }
    /**
     * wifi网络mac
     */

    public static String wifiMac = null;
    public static String getMacByWifi(){
        if(!TextUtils.isEmpty(wifiMac)) return  wifiMac;
        /*try {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            wifiMac = info.getMacAddress();
            return wifiMac;
        } catch (Exception e) {
            e.printStackTrace();
            return wifiMac;
        }*/
        //在不开起WiFi的情况下获取mac地址
        try {
                String path = "sys/class/net/eth0/address";
                FileInputStream fis_name = new FileInputStream(path);
                byte[] buffer_name = new byte[8192];
                int byteCount_name = fis_name.read(buffer_name);
                if (byteCount_name > 0) {
                    wifiMac = new String(buffer_name, 0, byteCount_name, "utf-8");
                }
                if (wifiMac == null) {
                    fis_name.close();
                    return wifiMac;
                }
                fis_name.close();
            } catch (Exception io) {
                String path = "sys/class/net/wlan0/address";
                FileInputStream fis_name;
                try {
                    fis_name = new FileInputStream(path);
                    byte[] buffer_name = new byte[8192];
                    int byteCount_name = fis_name.read(buffer_name);
                    if (byteCount_name > 0) {
                        wifiMac = new String(buffer_name, 0, byteCount_name, "utf-8");
                    }
                    fis_name.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }


            if (wifiMac == null) {
                return wifiMac;
            } else {
                wifiMac = wifiMac.trim();
                if(wifiMac.contains("\n")){
                    wifiMac = wifiMac.substring(0,wifiMac.indexOf("\\"));
                }
                return wifiMac;
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
