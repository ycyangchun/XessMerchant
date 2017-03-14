package com.golive.xess.merchant.model.api.body;

/**
 * Created by YangChun .
 * on 2017/3/14.
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
    private String winState;
    private String startTime;
    private String endTime;
    private String pageNo;
    private String pageSize;

    public BetBody(String storeNo, String deviceNo, String userNo, String pageNo, String pageSize) {
        this.storeNo = storeNo;
        this.deviceNo = deviceNo;
        this.userNo = userNo;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
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
