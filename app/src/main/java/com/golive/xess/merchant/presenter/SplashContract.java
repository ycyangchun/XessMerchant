package com.golive.xess.merchant.presenter;


import com.golive.xess.merchant.model.api.body.DeviceBody;
import com.golive.xess.merchant.model.entity.DeviceEntity;

import okhttp3.RequestBody;

/**
 *
 */
public interface SplashContract {
    interface Presenter {
        void getSplash(String deviceId);
        void deviceAuth(DeviceBody data);
    }
    interface View {
        void showOnFailure(Throwable throwable);
        void successLoad(DeviceEntity deviceEntity);
    }
}
