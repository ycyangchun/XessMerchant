package com.golive.xess.merchant.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseActivity;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerModifyComponent;
import com.golive.xess.merchant.di.modules.ModifyModule;
import com.golive.xess.merchant.model.api.body.ModifyBody;
import com.golive.xess.merchant.model.entity.LoginEntity;
import com.golive.xess.merchant.presenter.ModifyContract;
import com.golive.xess.merchant.presenter.ModifyPresenter;
import com.golive.xess.merchant.utils.AppUtil;
import com.golive.xess.merchant.view.widget.DialogErr;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyActivity extends BaseActivity implements ModifyContract.View {

    @BindView(R.id.original_pwd_tv)
    EditText originalPwdTv;
    @BindView(R.id.new_pwd_tv)
    EditText newPwdTv;
    @BindView(R.id.affirm_pwd_tv)
    EditText affirmPwdTv;
    @BindView(R.id.tv_modify_original_result)
    TextView tvOriResult;
    @BindView(R.id.tv_modify_new_result)
    TextView tvNewResult;
    @BindView(R.id.tv_modify_affirm_result)
    TextView tvAffResult;

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

    @OnClick(R.id.login_bt)
    void onLoginBtClick() {
        String original = originalPwdTv.getText().toString().trim();
        String newPwd = newPwdTv.getText().toString().trim();
        String affirmPwd = affirmPwdTv.getText().toString().trim();
        if (!verifyOriPass(original, tvOriResult))
            return;

        if (!verifyNewAndAffirmPwd(newPwd, affirmPwd))
            return;
        presenter.modify(new ModifyBody(deviceNo, storeUid, AppUtil.getMacByWifi()
                , original, affirmPwd));
    }

    /**
     *验证原密码
     * @param pass 原密码
     * @param tvResult 结果显示
     */
    private boolean verifyOriPass(String pass,TextView tvResult){
        if (TextUtils.isEmpty(pass)){
            tvResult.setText(getString(R.string.modify_original_null));
            return false;
        }else tvResult.setText("");
        return true;
    }

    /**
     * 验证新密码和确认密码
     * @return
     */
    private boolean verifyNewAndAffirmPwd(String newPwd,String affirmPwd){
        if (TextUtils.isEmpty(newPwd)){
            tvNewResult.setText(getString(R.string.modify_new_null));
            return false;
        }else tvNewResult.setText("");

        if (TextUtils.isEmpty(affirmPwd)){
            tvAffResult.setText(getString(R.string.modify_affirm_null));
            return false;
        }else tvAffResult.setText("");

        if (!affirmPwd.equals(newPwd)){
            tvAffResult.setText(getString(R.string.modify_pass_input_same));
            return false;
        }else tvAffResult.setText("");
        return true;
    }
    //////////////////ModifyContract.View///////////////

    @Override
    public void showOnFailure(Throwable throwable) {
        new DialogErr(this, throwable.getMessage()).show();
    }

    @Override
    public void successModify(LoginEntity loginEntity) {
        Toast.makeText(this, "修改密码成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    //////////////////ModifyContract.View///////////////
}
