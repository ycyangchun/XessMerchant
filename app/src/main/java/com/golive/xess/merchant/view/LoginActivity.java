package com.golive.xess.merchant.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseActivity;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerLoginComponent;
import com.golive.xess.merchant.di.modules.LoginModule;
import com.golive.xess.merchant.model.api.NoNetworkException;
import com.golive.xess.merchant.model.api.body.LoginBody;
import com.golive.xess.merchant.model.entity.LoginEntity;
import com.golive.xess.merchant.presenter.LoginContract;
import com.golive.xess.merchant.presenter.LoginPresenter;
import com.golive.xess.merchant.utils.SharedPreferencesUtils;
import com.golive.xess.merchant.view.widget.DialogErr;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.login_mobile_tv)
    EditText loginMobileTv;
    @BindView(R.id.login_password_tv)
    EditText loginPasswordTv;

    @Inject
    LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        DaggerLoginComponent.builder()
                .netComponent(XessApp.get(this).getNetComponent())
                .loginModule(new LoginModule(this))
                .build().inject(this);

    }


    @OnClick(R.id.login_bt)
    void onLoginBtClick() {
        String uid = loginMobileTv.getText().toString().trim();
        String pwd = loginPasswordTv.getText().toString().trim();
        if( !TextUtils.isEmpty(uid) && !TextUtils.isEmpty(pwd)) {
            LoginBody body = new LoginBody();
            body.setDeviceNo(deviceNo);
            body.setPassword(pwd);
            body.setStoreUid(uid);
            presenter.login(body);
        } else
            Toast.makeText(this,"商家编号 或 登录密码 为空！",Toast.LENGTH_LONG).show();
    }

    ////////////LoginContract.View///////////////////
    @Override
    public void showOnFailure(Throwable throwable) {
        new DialogErr(this,throwable.getMessage()).show();
    }

    @Override
    public void successLogin(LoginEntity loginEntity ,String pwd) {
        if(loginEntity != null){
            SharedPreferencesUtils.put("storeUid",loginEntity.getStoreUid()+"");
            SharedPreferencesUtils.put("password",pwd);
            JPushInterface.setAlias(getApplicationContext(), loginEntity.getStoreUid()+"", null);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    ////////////LoginContract.View///////////////////
}
