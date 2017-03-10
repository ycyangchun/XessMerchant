package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.api.ApiService;

import javax.inject.Inject;

/**
 * Created by YangChun .
 * on 2017/3/10.
 */

public class BetPresenter implements BetContract.Presenter {
    private BetContract.View view;
    private ApiService apiService;

    @Inject
    public BetPresenter(ApiService apiService, BetContract.View view) {
        this.apiService = apiService;
        this.view = view;
    }

    @Override
    public void batchPay() {

    }

    @Override
    public void query(String[] arr) {
        view.successQuery();
    }

    @Override
    public void statement() {

    }

    @Override
    public void detail() {

    }
}
