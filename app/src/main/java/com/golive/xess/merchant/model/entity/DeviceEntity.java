package com.golive.xess.merchant.model.entity;

/**
 * Created by YangChun .
 * on 2017/3/11.
 * 设备信息上传
 */

public class DeviceEntity {
    /**
     * deviceNo : BA201956CFB9EC2295984BDEA0BA2F78
     * deviceName : MiTV3S-43
     * brand : Xiaomi
     * wifiMac : 8c:57:9b:26:ec:eb
     * btMac : 00:9E:C8:36:F9:46
     * deviceModel : hancock
     */
    /*{"code":"0","msg":"已经存在"}*/

    private String deviceNo;
    private String deviceName;
    private String brand;
    private String wifiMac;
    private String btMac;
    private String deviceModel;

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWifiMac() {
        return wifiMac;
    }

    public void setWifiMac(String wifiMac) {
        this.wifiMac = wifiMac;
    }

    public String getBtMac() {
        return btMac;
    }

    public void setBtMac(String btMac) {
        this.btMac = btMac;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }
}
