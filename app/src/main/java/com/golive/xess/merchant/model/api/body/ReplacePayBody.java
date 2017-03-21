package com.golive.xess.merchant.model.api.body;

/**
 * Created by YangChun .
 * on 2017/3/18.
 */

public class ReplacePayBody {

    /**
     * storeUid : 100001
     * deviceNo : 95A32E080CB74C638C5E7471D6C21EE3
     * oids : 201703090918506047175100124#201703090759354116649100119
     */

    private String storeUid;
    private String deviceNo;
    private String oids;

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

    public String getOids() {
        return oids;
    }

    public void setOids(String oids) {
        this.oids = oids;
    }
}
