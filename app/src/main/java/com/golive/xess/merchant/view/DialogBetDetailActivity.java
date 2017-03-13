package com.golive.xess.merchant.view;

import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.XessConfig;
import com.golive.xess.merchant.base.BaseActivity;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerBetDetailComponent;
import com.golive.xess.merchant.di.modules.BetDetailModule;
import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.entity.OrdersEntity;
import com.golive.xess.merchant.presenter.BetDetailContract;
import com.golive.xess.merchant.presenter.BetDetailPresenter;
import com.golive.xess.merchant.utils.Base64Util;
import com.golive.xess.merchant.utils.Des3Util;
import com.golive.xess.merchant.utils.EnumUtils;
import com.golive.xess.merchant.utils.SharedPreferencesUtils;
import com.google.gson.Gson;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class DialogBetDetailActivity extends BaseActivity implements BetDetailContract.View{

    @BindView(R.id.bet_issue_tv) TextView betIssueTv;
    @BindView(R.id.bet_orders_tv) TextView betOrdersTv;
    @BindView(R.id.bet_time_tv) TextView betTimeTv;
    @BindView(R.id.bet_win_money_tv) TextView betWinMoneyTv;
    @BindView(R.id.bet_mobile_tv) TextView betMobileTv;
    @BindView(R.id.bet_play_tv) TextView betPlayTv;
    @BindView(R.id.bet_detail_kidney_tv) TextView betDetailKidneyTv;
    @BindView(R.id.bet_kai_status_tv) TextView betKaiStatusTv;
    @BindView(R.id.bet_note_tv) TextView betNoteTv;
    @BindView(R.id.bet_win_status_tv) TextView betWinStatusTv;
    @BindView(R.id.bet_lottery_num_tv) TextView betLotteryNumTv;
    @BindView(R.id.bet_play_type_tv) TextView bet_play_type_tv;
    @BindView(R.id.bet_times_tv) TextView bet_times_tv;

    String orderNo ,deviceNo;

    @Inject
    BetDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bet_detail);
        ButterKnife.bind(this);
        orderNo = getIntent().getStringExtra("orderNo");
        deviceNo = SharedPreferencesUtils.getString("deviceNo");

        DaggerBetDetailComponent.builder()
                .netComponent(XessApp.get(this).getNetComponent())
                .betDetailModule(new BetDetailModule(this))
                .build().inject(this);
        Map<String,String> map = new HashMap<>();
        map.put("deviceNo",SharedPreferencesUtils.getString("deviceNo"));
        map.put("orderNo",orderNo);

        String ss = new Gson().toJson(map);
        System.out.println("==================>"+ss);
        String data  = null;
        try {
            data = Base64Util.encode(Des3Util.getInstance(ApiService.SECRET_KEY, ApiService.SECRET_VALUE).encode(ss));
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestBody requestBody =
                RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                        data);
        presenter.getDetail(requestBody);
    }

    @Override
    public void showOnFailure(Throwable throwable) {

    }

    @Override
    public void successLoad(OrdersEntity ordersEntity) {
        if(ordersEntity != null){
            betIssueTv.setText(getMessageFormatString(this ,R.string.bet_issue_s,ordersEntity.getIssue()));
            betOrdersTv.setText(getMessageFormatString(this ,R.string.bet_order_s,ordersEntity.getOrderNo()));
            betTimeTv.setText(getMessageFormatString(this ,R.string.bet_time_s,ordersEntity.getCreateTime()));
            betWinMoneyTv.setText(getMessageFormatString(this ,R.string.bet_win_money_s,ordersEntity.getWinAmount()));
            betMobileTv.setText(getMessageFormatString(this ,R.string.bet_mobile_s,ordersEntity.getMobile()));
            bet_play_type_tv.setText(getMessageFormatString(this ,R.string.bet_lottery_s,EnumUtils.getName(ordersEntity.getLid())));
            betPlayTv.setText(getMessageFormatString(this ,R.string.bet_play_s,EnumUtils.getName(ordersEntity.getPid())));
            bet_times_tv.setText(getMessageFormatString(this ,R.string.bet_times_s,ordersEntity.getTimesCount()));
//            betDetailKidneyTv.setText("");
            betKaiStatusTv.setText(getMessageFormatString(this ,R.string.bet_kai_status_s,ordersEntity.getOrderStateDesc()));
            betNoteTv.setText(getMessageFormatString(this ,R.string.bet_note_s,ordersEntity.getInvestNum()));
            betWinStatusTv.setText(getMessageFormatString(this ,R.string.bet_win_status_s,EnumUtils.getName(ordersEntity.getWinState())));
            betLotteryNumTv.setText(getMessageFormatString(this ,R.string.bet_lottery_num_s,ordersEntity.getInvestCode()));
        }
    }
}
