package com.golive.xess.merchant.model.entity;

/**
 * Created by YangChun .
 * on 2017/3/13.
 * 钱包记录
 */

public class WalletLogEntity {

    /**
     * storeNo : 6AC3559BDF3C4B90AF68AA055DD4BA20
     * actionType : 2
     * peaConsume : 1000
     * createTime : 2017-03-09 16:20:10:000
     * updateTime : 2017-03-09 16:20:12:000
     * actionTypeDesc : 充值
     * 1投注 2充值 3零花钱兑换 4消费 5提现 6退款 7返奖8注册赠送
     */

    private String storeNo;
    private String actionType;
    private int peaConsume;
    private String createTime;
    private String updateTime;
    private String actionTypeDesc;

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public int getPeaConsume() {
        return peaConsume;
    }

    public void setPeaConsume(int peaConsume) {
        this.peaConsume = peaConsume;
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

    public String getActionTypeDesc() {
        return actionTypeDesc;
    }

    public void setActionTypeDesc(String actionTypeDesc) {
        this.actionTypeDesc = actionTypeDesc;
    }
}
