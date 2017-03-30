package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.api.body.AccountBody;
import com.golive.xess.merchant.model.api.body.BetBody;
import com.golive.xess.merchant.model.api.body.ReplacePayBody;
import com.golive.xess.merchant.model.entity.AccountEntity;
import com.golive.xess.merchant.model.entity.CommonEntity;
import com.golive.xess.merchant.model.entity.MarketEntity;
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
    public void batchPay(ReplacePayBody payBody) {
        apiService.batchAgentPay(payBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<CommonEntity<List<LinkedTreeMap>>>() {
                                @Override
                                public void call(CommonEntity<List<LinkedTreeMap>> list) {
                        String code = list.getCode();
                        String msg = list.getMsg();
                        if("0".equals(code)) {
                            view.successPay(list.getData());
                        } else
                            view.showOnFailure(new Throwable(msg),BetContract.TYPEPAY);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.showOnFailure(throwable, BetContract.TYPEPAY);
                    }
                });
    }

    @Override
    public void query(BetBody data) {
        apiService.getOrders(data).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PageEntity<List<LinkedTreeMap>>>() {
                    @Override
                    public void call(PageEntity<List<LinkedTreeMap>> pageEntity) {
                        String code = pageEntity.getCode();
                        String msg = pageEntity.getMsg();
                        if("0".equals(code)) {
                            view.successQuery((List<LinkedTreeMap>)pageEntity.getData().getOrders(),pageEntity.getData().getOther());
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
    public void account(AccountBody accountBody) {
        apiService.getAccount(accountBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonEntity<AccountEntity>>() {
                    @Override
                    public void call(CommonEntity<AccountEntity> accountEntity) {
                        String code = accountEntity.getCode();
                        String msg = accountEntity.getMsg();
                        if("0".equals(code)) {
                            view.successAccount(accountEntity.getData());
                        } else
                            view.showOnFailure(new Throwable(msg),BetContract.TYPEACCOUNT);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.showOnFailure(throwable, BetContract.TYPEACCOUNT);
                    }
                });
    }


    @Override
    public void market(ReplacePayBody payBody) {
        apiService.countNumAndAmountByTime(payBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonEntity<MarketEntity>>() {
                    @Override
                    public void call(CommonEntity<MarketEntity> marketEntityCommonEntity) {
                        String code = marketEntityCommonEntity.getCode();
                        String msg = marketEntityCommonEntity.getMsg();
                        if("0".equals(code)) {
                            view.successMarket(marketEntityCommonEntity.getData());
                        } else
                            view.showOnFailure(new Throwable(msg),BetContract.TYPEACCOUNT);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.showOnFailure(throwable, BetContract.TYPEDMARKET);
                    }
                });
    }
}
