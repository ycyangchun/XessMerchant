package com.golive.xess.merchant.personter;


import java.util.List;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/22
 * owspace
 */
public interface SplashContract {
    interface Presenter {
        void getSplash(String deviceId);
    }
    interface View {
        void showOnFailure(Throwable throwable);
        void successLoad();
    }
}
