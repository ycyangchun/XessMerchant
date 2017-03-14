package com.golive.xess.merchant.model.api.body;

/**
 * Created by YangChun .
 * on 2017/3/14.
 *
 */

public class BetDetailBody {
    private String deviceNo;
    private String orderNo;
    public BetDetailBody(String deviceNo, String orderNo) {
        this.deviceNo = deviceNo;
        this.orderNo = orderNo;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
