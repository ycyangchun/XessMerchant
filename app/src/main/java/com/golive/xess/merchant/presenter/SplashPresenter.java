package com.golive.xess.merchant.presenter;

import android.content.Context;

import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.api.body.SyncBody;
import com.golive.xess.merchant.model.entity.CommonEntity;
import com.golive.xess.merchant.model.entity.SyncEntity;
import com.golive.xess.merchant.utils.AppUtil;
import com.golive.xess.merchant.utils.DeviceUtils;
import com.golive.xess.merchant.utils.MerchantUtils;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
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


    public void updateDevice(Context context , String deviceNo){
        apiService.devicesAuto(AppUtil.getPhoneProduct(),AppUtil.getBuildLevel() + "",AppUtil.getBuildVersion(),AppUtil.getDeviceId(context)
                ,AppUtil.getMacByWifi(),AppUtil.getMacByBlue(),AppUtil.getDisplay(context),AppUtil.getPhoneBrand(),AppUtil.getPhoneModel()
                ,deviceNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String deviceEntity) {
                        try {
                            JSONObject object = new JSONObject(deviceEntity);
                            String code = object.getString("code");
                            String msg = object.getString("msg");
                            if("0".equals(code)) {
                                MerchantUtils.setUpdateDevice(code);
                                view.successLoad(code);
                            }else
                                view.showOnFailure(new Throwable("设备信息上传-"+msg));
                        } catch (JSONException e) {
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
    public void syncDevice(Context context,String deviceNo) {
        SyncBody body = new SyncBody(deviceNo,AppUtil.getMacByWifi());
        apiService.syncUserInfo(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonEntity<SyncEntity>>() {
                    @Override
                    public void call(CommonEntity<SyncEntity> syncEntityCommonEntity) {
                        String code = syncEntityCommonEntity.getCode();
                        String msg = syncEntityCommonEntity.getMsg();
                        if("0".equals(code)) {
                            String lhq = syncEntityCommonEntity.getData().getLhqId();
                            MerchantUtils.setLhqId(lhq);
                            view.successSync(lhq);
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
