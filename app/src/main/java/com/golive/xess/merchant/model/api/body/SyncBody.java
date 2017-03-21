package com.golive.xess.merchant.model.api.body;

/**
 * Created by YangChun .
 * on 2017/3/21.
 */

public class SyncBody {
    private String deviceId;
    private String mac;

    public SyncBody(String deviceId, String mac) {
        this.deviceId = deviceId;
        this.mac = mac;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
