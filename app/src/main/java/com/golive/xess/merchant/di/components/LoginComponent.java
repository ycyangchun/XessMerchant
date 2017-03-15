package com.golive.xess.merchant.di.components;

import com.golive.xess.merchant.di.modules.BetModule;
import com.golive.xess.merchant.di.modules.LoginModule;
import com.golive.xess.merchant.di.scopes.UserScope;
import com.golive.xess.merchant.view.BetHistoryFragment;
import com.golive.xess.merchant.view.LoginActivity;

import dagger.Component;

/**
 * Created by YangChun .
 * on 2017/3/9.
 */
@UserScope
@Component (modules = LoginModule.class, dependencies = NetComponent.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
