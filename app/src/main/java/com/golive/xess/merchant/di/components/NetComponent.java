package com.golive.xess.merchant.di.components;


import com.golive.xess.merchant.di.modules.NetModule;
import com.golive.xess.merchant.model.api.ApiService;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 *
 */
@Component(modules = NetModule.class)
@Singleton
public interface NetComponent {
    ApiService getApiService();

    OkHttpClient getOkHttp();

    Retrofit getRetrofit();
}
