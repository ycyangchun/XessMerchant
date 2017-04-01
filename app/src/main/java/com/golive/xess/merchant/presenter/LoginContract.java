package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.api.body.LoginBody;
import com.golive.xess.merchant.model.entity.LoginEntity;

/**
 * Created by YangChun .
 * on 2017/3/14.
 * 登录
 */

public interface LoginContract {
    interface Presenter{
        void login(LoginBody body);
    }
    interface  View{
        void showOnFailure(Throwable throwable);
        void successLogin(LoginEntity loginEntity);
    }
}
