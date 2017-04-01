package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.api.body.ModifyBody;
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

public class ModifyPresenter implements ModifyContract.Presenter {
    private ModifyContract.View view;
    private ApiService apiService;

    @Inject
    public ModifyPresenter(ModifyContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void modify(final ModifyBody body) {
        apiService.modifyPwd(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonEntity<LoginEntity>>() {
                    @Override
                    public void call(CommonEntity<LoginEntity> accountEntity) {
                        String code = accountEntity.getCode();
                        String msg = accountEntity.getMsg();
                        if("0".equals(code)) {
                            MerchantUtils.setPassword(body.getNewPwd());
                            view.successModify(accountEntity.getData());
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
