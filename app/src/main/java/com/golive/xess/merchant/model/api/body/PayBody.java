package com.golive.xess.merchant.model.api.body;

/**
 * Created by YangChun .
 * on 2017/3/21.
 */

public class PayBody {

    /**
     * mac : 7777777
     * deviceId : 88888888
     * clientType : TEST-TCL
     * userNo : 10086
     * lhqId : 53776
     * bean : 10
     * proportion : 1
     * type : 0
     * coin_type : 0
     */

    private String mac;
    private String deviceId;
    private String clientType;
    private String userNo;
    private String lhqId;
    private String bean;
    private String proportion;
    private String type;
    private String coin_type;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getLhqId() {
        return lhqId;
    }

    public void setLhqId(String lhqId) {
        this.lhqId = lhqId;
    }

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoin_type() {
        return coin_type;
    }

    public void setCoin_type(String coin_type) {
        this.coin_type = coin_type;
    }
}
