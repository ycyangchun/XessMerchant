package com.golive.xess.merchant.model.api.body;

/**
 * Created by YangChun .
 * on 2017/3/14.
 */

public class WalletLogsBody {
    /**
     * storeNo : 6AC3559BDF3C4B90AF68AA055DD4BA20
     * deviceNo : 95A32E080CB74C638C5E7471D6C21EE3
     * pageNo : 0
     * pageSize : 10
     */

    private String storeNo;
    private String deviceNo;
    private String pageNo;
    private String pageSize;
    private String userNo;

    public WalletLogsBody(String storeNo, String userNo ,String deviceNo, String pageNo, String pageSize) {
        this.storeNo = storeNo;
        this.deviceNo = deviceNo;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.userNo = userNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
