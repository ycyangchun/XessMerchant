package com.golive.xess.merchant.model.api.body;

/**
 * Created by YangChun .
 * on 2017/3/18.
 */

public class StoreBody {

    /**
     * storeUid : 10000
     * deviceNo :
     * headImg :
     * name :
     * province :
     * city :
     * fileSuffix : jpg
     * fileType : I
     */

    private String storeUid;
    private String deviceNo;
    private String headImg;
    private String name;
    private String province;
    private String city;
    private String fileSuffix;
    private String fileType;

    public String getStoreUid() {
        return storeUid;
    }

    public void setStoreUid(String storeUid) {
        this.storeUid = storeUid;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
