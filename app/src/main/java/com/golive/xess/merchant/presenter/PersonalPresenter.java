package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.XessConfig;
import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.entity.CommonEntity;
import com.golive.xess.merchant.model.entity.DeviceEntity;
import com.golive.xess.merchant.model.entity.UserInfo;
import com.orhanobut.logger.Logger;

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
    public void initViewData(RequestBody data) {
        Observable<CommonEntity<UserInfo>> observable ;
        if (XessConfig._VERSION == XessConfig._PERSONAL)
            observable = apiService.getUserInfo(data);
        else
            observable = apiService.getStoreInfo(data);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<CommonEntity<UserInfo>, Observable<UserInfo>>() {
                    @Override
                    public Observable<UserInfo> call(CommonEntity<UserInfo> deviceEntityCommonEntity) {
                        return Observable.just(deviceEntityCommonEntity.getData());
                    }
                }, new Func1<Throwable, Observable<? extends UserInfo>>() {
                    @Override
                    public Observable<? extends UserInfo> call(Throwable throwable) {
                        view.showOnFailure(throwable);
                        return null;
                    }
                }, new Func0<Observable<? extends UserInfo>>() {
                    @Override
                    public Observable<? extends UserInfo> call() {
                        return null;
                    }
                })
                .subscribe(new Action1<UserInfo>() {
                    @Override
                    public void call(UserInfo userInfo) {
                        view.successLoad(userInfo);
                    }
                });
    }

    @Override
    public void submitEdit() {
        view.successEdit();
    }
}
