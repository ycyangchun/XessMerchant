package com.golive.xess.merchant.di.components;

import com.golive.xess.merchant.di.modules.BetModule;
import com.golive.xess.merchant.di.modules.WalletModule;
import com.golive.xess.merchant.di.scopes.UserScope;
import com.golive.xess.merchant.presenter.BetContract;
import com.golive.xess.merchant.view.BetHistoryFragment;
import com.golive.xess.merchant.view.WalletFragment;

import dagger.Component;

/**
 * Created by YangChun .
 * on 2017/3/9.
 */
@UserScope
@Component (modules = BetModule.class, dependencies = NetComponent.class)
public interface BetComponent {
    void inject(BetHistoryFragment betHistoryFragment);
}
