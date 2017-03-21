package com.golive.xess.merchant.presenter;


import android.content.Context;

import com.golive.xess.merchant.model.entity.DeviceEntity;

/**
 *
 */
public interface SplashContract {
    interface Presenter {
        void updateDevice(Context context);
        void syncDevice(Context context);
    }
    interface View {
        void showOnFailure(Throwable throwable);
        void successLoad(String code);
        void successSync(String lhq);
    }
}
