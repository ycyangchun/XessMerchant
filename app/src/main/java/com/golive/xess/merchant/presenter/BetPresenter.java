package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.api.body.BetBody;
import com.golive.xess.merchant.model.entity.PageEntity;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by YangChun .
 * on 2017/3/10.
 */

public class BetPresenter implements BetContract.Presenter {
    private BetContract.View view;
    private ApiService apiService;

    @Inject
    public BetPresenter(ApiService apiService, BetContract.View view) {
        this.apiService = apiService;
        this.view = view;
    }

    @Override
    public void batchPay() {

    }

    @Override
    public void query(BetBody data) {
        apiService.getOrders(data).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PageEntity<List<LinkedTreeMap>>>() {
                    @Override
                    public void call(PageEntity<List<LinkedTreeMap>> list) {
                        String code = list.getCode();
                        String msg = list.getMsg();
                        if("0".equals(code)) {
                            view.successQuery((List<LinkedTreeMap>)list.getData().getOrders());
                        } else
                            view.showOnFailure(new Throwable(msg),BetContract.TYPEORDER);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.showOnFailure(throwable, BetContract.TYPEORDER);
                    }
                });
    }

    @Override
    public void statement() {

    }

    @Override
    public void detail() {

    }
}
