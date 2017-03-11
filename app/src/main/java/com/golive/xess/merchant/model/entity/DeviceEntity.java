package com.golive.xess.merchant.model.entity;

/**
 * Created by YangChun .
 * on 2017/3/11.
 * 设备信息初始化
 */

public class DeviceEntity {

    /**
     * code : 0
     * msg : success
     * data : {"deviceNo":"95A32E080CB74C638C5E7471D6C21EE3","storeNo":"6AC3559BDF3C4B90AF68AA055DD4BA20","channelNo":"C1BF373010464B5FA04057831F218E53","deviceName":"TCL定制机","brand":"TCL","deviceModel":"4687553019","storeName":"全球播官方和彩","storeAlias":"Golive和彩","enStoreAlias":"golive_test_01","state":"0","kidneyBean":0,"channelName":"全球播自营渠道","enChannelName":"Golive"}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * deviceNo : 95A32E080CB74C638C5E7471D6C21EE3
         * storeNo : 6AC3559BDF3C4B90AF68AA055DD4BA20
         * channelNo : C1BF373010464B5FA04057831F218E53
         * deviceName : TCL定制机
         * brand : TCL
         * deviceModel : 4687553019
         * storeName : 全球播官方和彩
         * storeAlias : Golive和彩
         * enStoreAlias : golive_test_01
         * state : 0
         * kidneyBean : 0
         * channelName : 全球播自营渠道
         * enChannelName : Golive
         */

        private String deviceNo;
        private String storeNo;
        private String channelNo;
        private String deviceName;
        private String brand;
        private String deviceModel;
        private String storeName;
        private String storeAlias;
        private String enStoreAlias;
        private String state;
        private int kidneyBean;
        private String channelName;
        private String enChannelName;

        public String getDeviceNo() {
            return deviceNo;
        }

        public void setDeviceNo(String deviceNo) {
            this.deviceNo = deviceNo;
        }

        public String getStoreNo() {
            return storeNo;
        }

        public void setStoreNo(String storeNo) {
            this.storeNo = storeNo;
        }

        public String getChannelNo() {
            return channelNo;
        }

        public void setChannelNo(String channelNo) {
            this.channelNo = channelNo;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getDeviceModel() {
            return deviceModel;
        }

        public void setDeviceModel(String deviceModel) {
            this.deviceModel = deviceModel;
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

        public int getKidneyBean() {
            return kidneyBean;
        }

        public void setKidneyBean(int kidneyBean) {
            this.kidneyBean = kidneyBean;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public String getEnChannelName() {
            return enChannelName;
        }

        public void setEnChannelName(String enChannelName) {
            this.enChannelName = enChannelName;
        }
    }

    @Override
    public String toString() {
        return "DeviceEntity{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
