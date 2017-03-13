package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.XessConfig;
import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.entity.CommonEntity;
import com.golive.xess.merchant.model.entity.OrdersEntity;
import com.golive.xess.merchant.model.entity.UserInfo;

import java.util.List;

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
    public void query(RequestBody data) {
        apiService.getOrders(data).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<CommonEntity<List<OrdersEntity>>, Boolean>() {
                    @Override
                    public Boolean call(CommonEntity<List<OrdersEntity>> listCommonEntity) {
                        String code = listCommonEntity.getCode();
                        String msg = listCommonEntity.getMsg();
                        view.showOnFailure(new Exception(msg), BetContract.TYPEORDER);
                        return Integer.parseInt(code) == 0;
                    }
                })
                .flatMap(new Func1<CommonEntity<List<OrdersEntity>>, Observable<List<OrdersEntity>>>() {
                    @Override
                    public Observable<List<OrdersEntity>> call(CommonEntity<List<OrdersEntity>> deviceEntityCommonEntity) {
                        return Observable.just(deviceEntityCommonEntity.getData());
                    }
                }, new Func1<Throwable, Observable<? extends List<OrdersEntity>>>() {
                    @Override
                    public Observable<? extends List<OrdersEntity>> call(Throwable throwable) {
                        view.showOnFailure(throwable, BetContract.TYPEORDER);
                        return null;
                    }
                }, new Func0<Observable<? extends List<OrdersEntity>>>() {
                    @Override
                    public Observable<? extends List<OrdersEntity>> call() {
                        return null;
                    }
                })
                .subscribe(new Action1<List<OrdersEntity>>() {
                    @Override
                    public void call(List<OrdersEntity> list) {
                        view.successQuery(list);
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
