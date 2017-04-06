package com.golive.xess.merchant.model.api.body;

/**
 * Created by YangChun .
 * on 2017/3/14.
 * 投注记录 列表
 */

public class BetBody {

    /**
     * lid :
     * issue :
     * userNo :
     * mobile :
     * storeNo :
     * deviceNo : 95A32E080CB74C638C5E7471D6C21EE3
     * channelNo :
     * winState :
     * startTime :
     * endTime :
     * pageNo : 0
     * pageSize : 10
     */

    private String lid;
    private String issue;
    private String userNo;
    private String mobile;
    private String storeNo;
    private String deviceNo;
    private String channelNo;
    private String startTime;
    private String endTime;
    private String pageNo;
    private String pageSize;
    private String storeUid;
    /*
           需代付  invest_state : 10210  origin：1
           已代付   invest_state ：10200  origin：1
           中奖订单  invest_state: ：10200   order_state: 10300  win_state: 10400 origin：1
           未开奖订单  invest_state: ：10200   order_state: 10300  win_state: 10401 origin：1
    */
    private String investState;
    private String orderState;
    private String winState;
    private String origin;

    public BetBody(String storeUid ,String deviceNo,String pageNo, String pageSize) {
        this.storeUid = storeUid;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.deviceNo = deviceNo;
    }

    //获取列表数据 参数
    public void setParam(String invest_state, String order_state, String win_state, String origin) {
        this.investState = invest_state;
        this.orderState = order_state;
        this.winState = win_state;
        this.origin = origin;
    }

    // 输入条件查询
    public void conditionParam(String startTime, String endTime, String mobile, String lid) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.mobile = mobile;
        this.lid = lid;
    }

    // 输入条件查询 后清空
    public void clearConditionParam(){
        this.startTime = "";
        this.endTime = "";
        this.mobile = "";
        this.lid = "";
    }

    public String getInvestState() {
        return investState;
    }

    public void setInvestState(String investState) {
        this.investState = investState;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getStoreUid() {
        return storeUid;
    }

    public void setStoreUid(String storeUid) {
        this.storeUid = storeUid;
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

    public String getWinState() {
        return winState;
    }

    public void setWinState(String winState) {
        this.winState = winState;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
