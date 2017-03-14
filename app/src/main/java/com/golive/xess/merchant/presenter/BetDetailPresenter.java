package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.api.body.BetDetailBody;
import com.golive.xess.merchant.model.entity.CommonEntity;
import com.golive.xess.merchant.model.entity.OrdersEntity;

import javax.inject.Inject;

import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by YangChun .
 * on 2017/3/13.
 */

public class BetDetailPresenter implements BetDetailContract.Presenter {
    private BetDetailContract.View view;
    private ApiService apiService;

    @Inject
    public BetDetailPresenter(ApiService apiService, BetDetailContract.View view) {
        this.apiService = apiService;
        this.view = view;
    }

    @Override
    public void getDetail(BetDetailBody data) {
        apiService.getOrderDetail(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonEntity<OrdersEntity>>() {
                    @Override
                    public void call(CommonEntity<OrdersEntity> ordersEntityCommonEntity) {
                        view.successLoad(ordersEntityCommonEntity.getData());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.showOnFailure(throwable);
                    }
                });
    }
}
