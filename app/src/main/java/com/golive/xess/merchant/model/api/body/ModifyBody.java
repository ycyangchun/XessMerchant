package com.golive.xess.merchant.model.api.body;

/**
 * Created by YangChun .
 * on 2017/3/20.
 */

public class ModifyBody {

    public ModifyBody(String deviceNo, String storeUid ,String mac, String originalPwd,String newPwd) {
        this.deviceNo = deviceNo;
        this.mac = mac;
        this.newPwd = newPwd;
        OriginalPwd = originalPwd;
        this.storeUid = storeUid;
    }

    /**
     * storeUid : 18515966636
     * OriginalPwd : 1
     * newPwd : 123456
     * deviceNo : 95A32E080CB74C638C5E7471D6C21EE3
     * mac : 5C36B8EE9009
     */


    private String storeUid;
    private String OriginalPwd;
    private String newPwd;
    private String deviceNo;
    private String mac;

    public String getStoreUid() {
        return storeUid;
    }

    public void setStoreUid(String storeUid) {
        this.storeUid = storeUid;
    }

    public String getOriginalPwd() {
        return OriginalPwd;
    }

    public void setOriginalPwd(String OriginalPwd) {
        this.OriginalPwd = OriginalPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
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
