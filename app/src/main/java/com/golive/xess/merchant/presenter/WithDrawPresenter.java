package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.api.body.BindCardBody;
import com.golive.xess.merchant.model.api.body.WithdrawBody;
import com.golive.xess.merchant.model.entity.BindOrChangerCard;
import com.golive.xess.merchant.model.entity.CommonEntity;
import com.golive.xess.merchant.model.entity.PayEvent;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.golive.xess.merchant.presenter.WithDrawContract.DIALOG_STATUS_CARD;
import static com.golive.xess.merchant.presenter.WithDrawContract.DIALOG_STATUS_CARD_AFFIRM;

/**
 * Created by YangChun .
 * on 2017/3/9.
 */

public class WithDrawPresenter implements WithDrawContract.Presenter{
    private WithDrawContract.View view;
    private ApiService apiService;

    @Inject
    public WithDrawPresenter(WithDrawContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void withDraw(final WithdrawBody data) {
        apiService.storeWithdraw(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonEntity<PayEvent>>() {
                    @Override
                    public void call(CommonEntity<PayEvent> payEventCommonEntity) {
                        String code = payEventCommonEntity.getCode();
                        String msg = payEventCommonEntity.getMsg();
                        if("0".equals(code)) {
                            view.successWithDraw(payEventCommonEntity.getData() ,data.getKidneyBean(), data.getType());
                        }else
                            view.showOnFailure(new Throwable(msg), DIALOG_STATUS_CARD_AFFIRM);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.showOnFailure(throwable, DIALOG_STATUS_CARD_AFFIRM );
                    }
                });
    }

    @Override
    public void bindCard(BindCardBody data, final String commission) {
        apiService.bindCard(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonEntity<BindOrChangerCard>>() {
                    @Override
                    public void call(CommonEntity<BindOrChangerCard> bindCardEntityCommonEntity) {
                        String code = bindCardEntityCommonEntity.getCode();
                        String msg = bindCardEntityCommonEntity.getMsg();
                        if("0".equals(code)) {
                            view.successBindOrChangeCard(bindCardEntityCommonEntity.getData(),commission);
                        }else
                            view.showOnFailure(new Throwable(msg), DIALOG_STATUS_CARD);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.showOnFailure(throwable, DIALOG_STATUS_CARD);
                    }
                });
    }

    @Override
    public void changeCard(BindCardBody data, final String commission) {
        apiService.changeCard(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonEntity<BindOrChangerCard>>() {
                    @Override
                    public void call(CommonEntity<BindOrChangerCard> bindCardEntityCommonEntity) {
                        String code = bindCardEntityCommonEntity.getCode();
                        String msg = bindCardEntityCommonEntity.getMsg();
                        if("0".equals(code)) {
                            view.successBindOrChangeCard(bindCardEntityCommonEntity.getData(),commission);
                        }else
                            view.showOnFailure(new Throwable(msg), DIALOG_STATUS_CARD);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.showOnFailure(throwable, DIALOG_STATUS_CARD);
                    }
                });
    }
}
