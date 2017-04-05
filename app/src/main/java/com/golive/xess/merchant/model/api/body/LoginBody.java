package com.golive.xess.merchant.model.api.body;

/**
 * Created by YangChun .
 * on 2017/3/15.
 */

public class LoginBody {

    /**
     * storeUid : 10000
     * password : 123456
     * deviceNo : 33333028A95D42278FD3ACFD30CB8B32
     */

    private String storeUid;
    private String password;
    private String deviceNo;
    private String  onlineNo;

    public LoginBody() {
    }

    public LoginBody(String storeUid, String password, String deviceNo, String onlineNo) {
        this.storeUid = storeUid;
        this.password = password;
        this.deviceNo = deviceNo;
        this.onlineNo = onlineNo;
    }

    public String getOnlineNo() {
        return onlineNo;
    }

    public void setOnlineNo(String onlineNo) {
        this.onlineNo = onlineNo;
    }

    public String getStoreUid() {
        return storeUid;
    }

    public void setStoreUid(String storeUid) {
        this.storeUid = storeUid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
}
