package com.golive.xess.merchant.model.entity;

import java.util.List;

/**
 * Created by YangChun .
 * on 2017/3/15.
 */

public class LoginEntity {


    /**
     * storeUid : 10000
     * storeNo : 6AC3559BDF3C4B90AF68AA055DD4BA20
     * storeAccountNo : 7AB2C7943ABC44DEB0D5FE2816AC4F9B
     * name : 黄金
     * kidneyBean : 200
     * clientIp : 123.121.70.197
     * headImg : http://211.99.241.7/wxqrcode/2017032011489978870906i.jpg
     * province : 110000
     * city : 110105
     * createTime : 2017-03-13 19:17:54:000
     * updateTime : 2017-03-20 11:02:07:718
     * areas : [{"code":"110000","name":"北京市"},{"code":"110105","name":"朝阳区"}]
     */

    private int storeUid;
    private String storeNo;
    private String storeAccountNo;
    private String name;
    private int kidneyBean;
    private String clientIp;
    private String headImg;
    private String province;
    private String city;
    private String createTime;
    private String updateTime;
    private List<AreasBean> areas;

    public int getStoreUid() {
        return storeUid;
    }

    public void setStoreUid(int storeUid) {
        this.storeUid = storeUid;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getStoreAccountNo() {
        return storeAccountNo;
    }

    public void setStoreAccountNo(String storeAccountNo) {
        this.storeAccountNo = storeAccountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKidneyBean() {
        return kidneyBean;
    }

    public void setKidneyBean(int kidneyBean) {
        this.kidneyBean = kidneyBean;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
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

    public List<AreasBean> getAreas() {
        return areas;
    }

    public void setAreas(List<AreasBean> areas) {
        this.areas = areas;
    }

    public static class AreasBean {
        /**
         * code : 110000
         * name : 北京市
         */

        private String code;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String getAddress(){
        String address = "";
        if(areas != null) {
            address += areas.get(0).getName()+" "+areas.get(1).getName();
        }
        return address;
    }
}
