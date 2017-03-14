package com.golive.xess.merchant.model.api;


import com.golive.xess.merchant.model.entity.CommonEntity;
import com.golive.xess.merchant.model.entity.DeviceEntity;
import com.golive.xess.merchant.model.entity.OrdersEntity;
import com.golive.xess.merchant.model.entity.PageEntity;
import com.golive.xess.merchant.model.entity.SplashEntity;
import com.golive.xess.merchant.model.entity.UserInfo;
import com.golive.xess.merchant.model.entity.WalletEntity;
import com.golive.xess.merchant.model.entity.WalletLogEntity;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 *
 */
public interface ApiService {
    String SECRET_KEY = "golive_lottery@123456#$%~";
    String SECRET_VALUE = "12345678";
    /** retrofit 测试用
     * <p>http://static.owspace.com/static/picture_list.txt?client=android&version=1.3.0&time=1467864021&device_id=866963027059338</p>
     *
     * @param client
     * @param version
     * @param time
     * @param deviceId
     * @return
     */
    @GET("static/picture_list.txt")
    Observable<SplashEntity> getSplash(@Query("client") String client, @Query("version") String version, @Query("time") Long time, @Query("device_id") String deviceId);

    /**
     *
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("lottery/doDeviceAuth")
    Observable<CommonEntity<DeviceEntity>> getDeviceAuth(@Body RequestBody data);

    /**
     * 7.7获取用户信息(POST)
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/getUserInfo")
    Observable<CommonEntity<UserInfo>> getUserInfo(@Body RequestBody data);


    /**
     * 7.8获取商家信息(POST)
     * 1．个人订单列表：设备编号+用户编号   deviceNo  userNo
     * 2．商家订单列表：设备编号+商家编号   deviceNo  storeNo
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/getStoreInfo")
    Observable<CommonEntity<UserInfo>> getStoreInfo(@Body RequestBody data);

     /**
     * 7.13订单列表(POST)
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/orders")
    Observable<PageEntity<List<LinkedTreeMap>>> getOrders(@Body RequestBody data);

    /**
     * 7.14订单详情(POST)
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/order")
    Observable<CommonEntity<OrdersEntity>> getOrderDetail(@Body RequestBody data);


    /**
     * 7.14我的钱包-用户(POST)
     *  1．个人：设备编号+用户编号   deviceNo  userNo
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/myWallet")
    Observable<CommonEntity<WalletEntity>> getWallet(@Body RequestBody data);

    /**
     * 7.15我的钱包-商家(POST)
     *  2．商家：设备编号+商家编号   deviceNo  storeNo
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/store/myWallet")
    Observable<CommonEntity<WalletEntity>> getWalletStore(@Body RequestBody data);
    /**
     * 7.17钱包记录-用户(POST)
     *  1．个人：设备编号+用户编号   deviceNo  userNo
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/getWalletLogs")
    Observable<PageEntity<List<LinkedTreeMap>>> getWalletLogs(@Body RequestBody data);

    /**
     * 7.18钱包记录-商家(POST)
     *  2．商家：设备编号+商家编号   deviceNo  storeNo
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/store/getWalletLogs")
    Observable<PageEntity<List<LinkedTreeMap>>> getWalletStoreLogs(@Body RequestBody data);

    /**
     * 7.11上传图片 (GET)
     */
    @GET("file/upload")
    Observable<String> uploadFile(@Query("fileData") String fileData,@Query("fileSuff") String fileSuff,@Query("fileType") String fileType);



}
