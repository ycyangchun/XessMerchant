package com.golive.xess.merchant.di.modules;


import android.content.Context;

import com.golive.xess.merchant.XessConfig;
import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.api.JsonConverterFactory;
import com.golive.xess.merchant.model.api.NoNetworkException;
import com.golive.xess.merchant.model.api.StringConverterFactory;
import com.golive.xess.merchant.model.entity.LiveNetworkMonitor;
import com.golive.xess.merchant.model.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YangChun .
 * on 2017/3/6.
 */
@Module
public class NetModule {
    private Context context;

    public NetModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public LiveNetworkMonitor provideLiveNetworkMonitor(){
        return new LiveNetworkMonitor(context);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(final LiveNetworkMonitor networkMonitor) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(XessConfig.IS_DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        Interceptor networkInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                boolean connected = networkMonitor.isConnected();
                if (connected) {
                    return chain.proceed(chain.request());
                } else {
                    throw new NoNetworkException("网络未连接，请查看网络设置");
                }
            }
        };
        OkHttpClient okhttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(networkInterceptor)
                .build();
        return okhttpClient;
    }
    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okhttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okhttpClient)
                .baseUrl(XessConfig.getServerUrl())
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(JsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }
    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
}
