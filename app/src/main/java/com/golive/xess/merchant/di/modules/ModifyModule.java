package com.golive.xess.merchant.di.modules;

import com.golive.xess.merchant.presenter.ModifyContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by YangChun .
 * on 2017/3/15.
 */
@Module
public class ModifyModule {
    ModifyContract.View view;

    public ModifyModule(ModifyContract.View view) {
        this.view = view;
    }
    @Provides
    public ModifyContract.View provideView(){
        return view;
    }
}
