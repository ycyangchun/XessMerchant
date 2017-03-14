package com.golive.xess.merchant.model.api.body;

/**
 * Created by YangChun .
 * on 2017/3/14.
 * 获取用户 or 商家信息
 */

public class UserBody {

    /**
     * mobile : 18515966636
     * deviceNo : 95A32E080CB74C638C5E7471D6C21EE3
     */

    private String mobile;
    private String deviceNo;

    public UserBody(String mobile, String deviceNo) {
        this.mobile = mobile;
        this.deviceNo = deviceNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
}
