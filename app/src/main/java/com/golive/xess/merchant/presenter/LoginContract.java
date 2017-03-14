package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.entity.UserInfo;

/**
 * Created by YangChun .
 * on 2017/3/14.
 * 登录
 */

public interface LoginContract {
    interface Presenter{
        void getVerification();
        void login();
    }
    interface  View{
        void showOnFailure(Throwable throwable);
        void successLogin(UserInfo UserInfo);
        void successVerification(String verification);
    }
}
