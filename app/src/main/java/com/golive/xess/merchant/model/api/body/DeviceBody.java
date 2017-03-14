package com.golive.xess.merchant.model.api.body;

/**
 * Created by YangChun .
 * on 2017/3/14.
 */

public class DeviceBody {
    private String deviceNo;

    public DeviceBody(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
}
