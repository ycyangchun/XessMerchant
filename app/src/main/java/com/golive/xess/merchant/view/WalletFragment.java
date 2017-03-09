package com.golive.xess.merchant.view;

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

/**
 * Created by YangChun .
 * on 2017/3/7.
 * 钱包
 */

public class WalletFragment extends BaseFragment {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.currently_kidney_tv)
    TextView currentlyKidneyTv;
    @BindView(R.id.win_kidney_tv)
    TextView winKidneyTv;
    @BindView(R.id.top_award_tv)
    TextView topAwardTv;
    @BindView(R.id.win_count_tv)
    TextView winCountTv;
    @BindView(R.id.commission_kidney_tv)
    TextView commissionKidneyTv;
    @BindView(R.id.recharge_bt)
    Button rechargeBt;
    @BindView(R.id.withdraw_bt)
    Button withdrawBt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
