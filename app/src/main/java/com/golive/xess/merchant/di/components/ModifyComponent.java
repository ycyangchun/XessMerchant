package com.golive.xess.merchant.di.components;

import com.golive.xess.merchant.di.modules.LoginModule;
import com.golive.xess.merchant.di.modules.ModifyModule;
import com.golive.xess.merchant.di.scopes.UserScope;
import com.golive.xess.merchant.view.LoginActivity;
import com.golive.xess.merchant.view.ModifyActivity;

import dagger.Component;

/**
 * Created by YangChun .
 * on 2017/3/9.
 */
@UserScope
@Component (modules = ModifyModule.class, dependencies = NetComponent.class)
public interface ModifyComponent {
    void inject(ModifyActivity modifyActivity);
}
