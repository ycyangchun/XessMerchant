package com.golive.xess.merchant.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseActivity;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerSplashComponent;
import com.golive.xess.merchant.di.modules.SplashModule;
import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.api.NoNetworkException;
import com.golive.xess.merchant.model.entity.DeviceEntity;
import com.golive.xess.merchant.presenter.SplashContract;
import com.golive.xess.merchant.presenter.SplashPresenter;
import com.golive.xess.merchant.utils.Base64Util;
import com.golive.xess.merchant.utils.Des3Util;
import com.golive.xess.merchant.utils.DisplayUtils;
import com.golive.xess.merchant.utils.SharedPreferencesUtils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;


public class SplashActivity extends BaseActivity implements SplashContract.View {


    @BindView(R.id.textView)
    TextView textView;
    @Inject
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        DaggerSplashComponent.builder()
                .netComponent(XessApp.get(this).getNetComponent())
                .splashModule(new SplashModule(this))
                .build().inject(this);
//        presenter.getSplash("");
        Map<String,String> map = new HashMap<>();
        map.put("deviceNo","95A32E080CB74C638C5E7471D6C21EE3");
        String ss = new Gson().toJson(map);
        String data  = null;
        try {
            data = Base64Util.encode(Des3Util.getInstance(ApiService.SECRET_KEY, ApiService.SECRET_VALUE).encode(ss));
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestBody requestBody =
                RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                        data);
        presenter.deviceAuth(requestBody);

    }


    @Override
    public void showOnFailure(Throwable throwable) {
        if ( throwable instanceof NoNetworkException) {
            //'no network'
            Toast.makeText(this,throwable.getMessage()+" No Network Connection",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,throwable.getMessage()+" Some Other Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void successLoad(DeviceEntity device) {
        if(device != null){
            Logger.d(device.getDeviceNo());
            SharedPreferencesUtils.put("deviceNo",device.getDeviceNo());
            SharedPreferencesUtils.put("storeNo",device);

            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
