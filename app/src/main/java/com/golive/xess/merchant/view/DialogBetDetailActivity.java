package com.golive.xess.merchant.view;

import android.os.Bundle;
import android.widget.TextView;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogBetDetailActivity extends BaseActivity {

    @BindView(R.id.bet_issue_tv) TextView betIssueTv;
    @BindView(R.id.bet_orders_tv) TextView betOrdersTv;
    @BindView(R.id.bet_time_tv) TextView betTimeTv;
    @BindView(R.id.bet_win_money_tv) TextView betWinMoneyTv;
    @BindView(R.id.bet_mobile_tv) TextView betMobileTv;
    @BindView(R.id.bet_play_tv) TextView betPlayTv;
    @BindView(R.id.bet_detail_kidney_tv) TextView betDetailKidneyTv;
    @BindView(R.id.bet_times_tv) TextView betTimesTv;
    @BindView(R.id.bet_kai_status_tv) TextView betKaiStatusTv;
    @BindView(R.id.bet_note_tv) TextView betNoteTv;
    @BindView(R.id.bet_win_status_tv) TextView betWinStatusTv;
    @BindView(R.id.bet_lottery_num_tv) TextView betLotteryNumTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bet_detail);
        ButterKnife.bind(this);

    }

}
