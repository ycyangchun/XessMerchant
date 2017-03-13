package com.golive.xess.merchant.model.entity;

/**
 * Created by YangChun .
 * on 2017/3/13.
 * 订单
 */

public class OrdersEntity {

    /**
     * userNo : 100001
     * mobile : 18515966636
     * orderNo : 201703090759354116649100119
     * lid : 20201
     * pid : 502
     * investCode : 20170309|4|007|1^20170308|3|011|3^
     * investNum : 1
     * investType : 0
     * timesCount : 1
     * amount : 200
     * orderState : 10300
     * investState : 10200
     * winState : 10402
     * winAmount : 0
     * storeNo : 95A32E080CB74C638C5E7471D6C21EE3
     * deviceNo : 95A32E080CB74C638C5E7471D6C21EE3
     * channelNo : C1BF373010464B5FA04057831F218E53
     * isExternal : 0
     * createTime : 2017-03-09 11:02:30:000
     * updateTime : 2017-03-09 11:02:32:000
     * orderStateDesc : 还未开奖
     * issue : 2017027
     */

    private String userNo;
    private String mobile;
    private String orderNo;
    private String lid;
    private String pid;
    private String investCode;
    private String investNum;
    private String investType;
    private String timesCount;
    private int amount;
    private String orderState;
    private String investState;
    private String winState;
    private int winAmount;
    private String storeNo;
    private String deviceNo;
    private String channelNo;
    private String isExternal;
    private String createTime;
    private String updateTime;
    private String orderStateDesc;
    private String issue;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getInvestCode() {
        return investCode;
    }

    public void setInvestCode(String investCode) {
        this.investCode = investCode;
    }

    public String getInvestNum() {
        return investNum;
    }

    public void setInvestNum(String investNum) {
        this.investNum = investNum;
    }

    public String getInvestType() {
        return investType;
    }

    public void setInvestType(String investType) {
        this.investType = investType;
    }

    public String getTimesCount() {
        return timesCount;
    }

    public void setTimesCount(String timesCount) {
        this.timesCount = timesCount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getInvestState() {
        return investState;
    }

    public void setInvestState(String investState) {
        this.investState = investState;
    }

    public String getWinState() {
        return winState;
    }

    public void setWinState(String winState) {
        this.winState = winState;
    }

    public int getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(int winAmount) {
        this.winAmount = winAmount;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getIsExternal() {
        return isExternal;
    }

    public void setIsExternal(String isExternal) {
        this.isExternal = isExternal;
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

    public String getOrderStateDesc() {
        return orderStateDesc;
    }

    public void setOrderStateDesc(String orderStateDesc) {
        this.orderStateDesc = orderStateDesc;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }
}
