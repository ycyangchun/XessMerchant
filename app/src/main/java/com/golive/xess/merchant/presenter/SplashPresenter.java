package com.golive.xess.merchant.presenter;

import android.content.Context;

import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.NetComponent;
import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.entity.CommonEntity;
import com.golive.xess.merchant.model.entity.DeviceEntity;
import com.golive.xess.merchant.utils.AppUtil;
import com.golive.xess.merchant.utils.DeviceUtils;
import com.golive.xess.merchant.utils.SharedPreferencesUtils;

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


    public void updateDevice(Context context){
        final String deviceNo = DeviceUtils.getDeviceNo(context);
        apiService.devicesAuto(AppUtil.getPhoneProduct(),AppUtil.getBuildLevel() + "",AppUtil.getBuildVersion(),AppUtil.getDeviceId(context)
                ,AppUtil.getMacByWifi(context),AppUtil.getMacByBlue(),AppUtil.getDisplay(context),AppUtil.getPhoneBrand(),AppUtil.getPhoneModel()
                ,deviceNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonEntity<DeviceEntity>>() {
                    @Override
                    public void call(CommonEntity<DeviceEntity> deviceEntity) {
                        String code = deviceEntity.getCode();
                        String msg = deviceEntity.getMsg();
                        if("0".equals(code)) {
                            SharedPreferencesUtils.put("deviceNo", deviceNo);
                            view.successLoad(code);
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
