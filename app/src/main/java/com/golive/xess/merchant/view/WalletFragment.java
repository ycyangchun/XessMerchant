package com.golive.xess.merchant.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.XessConfig;
import com.golive.xess.merchant.base.BaseFragment;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerWalletComponent;
import com.golive.xess.merchant.di.modules.WalletModule;
import com.golive.xess.merchant.model.api.body.WalletBody;
import com.golive.xess.merchant.model.api.body.WalletLogsBody;
import com.golive.xess.merchant.model.entity.WalletEntity;
import com.golive.xess.merchant.presenter.WalletContract;
import com.golive.xess.merchant.presenter.WalletPresenter;
import com.golive.xess.merchant.view.adapter.ItemWalletAdapter;
import com.golive.xess.merchant.view.widget.Common_dialog;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by YangChun .
 * on 2017/3/7.
 * 钱包
 */

public class WalletFragment extends BaseFragment implements WalletContract.View{

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
    @BindView(R.id.wallet_lv)
    ListView walletLv;

    LayoutInflater mInflater;
    @Inject
    WalletPresenter presenter;

    List<LinkedTreeMap> mapList;
    ItemWalletAdapter walletAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = inflater;
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DaggerWalletComponent.builder()
                .netComponent(XessApp.get(activity).getNetComponent())
                .walletModule(new WalletModule(this)).build().inject(this);


        WalletBody body;
        if(XessConfig._VERSION == XessConfig._PERSONAL)
            body = new WalletBody("","100001",deviceNo);
        else
            body = new WalletBody(storeUid,"",deviceNo);
        presenter.getWalletInfo(body);
        ////////////////////////
        WalletLogsBody logsBody;
        if(XessConfig._VERSION == XessConfig._PERSONAL)
            logsBody = new WalletLogsBody("","100001",deviceNo,"0","10");
        else
            logsBody = new WalletLogsBody(storeUid,"",deviceNo,"0","10");
        presenter.getWalletLogs(logsBody);
        //////////////////////////////
        mapList = new ArrayList<>();
        walletAdapter = new ItemWalletAdapter(mInflater,mapList);
        walletLv.setAdapter(walletAdapter);
    }

    @OnClick(R.id.recharge_bt)
    void onClickRecharge(){

    }

    @OnClick(R.id.withdraw_bt)
    void onClickWithdraw(){
        Common_dialog dialog = new Common_dialog(activity,0,10);
        dialog.show();
    }

    ///////////////////WalletContract.View////////////////////////

    @Override
    public void dataFailed(Throwable throwable, int type) {
        Toast.makeText(activity,throwable.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dataInfoSuccess(WalletEntity walletEntity) {
        if(walletEntity != null){
            if(XessConfig._VERSION == XessConfig._PERSONAL) {
                currentlyKidneyTv.setText(getMessageFormatString(activity ,R.string.currently_kidney_s,walletEntity.getKidneyBean()));
                winKidneyTv.setText(getMessageFormatString(activity ,R.string.win_kidney_s,walletEntity.getGainBean()));
                topAwardTv.setText(getMessageFormatString(activity ,R.string.top_award_s,walletEntity.getTopMoney()));
                winCountTv.setText(getMessageFormatString(activity ,R.string.win_count_s,walletEntity.getWinTimes()));
//                commissionKidneyTv.setText();
            }else {
                currentlyKidneyTv.setText(getMessageFormatString(activity ,R.string.currently_kidney_s,walletEntity.getKidneyBean()));
            }
        }
    }

    @Override
        public void dataLogsSuccess(List<LinkedTreeMap> walletLogEntity) {
        mapList.addAll(walletLogEntity);
        walletAdapter.notifyDataSetChanged();
    }

    ///////////////////WalletContract.View////////////////////////

}
