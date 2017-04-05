package com.golive.xess.merchant.presenter;


import android.content.Context;

import com.golive.xess.merchant.model.entity.DeviceEntity;

/**
 *
 */
public interface SplashContract {
    interface Presenter {
        void updateDevice(Context context , String deviceNo);
        void syncDevice(Context context,String deviceNo);
        void checkOnline(Context context,String onlineNo);
    }
    interface View {
        void showOnFailure(Throwable throwable);
        void successLoad(String code);
        void successSync(String lhq);
    }
}
