package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.api.ApiService;

import javax.inject.Inject;

/**
 * Created by YangChun .
 * on 2017/3/9.
 */

public class WalletPresenter implements WalletContract.Persenter {
    private WalletContract.View view;
    private ApiService apiService;

    @Inject
    public WalletPresenter(WalletContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void getWalletData() {
        view.dataSuccess();
    }
}
