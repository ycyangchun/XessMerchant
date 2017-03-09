package com.golive.xess.merchant.di.modules;

import com.golive.xess.merchant.presenter.PersonalContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by YangChun .
 * on 2017/3/8.
 */
@Module
public class PersonalModule {
    PersonalContract.View view;

    public PersonalModule(PersonalContract.View view) {
        this.view = view;
    }

    @Provides
    public PersonalContract.View provideView(){
        return  view;
    }
}
