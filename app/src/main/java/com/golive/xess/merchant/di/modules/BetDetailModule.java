package com.golive.xess.merchant.di.modules;

import com.golive.xess.merchant.presenter.BetDetailContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by YangChun .
 * on 2017/3/9.
 */

@Module
public class BetDetailModule {
    BetDetailContract.View view;

    public BetDetailModule(BetDetailContract.View view) {
        this.view = view;
    }
    @Provides
    public BetDetailContract.View provideView(){
        return view;
    }
}
