package com.golive.xess.merchant.model.entity;

/**
 * Created by YangChun .
 * on 2017/3/21.
 */

public class PayEntity {

    /**
     * aliPayQr : https://******
     * wxPayQr : https://*********.jpg
     */

    private String aliPayQr;
    private String wxPayQr;

    public String getAliPayQr() {
        return aliPayQr;
    }

    public void setAliPayQr(String aliPayQr) {
        this.aliPayQr = aliPayQr;
    }

    public String getWxPayQr() {
        return wxPayQr;
    }

    public void setWxPayQr(String wxPayQr) {
        this.wxPayQr = wxPayQr;
    }
}
