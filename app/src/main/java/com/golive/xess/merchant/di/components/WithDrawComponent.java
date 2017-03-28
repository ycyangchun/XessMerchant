package com.golive.xess.merchant.di.components;

import com.golive.xess.merchant.di.modules.WalletModule;
import com.golive.xess.merchant.di.modules.WithDrawModule;
import com.golive.xess.merchant.di.scopes.UserScope;
import com.golive.xess.merchant.presenter.WithDrawContract;
import com.golive.xess.merchant.view.WalletFragment;
import com.golive.xess.merchant.view.widget.CommonDialog;

import dagger.Component;

/**
 * Created by YangChun .
 * on 2017/3/9.
 */
@UserScope
@Component (modules = WithDrawModule.class, dependencies = NetComponent.class)
public interface WithDrawComponent {
    void inject(CommonDialog commonDialog);
}
