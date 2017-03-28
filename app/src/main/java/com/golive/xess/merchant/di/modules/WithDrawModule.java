package com.golive.xess.merchant.di.modules;

import com.golive.xess.merchant.presenter.WalletContract;
import com.golive.xess.merchant.presenter.WithDrawContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by YangChun .
 * on 2017/3/9.
 */

@Module
public class WithDrawModule {
    WithDrawContract.View view;

    public WithDrawModule(WithDrawContract.View view) {
        this.view = view;
    }
    @Provides
    public WithDrawContract.View provideView(){
        return view;
    }
}
