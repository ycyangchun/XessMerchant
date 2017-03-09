package com.golive.xess.merchant.di.components;


import com.golive.xess.merchant.di.modules.SplashModule;
import com.golive.xess.merchant.di.scopes.UserScope;
import com.golive.xess.merchant.view.SplashActivity;

import dagger.Component;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/10/25
 * owspace
 */
@UserScope
@Component(modules = SplashModule.class,dependencies = NetComponent.class)
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
