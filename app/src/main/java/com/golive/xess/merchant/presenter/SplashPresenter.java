package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.entity.SplashEntity;
import com.golive.xess.merchant.utils.TimeUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 *
 */
public class SplashPresenter implements SplashContract.Presenter{
    private SplashContract.View view;
    private ApiService apiService;

    @Inject
    public SplashPresenter(SplashContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void getSplash(String deviceId) {
        String client = "android";
        String version = "1.3.0";
        Long time = TimeUtil.getCurrentSeconds();
        apiService.getSplash(client, version, time, deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<SplashEntity, Observable<String>>() {
                    @Override
                    public Observable<String> call(SplashEntity splashEntity) {
                        List<String> list = splashEntity.getImages();
                        String[] arr = list.toArray(new String[list.size()]);
                        return Observable.from(arr);
                    }
                }, new Func1<Throwable, Observable<? extends String>>() {
                    @Override
                    public Observable<? extends String> call(Throwable throwable) {
                        view.showOnFailure(throwable);
                        return null;
                    }
                }, new Func0<Observable<? extends String>>() {
                    @Override
                    public Observable<? extends String> call() {
                        return null;
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                        view.successLoad();
                    }
                });


    }

}
