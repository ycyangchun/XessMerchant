package com.golive.xess.merchant.di.modules;

import com.golive.xess.merchant.presenter.BetContract;
import com.golive.xess.merchant.presenter.WalletContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by YangChun .
 * on 2017/3/9.
 */

@Module
public class BetModule {
    BetContract.View view;

    public BetModule(BetContract.View view) {
        this.view = view;
    }
    @Provides
    public BetContract.View provideView(){
        return view;
    }
}
