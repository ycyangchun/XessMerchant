package com.golive.xess.merchant.model.api;


import com.golive.xess.merchant.model.entity.SplashEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 *
 */
public interface ApiService {
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

}
