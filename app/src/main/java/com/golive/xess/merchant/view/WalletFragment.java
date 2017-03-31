package com.golive.xess.merchant.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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
import com.golive.xess.merchant.model.entity.PayEvent;
import com.golive.xess.merchant.model.entity.WalletEntity;
import com.golive.xess.merchant.presenter.WalletContract;
import com.golive.xess.merchant.presenter.WalletPresenter;
import com.golive.xess.merchant.utils.RxBus;
import com.golive.xess.merchant.utils.SharedPreferencesUtils;
import com.golive.xess.merchant.view.adapter.ItemWalletAdapter;
import com.golive.xess.merchant.view.widget.CommonDialog;
import com.golive.xess.merchant.view.widget.DialogErr;
import com.golive.xess.merchant.view.widget.DialogRecharge;
import com.golive.xess.merchant.view.widget.MyListView;
import com.google.gson.internal.LinkedTreeMap;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

import static com.golive.xess.merchant.presenter.WalletContract.GAINDATA;
import static com.golive.xess.merchant.presenter.WalletContract.GAINMORE;
import static com.golive.xess.merchant.presenter.WithDrawContract.DIALOG_STATUS_AFFIRM;

/**
 * Created by YangChun .
 * on 2017/3/7.
 * 钱包
 */

public class WalletFragment extends BaseFragment implements WalletContract.View {

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


    @Inject
    WalletPresenter presenter;

    List<LinkedTreeMap> mapList;
    ItemWalletAdapter walletAdapter;
    Subscription rxSubscription;
    private WalletEntity mWalletEntity;
    LayoutInflater mInflater;
    WalletBody body;
    WalletLogsBody logsBody;
    private int pageSize = 10;
    private static int pageNo = 0;

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

        initView();
        initData();
        initRxBus();
    }

    private void initRxBus() {
        rxSubscription = RxBus.getInstance().toObserverable(PayEvent.class)
                .subscribe(new Action1<PayEvent>() {
                    @Override
                    public void call(PayEvent payEvent) {
                        String result = payEvent.getResult();
                        if("success".equals(result)) {
                            result = "支付成功";
                            String kidneyBean = payEvent.getKidneyBean();
                            kidneyBean = getBig(kidneyBean);
                            SharedPreferencesUtils.put("kidneyBean", kidneyBean);
                            currentlyKidneyTv.setText(getMessageFormatString(activity, R.string.currently_kidney_s, kidneyBean));
                            new DialogErr(activity, result).show();
                        } else if("withdraw".equals(result)){
                            String kidneyBean = payEvent.getKidneyBean();
                            String commission = payEvent.getCommission();
                            kidneyBean = getBig(kidneyBean);
                            commission = getBig(commission);
                            SharedPreferencesUtils.put("kidneyBean", kidneyBean);
                            currentlyKidneyTv.setText(getMessageFormatString(activity, R.string.currently_kidney_s, kidneyBean));
                            commissionKidneyTv.setText(getMessageFormatString(activity, R.string.commission_kidney_s, commission));
                            pageNo = 0;
                            presenter.getWalletLogs(logsBody,GAINDATA);
                        } else {
                            result = "支付失败";
                            new DialogErr(activity,result).show();
                        }
                    }
                });
    }
    private void initView() {
        if (XessConfig._VERSION == XessConfig._PERSONAL) {

        } else {
            winKidneyTv.setVisibility(View.GONE);
            topAwardTv.setVisibility(View.GONE);
            winCountTv.setVisibility(View.GONE);
        }
    }

    private void initData() {
        body = new WalletBody(storeUid, "", deviceNo);
        presenter.getWalletInfo(body);
        ////////////////////////
        logsBody = new WalletLogsBody(storeUid,deviceNo, pageSize+"");
        logsBody.setPageNo(pageNo+"");
        presenter.getWalletLogs(logsBody,GAINDATA);
        //////////////////////////////
        mapList = new ArrayList<>();
        walletAdapter = new ItemWalletAdapter(mInflater, mapList);
        walletLv.setAdapter(walletAdapter);

        walletLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 当不滚动时
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    // 判断是否滚动到底部
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        //加载更多功能的代码
                        pageNo++;
                        logsBody.setPageNo(pageNo+"");
                        presenter.getWalletLogs(logsBody,GAINMORE);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @OnClick(R.id.recharge_bt)
    void onClickRecharge() {
        new DialogRecharge(activity).show();
    }

    @OnClick(R.id.withdraw_bt)
    void onClickWithdraw() {
        CommonDialog dialog = new CommonDialog(activity, DIALOG_STATUS_AFFIRM ,mWalletEntity);
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);// show之前设置无效
    }

    ///////////////////WalletContract.View////////////////////////

    @Override
    public void dataFailed(Throwable throwable, int type ,int gain ) {
        new DialogErr(activity,throwable.getMessage()).show();
        if(gain == GAINMORE){
            pageNo --;
        }
    }

    @Override
    public void dataInfoSuccess(WalletEntity walletEntity) {
        if (walletEntity != null) {
            mWalletEntity = walletEntity;
            String kidneyBean = walletEntity.getKidneyBean();
            String commission = walletEntity.getCommission();
            if (XessConfig._VERSION == XessConfig._PERSONAL) {
                currentlyKidneyTv.setText(getMessageFormatString(activity, R.string.currently_kidney_s, kidneyBean));
                winKidneyTv.setText(getMessageFormatString(activity, R.string.win_kidney_s, walletEntity.getGainBean()));
                topAwardTv.setText(getMessageFormatString(activity, R.string.top_award_s, walletEntity.getTopMoney()));
                winCountTv.setText(getMessageFormatString(activity, R.string.win_count_s, walletEntity.getWinTimes()));
//                commissionKidneyTv.setText();
            } else {
                currentlyKidneyTv.setText(getMessageFormatString(activity, R.string.currently_kidney_s, getBig(kidneyBean)));
                commissionKidneyTv.setText(getMessageFormatString(activity, R.string.commission_kidney_s, getBig(commission)));
            }
        }
    }

    @Override
    public void dataLogsSuccess(List<LinkedTreeMap> walletLogEntity ,int gain) {
        if(walletLogEntity != null && walletLogEntity.size() !=0) {
            if(gain == GAINDATA) mapList.clear();
            mapList.addAll(walletLogEntity);
            walletAdapter.notifyDataSetChanged();
        } else {
            if(gain == GAINMORE){
                pageNo --;
                Toast.makeText(activity,"已无更多",Toast.LENGTH_SHORT).show();
            }
        }
    }

    ///////////////////WalletContract.View////////////////////////


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (!rxSubscription.isUnsubscribed()){
            rxSubscription.unsubscribe();
        }
    }

    public String getBig(String s){
        BigDecimal kid;
        if(TextUtils.isEmpty(s))s = "0";
        kid = new BigDecimal(Double.parseDouble(s));
        return kid.toString();
    }
}
