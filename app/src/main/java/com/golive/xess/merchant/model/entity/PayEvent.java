package com.golive.xess.merchant.model.entity;

/**
 * Created by YangChun .
 * on 2017/3/22.
 */

public class PayEvent {
    private String result;
    private String kidneyBean;

    public PayEvent(String result, String kidneyBean) {
        this.result = result;
        this.kidneyBean = kidneyBean;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getKidneyBean() {
        return kidneyBean;
    }

    public void setKidneyBean(String kidneyBean) {
        this.kidneyBean = kidneyBean;
    }
}
