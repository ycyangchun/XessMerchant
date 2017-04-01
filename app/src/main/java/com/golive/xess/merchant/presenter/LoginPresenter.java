package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.api.body.LoginBody;
import com.golive.xess.merchant.model.entity.CommonEntity;
import com.golive.xess.merchant.model.entity.LoginEntity;
import com.golive.xess.merchant.utils.MerchantUtils;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by YangChun .
 * on 2017/3/14.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private ApiService apiService;

    @Inject
    public LoginPresenter(LoginContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void login(final LoginBody body ) {
        apiService.storeLogin(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonEntity<LoginEntity>>() {
                    @Override
                    public void call(CommonEntity<LoginEntity> loginEntityCommonEntity) {
                        String code = loginEntityCommonEntity.getCode();
                        String msg = loginEntityCommonEntity.getMsg();
                        if("0".equals(code)) {
                            LoginEntity login = loginEntityCommonEntity.getData();
                            MerchantUtils.setStoreNo(login.getStoreNo());
                            MerchantUtils.setStoreUid(login.getStoreUid()+"");
                            MerchantUtils.setPassword(body.getPassword());
                            view.successLogin(login);
                        }else
                            view.showOnFailure(new Throwable(msg));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.showOnFailure(throwable);
                    }
                });
    }

}
