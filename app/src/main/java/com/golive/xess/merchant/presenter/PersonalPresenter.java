package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.XessConfig;
import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.api.body.LoginBody;
import com.golive.xess.merchant.model.api.body.StoreBody;
import com.golive.xess.merchant.model.api.body.UserBody;
import com.golive.xess.merchant.model.entity.CommonEntity;
import com.golive.xess.merchant.model.entity.DeviceEntity;
import com.golive.xess.merchant.model.entity.LoginEntity;
import com.golive.xess.merchant.model.entity.UserInfo;
import com.golive.xess.merchant.utils.AppUtil;
import com.golive.xess.merchant.utils.Base64Util;
import com.golive.xess.merchant.utils.Des3Util;
import com.golive.xess.merchant.utils.SharedPreferencesUtils;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.RequestBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by YangChun .
 * on 2017/3/8.
 */

public class PersonalPresenter implements PersonalContract.Persenter {
    private PersonalContract.View view;
    private ApiService apiService;

    @Inject
    public PersonalPresenter(PersonalContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }


    @Override
    public void updateStore(StoreBody body) {
        apiService.updateStoreAccount(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonEntity>() {
                    @Override
                    public void call(CommonEntity deviceEntity) {
                        try {
                            String code = deviceEntity.getCode();
                            String msg = deviceEntity.getCode();
                            if("0".equals(code)) {
                                view.successUpload(msg);
                            }else
                                view.showOnFailure(new Throwable(msg));
                        } catch (Exception e) {
                            view.showOnFailure(e);
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.showOnFailure(throwable);
                    }
                });
    }

    @Override
    public void initViewData(LoginBody data) {
        apiService.storeLogin(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonEntity<LoginEntity>>() {
                    @Override
                    public void call(CommonEntity<LoginEntity> commonEntity) {
                        String code = commonEntity.getCode();
                        String msg = commonEntity.getMsg();
                        if("0".equals(code)) {
                            view.successLoad(commonEntity.getData());
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

    @Override
    public void submitEdit() {
        view.successEdit();
    }
}
