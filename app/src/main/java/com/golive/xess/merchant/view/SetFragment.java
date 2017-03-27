package com.golive.xess.merchant.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by YangChun .
 * on 2017/3/7.
 * 设置
 */

public class SetFragment extends BaseFragment {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.version_tv)
    TextView versionTv;
    @BindView(R.id.user_protocol_tv)
    TextView userProtocolTv;
    @BindView(R.id.service_phone_et)
    TextView servicePhoneEt;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.cut_version_bt)
    Button cutVersionBt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.cut_version_bt, R.id.modify_pwd_bt, R.id.change_user_bt, R.id.exit_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cut_version_bt:
                break;
            case R.id.modify_pwd_bt:
                startActivity(new Intent(activity, ModifyActivity.class));
                break;
            case R.id.change_user_bt:
                startActivity(new Intent(activity, LoginActivity.class));
                break;
            case R.id.exit_bt:
                activity.finish();
                break;
        }
    }


}
