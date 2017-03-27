package com.golive.xess.merchant.view;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseActivity;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerModifyComponent;
import com.golive.xess.merchant.di.modules.ModifyModule;
import com.golive.xess.merchant.model.api.body.ModifyBody;
import com.golive.xess.merchant.model.entity.CommonEntity;
import com.golive.xess.merchant.model.entity.LoginEntity;
import com.golive.xess.merchant.presenter.ModifyContract;
import com.golive.xess.merchant.presenter.ModifyPresenter;
import com.golive.xess.merchant.utils.AppUtil;
import com.golive.xess.merchant.utils.SharedPreferencesUtils;
import com.golive.xess.merchant.view.widget.DialogErr;
import com.zhy.android.percent.support.PercentRelativeLayout;

import android.text.TextUtils;
import android.widget.TextView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class ModifyActivity extends BaseActivity implements ModifyContract.View{

    @BindView(R.id.original_pwd_tv) EditText originalPwdTv;
    @BindView(R.id.new_pwd_tv) EditText newPwdTv;
    @BindView(R.id.affirm_pwd_tv) EditText affirmPwdTv;

    @Inject
    ModifyPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        ButterKnife.bind(this);

        DaggerModifyComponent.builder()
                .netComponent(XessApp.get(this).getNetComponent())
                .modifyModule(new ModifyModule(this))
                .build().inject(this);

    }

    @OnClick(R.id.login_bt) void onLoginBtClick() {
        String original  = originalPwdTv.getText().toString().trim();
        String newPwd = newPwdTv.getText().toString().trim();
        String affirmPwd = affirmPwdTv.getText().toString().trim();
        if(!TextUtils.isEmpty(original) && !TextUtils.isEmpty(newPwd) && !TextUtils.isEmpty(affirmPwd)) {
            if(newPwd.equals(affirmPwd))
            presenter.modify(new ModifyBody(deviceNo, storeUid, AppUtil.getMacByWifi()
                    , original, affirmPwd));
            else
                Toast.makeText(this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"未完整填写",Toast.LENGTH_SHORT).show();
        }
    }

    //////////////////ModifyContract.View///////////////

    @Override
    public void showOnFailure(Throwable throwable) {
        new DialogErr(this,throwable.getMessage()).show();
    }

    @Override
    public void successModify(LoginEntity loginEntity, String pwd) {
        Toast.makeText(this,"修改密码成功",Toast.LENGTH_SHORT).show();
        SharedPreferencesUtils.put("password",pwd);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    //////////////////ModifyContract.View///////////////
}
