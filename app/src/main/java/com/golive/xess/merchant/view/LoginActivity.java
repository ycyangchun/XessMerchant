package com.golive.xess.merchant.view;

import android.os.Bundle;
import android.app.Activity;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseActivity;
import com.golive.xess.merchant.model.entity.UserInfo;
import com.golive.xess.merchant.presenter.LoginContract;
import com.zhy.android.percent.support.PercentRelativeLayout;

import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.Button;
import android.widget.CheckBox;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class LoginActivity extends BaseActivity implements LoginContract.View{

    @BindView(R.id.login_tv)
    TextView loginTv;
    @BindView(R.id.login_mobile_tv)
    EditText loginMobileTv;
    @BindView(R.id.login_verification_tv)
    EditText loginVerificationTv;
    @BindView(R.id.protocol_cb)
    CheckBox protocolCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.get_verification_bt)
    void onGetVerificationBtClick() {

    }


    @OnClick(R.id.login_bt)
    void onLoginBtClick() {
        //TODO implement
    }

    ////////////LoginContract.View///////////////////
    @Override
    public void showOnFailure(Throwable throwable) {

    }

    @Override
    public void successLogin(UserInfo UserInfo) {

    }

    @Override
    public void successVerification(String verification) {

    }
    ////////////LoginContract.View///////////////////
}
