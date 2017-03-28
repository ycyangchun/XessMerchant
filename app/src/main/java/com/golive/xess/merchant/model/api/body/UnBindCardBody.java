package com.golive.xess.merchant.model.api.body;

/**
 * Created by YangChun .
 * on 2017/3/15.
 */

public class UnBindCardBody {

    /**
     * storeUid : 10000
     * deviceNo : 33333028A95D42278FD3ACFD30CB8B32
     */

    private String storeUid;
    private String deviceNo;

    public UnBindCardBody() {
    }

    public UnBindCardBody(String storeUid, String deviceNo) {
        this.storeUid = storeUid;
        this.deviceNo = deviceNo;
    }

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
}
