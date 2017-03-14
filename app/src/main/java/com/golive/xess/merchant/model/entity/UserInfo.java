package com.golive.xess.merchant.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by YangChun .
 * on 2017/3/13.
 * 用户信息
 */

public class UserInfo {


    /**
     {
     "uid": "100001",
     "lhqId": "14793520",
     "mobile": "18515966636",
     "name": "徐淼",
     "headImg": "2",
     "identifyType": 1,
     "identifyNo": "110223198812306038",
     "bankNo": "6214830100232262",
     "kidneyBean": 888688,
     "gainBean": 0,
     "topMoney": 0,
     "winTimes": 0,
     "golds": 999008,
     "integral": 0,
     "province": "110000",
     "city": "110100",
     "town": "110112",
     "mac": "5C36B8EE9001",
     "storeNo": "C1BF373010464B5FA04057831F218E53",
     "clientIp": "0:0:0:0:0:0:0:1",
     "platType": "1",
     "deviceNo": "95A32E080CB74C638C5E7471D6C21EE3",
     "createTime": "2017-02-27 11:19:05:000",
     "updateTime": "2017-03-08 17:55:16:000",
     "scope": "1",
     "scopeMsg": "商家用户双重身份"
     }
     */

    private String uid;
    private String lhqId;
    private String mobile;
    private String name;
    private String headImg;
    private int identifyType;
    private String identifyNo;
    private String bankNo;
    private double kidneyBean;
    private double gainBean;
    private double topMoney;
    private int winTimes;
    private double golds;
    private int integral;
    private String province;
    private String city;
    private String town;
    private String mac;
    private String storeNo;
    private String clientIp;
    private String platType;
    private String deviceNo;
    private String createTime;
    private String updateTime;
    private String scope;
    private String scopeMsg;
    /**
     * {
     "storeNo": "6AC3559BDF3C4B90AF68AA055DD4BA20",
     "mobile": "18515966636",
     "channelNo": "C1BF373010464B5FA04057831F218E53",
     "storeName": "全球播官方和彩",
     "storeAlias": "徐淼专享",
     "enStoreAlias": "golive_lottery",
     "state": "0",
     "authCode": "1111",
     "province": "110000",
     "city": "110100",
     "town": "110112",
     "address": "2124fsd",
     "kidneyBean": 1000
     }
     */

    private String channelNo;
    private String storeName;
    private String storeAlias;
    private String enStoreAlias;
    private String state;
    private String authCode;
    private String address;

    // 商家


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLhqId() {
        return lhqId;
    }

    public void setLhqId(String lhqId) {
        this.lhqId = lhqId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getIdentifyType() {
        return identifyType;
    }

    public void setIdentifyType(int identifyType) {
        this.identifyType = identifyType;
    }

    public String getIdentifyNo() {
        return identifyNo;
    }

    public void setIdentifyNo(String identifyNo) {
        this.identifyNo = identifyNo;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public double getKidneyBean() {
        return kidneyBean;
    }

    public void setKidneyBean(double kidneyBean) {
        this.kidneyBean = kidneyBean;
    }

    public double getGainBean() {
        return gainBean;
    }

    public void setGainBean(double gainBean) {
        this.gainBean = gainBean;
    }

    public double getTopMoney() {
        return topMoney;
    }

    public void setTopMoney(double topMoney) {
        this.topMoney = topMoney;
    }

    public int getWinTimes() {
        return winTimes;
    }

    public void setWinTimes(int winTimes) {
        this.winTimes = winTimes;
    }

    public double getGolds() {
        return golds;
    }

    public void setGolds(double golds) {
        this.golds = golds;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
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

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getPlatType() {
        return platType;
    }

    public void setPlatType(String platType) {
        this.platType = platType;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getScopeMsg() {
        return scopeMsg;
    }

    public void setScopeMsg(String scopeMsg) {
        this.scopeMsg = scopeMsg;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

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

    public String getEnStoreAlias() {
        return enStoreAlias;
    }

    public void setEnStoreAlias(String enStoreAlias) {
        this.enStoreAlias = enStoreAlias;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
