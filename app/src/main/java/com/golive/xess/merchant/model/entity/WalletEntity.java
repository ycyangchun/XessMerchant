package com.golive.xess.merchant.model.entity;

/**
 * Created by YangChun .
 * on 2017/3/13.
 * 钱包
 */

public class WalletEntity {

    /**
     * headImg : 2
     * kidneyBean : 600.0
     * gainBean : 0.0
     * topMoney : 0.0
     * winTimes : 0
     */

    private String headImg;
    private String kidneyBean;
    private String gainBean;
    private String topMoney;
    private String winTimes;

    //商家
    private String logo;
    private String kidney_bean;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getKidney_bean() {
        return kidney_bean;
    }

    public void setKidney_bean(String kidney_bean) {
        this.kidney_bean = kidney_bean;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getKidneyBean() {
        return kidneyBean;
    }

    public void setKidneyBean(String kidneyBean) {
        this.kidneyBean = kidneyBean;
    }

    public String getGainBean() {
        return gainBean;
    }

    public void setGainBean(String gainBean) {
        this.gainBean = gainBean;
    }

    public String getTopMoney() {
        return topMoney;
    }

    public void setTopMoney(String topMoney) {
        this.topMoney = topMoney;
    }

    public String getWinTimes() {
        return winTimes;
    }

    public void setWinTimes(String winTimes) {
        this.winTimes = winTimes;
    }
}
