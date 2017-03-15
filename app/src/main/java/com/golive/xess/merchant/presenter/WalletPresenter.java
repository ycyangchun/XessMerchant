package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.XessConfig;
import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.api.body.WalletBody;
import com.golive.xess.merchant.model.api.body.WalletLogsBody;
import com.golive.xess.merchant.model.entity.CommonEntity;
import com.golive.xess.merchant.model.entity.PageEntity;
import com.golive.xess.merchant.model.entity.WalletEntity;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

import javax.inject.Inject;

import okhttp3.RequestBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by YangChun .
 * on 2017/3/9.
 */

public class WalletPresenter implements WalletContract.Persenter {
    private WalletContract.View view;
    private ApiService apiService;

    @Inject
    public WalletPresenter(WalletContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void getWalletInfo(WalletBody data) {
        Observable observable ;
        if (XessConfig._VERSION == XessConfig._PERSONAL)
            observable = apiService.getWallet(data);
        else
            observable = apiService.getWalletStore(data);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonEntity<WalletEntity>>() {
                    @Override
                    public void call(CommonEntity<WalletEntity> walletEntityCommonEntity) {

                        String code = walletEntityCommonEntity.getCode();
                        String msg = walletEntityCommonEntity.getMsg();
                        if("0".equals(code)) {
                            view.dataInfoSuccess(walletEntityCommonEntity.getData());
                        } else
                            view.dataFailed(new Throwable(msg), WalletContract.TYPEINFO);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.dataFailed(throwable, WalletContract.TYPEINFO);
                    }
                });
    }

    @Override
    public void getWalletLogs(WalletLogsBody data) {
        Observable observable ;
        if (XessConfig._VERSION == XessConfig._PERSONAL)
            observable = apiService.getWalletLogs(data);
        else
            observable = apiService.getWalletStoreLogs(data);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PageEntity<List<LinkedTreeMap>>>() {
                    @Override
                    public void call(PageEntity<List<LinkedTreeMap>> walletLogEntityPageEntity) {
                        String code = walletLogEntityPageEntity.getCode();
                        String msg = walletLogEntityPageEntity.getMsg();
                        if("0".equals(code)) {
                            view.dataLogsSuccess((List<LinkedTreeMap>) walletLogEntityPageEntity.getData().getAccountings());
                        } else
                            view.dataFailed(new Throwable(msg), WalletContract.TYPELOGS);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.dataFailed(throwable, WalletContract.TYPELOGS);
                    }
                });
    }

}
