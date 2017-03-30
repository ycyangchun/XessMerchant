package com.golive.xess.merchant.model.entity;

/**
 * Created by YangChun .
 * on 2017/3/20.
 * 对账单
 */

public class AccountEntity {

    /**
     * mobile : 18515966636
     * headImg : 2
     * totalAmount : 200
     * SsqAmount : 0
     * DltAmount : 0
     * K3Amount : 0
     * FootBallAmount : 200
     * ElevenChoseFiveAmount : 0
     */

    private String mobile;
    private String headImg;
    private int totalAmount;
    private int SsqAmount;
    private int DltAmount;
    private int K3Amount;
    private int FootBallAmount;
    private int ElevenChoseFiveAmount;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getSsqAmount() {
        return SsqAmount;
    }

    public void setSsqAmount(int SsqAmount) {
        this.SsqAmount = SsqAmount;
    }

    public int getDltAmount() {
        return DltAmount;
    }

    public void setDltAmount(int DltAmount) {
        this.DltAmount = DltAmount;
    }

    public int getK3Amount() {
        return K3Amount;
    }

    public void setK3Amount(int K3Amount) {
        this.K3Amount = K3Amount;
    }

    public int getFootBallAmount() {
        return FootBallAmount;
    }

    public void setFootBallAmount(int FootBallAmount) {
        this.FootBallAmount = FootBallAmount;
    }

    public int getElevenChoseFiveAmount() {
        return ElevenChoseFiveAmount;
    }

    public void setElevenChoseFiveAmount(int ElevenChoseFiveAmount) {
        this.ElevenChoseFiveAmount = ElevenChoseFiveAmount;
    }
}
