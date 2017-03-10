package com.golive.xess.merchant.di.modules;

import com.golive.xess.merchant.presenter.WalletContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by YangChun .
 * on 2017/3/9.
 */

@Module
public class WalletModule {
    WalletContract.View view;

    public WalletModule(WalletContract.View view) {
        this.view = view;
    }
    @Provides
    public WalletContract.View provideView(){
        return view;
    }
}
