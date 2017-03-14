package com.golive.xess.merchant.model.api.body;

/**
 * Created by YangChun .
 * on 2017/3/14.
 */

public class WalletBody {
    /**
     * userNo : 10010
     * deviceNo : 95A32E080CB74C638C5E7471D6C21EE3
     */

    private String userNo;
    private String deviceNo;
    private String storeNo;

    public WalletBody(String storeNo, String userNo , String deviceNo) {
        this.storeNo = storeNo;
        this.deviceNo = deviceNo;
        this.userNo = userNo;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
}
