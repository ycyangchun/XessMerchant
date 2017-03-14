package com.golive.xess.merchant.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.LogWriter;
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
    @BindView(R.id.exit_bt)
    Button exitBt;
    @BindView(R.id.change_user_bt)
    Button changeUserBt;
    @BindView(R.id.cut_version_bt)
    Button cutVersionBt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.change_user_bt)
    public void onClickChangeUser(){
        startActivity(new Intent(activity, LoginActivity.class));
    }
}
