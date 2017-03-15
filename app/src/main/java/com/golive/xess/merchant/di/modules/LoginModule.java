package com.golive.xess.merchant.di.modules;

import com.golive.xess.merchant.presenter.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by YangChun .
 * on 2017/3/15.
 */
@Module
public class LoginModule {
    LoginContract.View view;

    public LoginModule(LoginContract.View view) {
        this.view = view;
    }
    @Provides
    public LoginContract.View provideView(){
        return view;
    }
}
