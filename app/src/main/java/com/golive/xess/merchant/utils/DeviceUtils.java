package com.golive.xess.merchant.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.leechuanrong.DevicesUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by YangChun .
 * on 2017/3/14.
 */

public class DeviceUtils {

    public static String getDeviceNo(Context context) {
        Map<String, String> req = getStringStringMap(context);
        String deviceNo = DevicesUtils.getInstance().getDeviceNo(req);
        return deviceNo;
    }

    @NonNull
    private static Map<String, String> getStringStringMap(Context context) {
        Map<String, String> req = new LinkedHashMap<>();
        req.put("deviceModel", AppUtil.getPhoneProduct());
        req.put("versionSdk", AppUtil.getBuildLevel() + "");
        req.put("versionRelease", AppUtil.getBuildVersion());
        req.put("deviceId", AppUtil.getDeviceId(context));
        req.put("wlanMac", AppUtil.getMacByWifi());
        req.put("btMac", AppUtil.getMacByBlue());
        req.put("screenInches", AppUtil.getDisplay(context));
        req.put("brand", AppUtil.getPhoneBrand());
        req.put("model", AppUtil.getPhoneModel());
        return req;
    }
}
