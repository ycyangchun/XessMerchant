package com.golive.xess.merchant.model.entity;

/**
 * Created by YangChun .
 * on 2017/3/21.
 */

public class SyncEntity {
    private String lhqId;
    private String clientIp;
    private String golds;

    public String getLhqId() {
        return lhqId;
    }

    public void setLhqId(String lhqId) {
        this.lhqId = lhqId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getGolds() {
        return golds;
    }

    public void setGolds(String golds) {
        this.golds = golds;
    }
}
