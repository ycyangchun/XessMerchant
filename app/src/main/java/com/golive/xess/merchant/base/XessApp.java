package com.golive.xess.merchant.base;


import android.app.Application;
import android.content.Context;

import com.golive.xess.merchant.XessConfig;
import com.golive.xess.merchant.di.components.DaggerNetComponent;
import com.golive.xess.merchant.di.components.NetComponent;
import com.golive.xess.merchant.di.modules.NetModule;
import com.golive.xess.merchant.utils.PathUtils;
import com.golive.xess.merchant.utils.SharedPreferencesUtils;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by yc on 2017/3/6.
 * app
 */

public class XessApp extends Application {
    public static XessApp instance;
    private NetComponent netComponent;
    public static  XessApp getInstance(){
        return instance;
    }
    public static XessApp get(Context context) {
        return (XessApp) context.getApplicationContext();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initLog();
        initNet();
        initPath();
        initDadaBase();
        JPushInterface.setDebugMode(XessConfig.IS_DEBUG);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);                          // 初始化 JPush
    }

    private void initDadaBase() {
    }


    private void initPath() {
        SharedPreferencesUtils.init(this);
        PathUtils.getInstance(this, "xess");
    }


    //net
    private void initNet() {
        netComponent = DaggerNetComponent.builder()
                .netModule(new NetModule(this))
                .build();
    }

    //logger
    private void initLog() {
        LogLevel logLevel;
        if (XessConfig.IS_DEBUG) {
            logLevel = LogLevel.FULL;
        } else {
            logLevel = LogLevel.NONE;
        }
        Logger.init("xess")
                .methodCount(3)
                .logLevel(logLevel);
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }
}
