package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.api.ApiService;

import javax.inject.Inject;

/**
 * Created by YangChun .
 * on 2017/3/14.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private ApiService apiService;

    @Inject
    public LoginPresenter(LoginContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void getVerification() {

    }

    @Override
    public void login() {

    }
}
