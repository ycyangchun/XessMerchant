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
    private String commission;
    private String bankNo;
    private String bankInfo;
    private String bankUserName;
    /**
     *
     *  storeNo :  6AC3559BDF3C4B90AF68AA055DD4BA20
     * userName : 徐淼Name : 中国银行·个人普卡
     * createTime : 2017-03-28 13:57:45:922
     */

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(String bankInfo) {
        this.bankInfo = bankInfo;
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName;
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
