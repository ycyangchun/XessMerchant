package com.golive.xess.merchant.model.entity;

/**
 * Created by YangChun .
 * on 2017/3/15.
 */

public class LoginEntity {

    /**
     * storeUid : 10000
     * storeNo : 6AC3559BDF3C4B90AF68AA055DD4BA20
     * storeAccountNo : 7AB2C7943ABC44DEB0D5FE2816AC4F9B
     * kidneyBean : 0.0
     * clientIp : 125.33.167.202
     * createTime : 2017-03-13 19:17:54:000
     * updateTime : 2017-03-15 11:30:10:744
     */

    private int storeUid;
    private String storeNo;
    private String storeAccountNo;
    private double kidneyBean;
    private String clientIp;
    private String createTime;
    private String updateTime;
    private String province;
    private String city;
    private String town;
    private String headImg;
    private String storeName;
    private String storeAlias;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAlias() {
        return storeAlias;
    }

    public void setStoreAlias(String storeAlias) {
        this.storeAlias = storeAlias;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getStoreUid() {
        return storeUid;
    }

    public void setStoreUid(int storeUid) {
        this.storeUid = storeUid;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getStoreAccountNo() {
        return storeAccountNo;
    }

    public void setStoreAccountNo(String storeAccountNo) {
        this.storeAccountNo = storeAccountNo;
    }

    public double getKidneyBean() {
        return kidneyBean;
    }

    public void setKidneyBean(double kidneyBean) {
        this.kidneyBean = kidneyBean;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
