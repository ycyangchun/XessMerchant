package com.golive.xess.merchant.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseActivity;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerLoginComponent;
import com.golive.xess.merchant.di.modules.LoginModule;
import com.golive.xess.merchant.model.api.body.LoginBody;
import com.golive.xess.merchant.model.entity.LoginEntity;
import com.golive.xess.merchant.presenter.LoginContract;
import com.golive.xess.merchant.presenter.LoginPresenter;
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
    @BindView(R.id.login_bt)
    Button loginBt;

    @Inject
    LoginPresenter presenter;

    private String uid ,pwd;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
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
            Toast.makeText(this,"商家编号 或 登录密码 为空！", Toast.LENGTH_LONG).show();

       /* RxView.clicks(loginBt)
                .throttleFirst(1, TimeUnit.SECONDS)
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object o) throws Exception {
                        uid = loginMobileTv.getText().toString().trim();
                        pwd = loginPasswordTv.getText().toString().trim();
                        boolean b = !TextUtils.isEmpty(uid) && !TextUtils.isEmpty(pwd);
                       if(b)
                           return b;
                        else
                            return Observable.error(new Throwable("商家编号 或 登录密码 为空！")).materialize();
                    }
                })
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        LoginBody body = new LoginBody();
                        body.setDeviceNo(deviceNo);
                        body.setPassword(pwd);
                        body.setStoreUid(uid);
                        presenter.login(body);
                    }

                    @Override
                    public void onError(Throwable e) {
                        new DialogErr(mContext, e.getMessage()).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                });*/
    }

    ////////////LoginContract.View///////////////////
    @Override
    public void showOnFailure(Throwable throwable) {
        new DialogErr(this, throwable.getMessage()).show();
    }

    @Override
    public void successLogin(LoginEntity loginEntity) {
        if (loginEntity != null) {
            JPushInterface.setAlias(getApplicationContext(), loginEntity.getStoreUid() + "", null);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    ////////////LoginContract.View///////////////////
}
