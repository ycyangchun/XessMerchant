package com.golive.xess.merchant.model.entity;

/**
 * Created by YangChun .
 * on 2017/4/5.
 */

public class BindOrChangerCard {

    /**
     *  storeNo :  6AC3559BDF3C4B90AF68AA055DD4BA20
     * userName : 徐淼
     * bankNo : 621661280000447287
     * bankName : 中国银行·个人普卡
     * bankInfo : 北京通州支行
     * createTime : 2017-03-28 13:57:45:922
     */

    private String storeNo;
    private String userName;
    private String bankNo;
    private String bankName;
    private String bankInfo;
    private String createTime;

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(String bankInfo) {
        this.bankInfo = bankInfo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
