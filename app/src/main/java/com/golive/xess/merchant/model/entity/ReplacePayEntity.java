package com.golive.xess.merchant.model.entity;

/**
 * Created by YangChun .
 * on 2017/3/18.
 */

public class ReplacePayEntity {

    /**
     * index : 0
     * oid : 201703171352412037008100001
     * status : 1
     * describe : 双色球-选号03,07,10,21,22,30*06第2017031已停售，截止时间为：2017-03-19 19:00:00
     * amount : 0.0
     * operateTime : 2017-03-20 11:45:40:922
     * beforeAmount : 0.0
     * backAmount : 0.0
     */

    private int index;
    private String oid;
    private String status;
    private String describe;
    private double amount;
    private String operateTime;
    private double beforeAmount;
    private double backAmount;

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public double getBeforeAmount() {
        return beforeAmount;
    }

    public void setBeforeAmount(double beforeAmount) {
        this.beforeAmount = beforeAmount;
    }

    public double getBackAmount() {
        return backAmount;
    }

    public void setBackAmount(double backAmount) {
        this.backAmount = backAmount;
    }
}
