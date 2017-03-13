package com.golive.xess.merchant.model.api;


import com.golive.xess.merchant.model.entity.CommonEntity;
import com.golive.xess.merchant.model.entity.DeviceEntity;
import com.golive.xess.merchant.model.entity.SplashEntity;
import com.golive.xess.merchant.model.entity.UserInfo;

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
    /**
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
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/getStoreInfo")
    Observable<CommonEntity<UserInfo>> getStoreInfo(@Body RequestBody data);
}
