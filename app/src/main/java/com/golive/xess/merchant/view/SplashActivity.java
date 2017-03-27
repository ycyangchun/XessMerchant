package com.golive.xess.merchant.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseActivity;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerSplashComponent;
import com.golive.xess.merchant.di.modules.SplashModule;
import com.golive.xess.merchant.model.api.NoNetworkException;
import com.golive.xess.merchant.presenter.SplashContract;
import com.golive.xess.merchant.presenter.SplashPresenter;
import com.golive.xess.merchant.utils.SharedPreferencesUtils;
import com.golive.xess.merchant.view.widget.DialogErr;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


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
        presenter.updateDevice(this);
        presenter.syncDevice(this);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


    @Override
    public void showOnFailure(Throwable throwable) {
       /* if ( throwable instanceof NoNetworkException) {
            //'no network'
            Toast.makeText(this,throwable.getMessage()+" No Network Connection",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,throwable.getMessage()+" Some Other Error", Toast.LENGTH_SHORT).show();
        }*/
        if("设备信息上传-参数异常".equals(throwable.getMessage())) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else
            new DialogErr(this,throwable.getMessage()).show();
    }

    @Override
    public void successLoad(String code) {
        /*if(!TextUtils.isEmpty(password)) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }*/
//        startActivity(new Intent(this, LoginActivity.class));
//        finish();
    }

    @Override
    public void successSync(String lhq) {
        SharedPreferencesUtils.put("lhqId",lhq);
    }
}
