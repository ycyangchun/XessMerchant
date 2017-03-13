package com.golive.xess.merchant.di.components;

import com.golive.xess.merchant.di.modules.BetDetailModule;
import com.golive.xess.merchant.di.scopes.UserScope;
import com.golive.xess.merchant.presenter.BetDetailContract;
import com.golive.xess.merchant.view.DialogBetDetailActivity;

import dagger.Component;

/**
 * Created by YangChun .
 * on 2017/3/9.
 */
@UserScope
@Component (modules = BetDetailModule.class, dependencies = NetComponent.class)
public interface BetDetailComponent {
    void inject(DialogBetDetailActivity dialogBetDetailActivity);
}
