package com.golive.xess.merchant.model.entity;

/**
 * Created by YangChun .
 * on 2017/3/18.
 */

public class PayEntity {

    /**
     * index : 0
     * oid : 201703090918506047175100124
     * uid : 100001
     * mobile : 18515966636
     * name : 徐淼
     * storeUid : 10000
     * storeName : 商户1
     * status : 0
     * describe : 代付成功
     * lid : 双色球
     * issue : 2017031
     * investCode : 03,07,10,21,22,30*06
     * investNum : 1
     * timesCount : 1
     * amount : 200
     * operateTime : 2017-03-18 17:02:49:761
     * endSaleTime : 2017-03-19 19:00:00
     * drawTime : 2017-03-19 20:45:00.0
     * beforeAmount : 400
     * backAmount : 200
     */

    private int index;
    private String oid;
    private String uid;
    private String mobile;
    private String name;
    private String storeUid;
    private String storeName;
    private String status;
    private String describe;
    private String lid;
    private String issue;
    private String investCode;
    private String investNum;
    private String timesCount;
    private int amount;
    private String operateTime;
    private String endSaleTime;
    private String drawTime;
    private int beforeAmount;
    private int backAmount;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getStoreUid() {
        return storeUid;
    }

    public void setStoreUid(String storeUid) {
        this.storeUid = storeUid;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
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

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getEndSaleTime() {
        return endSaleTime;
    }

    public void setEndSaleTime(String endSaleTime) {
        this.endSaleTime = endSaleTime;
    }

    public String getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(String drawTime) {
        this.drawTime = drawTime;
    }

    public int getBeforeAmount() {
        return beforeAmount;
    }

    public void setBeforeAmount(int beforeAmount) {
        this.beforeAmount = beforeAmount;
    }

    public int getBackAmount() {
        return backAmount;
    }

    public void setBackAmount(int backAmount) {
        this.backAmount = backAmount;
    }
}
