package com.golive.xess.merchant.presenter;


import com.golive.xess.merchant.model.entity.DeviceEntity;

import okhttp3.RequestBody;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/22
 * owspace
 */
public interface SplashContract {
    interface Presenter {
        void getSplash(String deviceId);
        void deviceAuth(RequestBody data);
    }
    interface View {
        void showOnFailure(Throwable throwable);
        void successLoad(DeviceEntity deviceEntity);
    }
}
