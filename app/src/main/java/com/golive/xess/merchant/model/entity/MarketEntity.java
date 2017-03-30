package com.golive.xess.merchant.model.entity;

/**
 * Created by YangChun .
 * on 2017/3/30.
 * 销售额
 */

public class MarketEntity {

    /**
     * dayNum : 6
     * dayAmount : 1200
     * monthNum : 6
     * monthAmount : 1200
     */

    private int dayNum;
    private int dayAmount;
    private int monthNum;
    private int monthAmount;

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public int getDayAmount() {
        return dayAmount;
    }

    public void setDayAmount(int dayAmount) {
        this.dayAmount = dayAmount;
    }

    public int getMonthNum() {
        return monthNum;
    }

    public void setMonthNum(int monthNum) {
        this.monthNum = monthNum;
    }

    public int getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(int monthAmount) {
        this.monthAmount = monthAmount;
    }
}
