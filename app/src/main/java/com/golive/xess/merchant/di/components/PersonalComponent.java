package com.golive.xess.merchant.di.components;

import com.golive.xess.merchant.di.modules.PersonalModule;
import com.golive.xess.merchant.di.scopes.UserScope;
import com.golive.xess.merchant.view.PersonInfoFragment;

import dagger.Component;

/**
 * Created by YangChun .
 * on 2017/3/8.
 */
@UserScope
@Component(modules = PersonalModule.class, dependencies = NetComponent.class)
public interface PersonalComponent {
    void inject(PersonInfoFragment personInfoFragment);
}
