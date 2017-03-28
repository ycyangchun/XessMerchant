package com.golive.xess.merchant.model.api.body;

/**
 * Created by YangChun .
 * on 2017/3/28.
 */

public class WithdrawBody {

    /**
     * storeUid : 10000
     * deviceNo : 95A32E080CB74C638C5E7471D6C21EE3
     * type : 2
     * kidneyBean  : 2
     */

    private String storeUid;
    private String deviceNo;
    private String type;
    private String kidneyBean;

    public String getStoreUid() {
        return storeUid;
    }

    public void setStoreUid(String storeUid) {
        this.storeUid = storeUid;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKidneyBean() {
        return kidneyBean;
    }

    public void setKidneyBean(String kidneyBean) {
        this.kidneyBean = kidneyBean;
    }
}
