package com.golive.xess.merchant.model.api.body;

/**
 * Created by YangChun .
 * on 2017/3/15.
 */

public class BindCardBody {

    /**
     * storeNo :  6AC3559BDF3C4B90AF68AA055DD4BA20
     * userName : 徐淼
     * bankInfo : 北京通州支行
     * bankNo : 621661280000447287
     * originType : 1    设备系统类型； 1android 2web
     * deviceNo : 95A32E080CB74C638C5E7471D6C21EE3
     * mac : 5C36B8EE9009
     */

    private String storeNo;
    private String userName;
    private String bankInfo;
    private String bankNo;
    private String originType;
    private String deviceNo;
    private String mac;

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(String bankInfo) {
        this.bankInfo = bankInfo;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getOriginType() {
        return originType;
    }

    public void setOriginType(String originType) {
        this.originType = originType;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
