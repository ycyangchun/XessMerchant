package com.golive.xess.merchant.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseActivity;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerSplashComponent;
import com.golive.xess.merchant.di.modules.SplashModule;
import com.golive.xess.merchant.presenter.SplashContract;
import com.golive.xess.merchant.presenter.SplashPresenter;
import com.golive.xess.merchant.utils.MerchantUtils;
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
       //同步lhq
        String lhqId = MerchantUtils.getLhqId();
        if(TextUtils.isEmpty(lhqId)) {
            presenter.syncDevice(this, deviceNo);
        }

        //上传设备信息
        String updateDevice = MerchantUtils.getUpdateDevice();
        if(!"0".equals(updateDevice)) {
            presenter.updateDevice(this, deviceNo);
        } else {
            skipLogin();
        }
    }

    //跳转登录
    private void skipLogin() {
        //是否在线
        presenter.checkOnline(this,onlineNo);
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
        try {
            new DialogErr(this,throwable.getMessage()).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void successLoad(String code) {
        skipLogin();
    }

    @Override
    public void successSync(String lhq) {

    }
}
