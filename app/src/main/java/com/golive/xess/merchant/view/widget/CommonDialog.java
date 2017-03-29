package com.golive.xess.merchant.view.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.XessConfig;
import com.golive.xess.merchant.base.BaseActivity;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerWithDrawComponent;
import com.golive.xess.merchant.di.modules.WithDrawModule;
import com.golive.xess.merchant.model.api.body.BindCardBody;
import com.golive.xess.merchant.model.api.body.WithdrawBody;
import com.golive.xess.merchant.model.entity.PayEvent;
import com.golive.xess.merchant.model.entity.WalletEntity;
import com.golive.xess.merchant.presenter.WithDrawContract;
import com.golive.xess.merchant.presenter.WithDrawPresenter;
import com.golive.xess.merchant.utils.AppUtil;
import com.golive.xess.merchant.utils.RxBus;
import com.golive.xess.merchant.utils.SharedPreferencesUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.golive.xess.merchant.presenter.WithDrawContract.DIALOG_STATUS_AFFIRM;
import static com.golive.xess.merchant.presenter.WithDrawContract.DIALOG_STATUS_CARD;
import static com.golive.xess.merchant.presenter.WithDrawContract.DIALOG_STATUS_CARD_AFFIRM;
import static com.golive.xess.merchant.presenter.WithDrawContract.DIALOG_STATUS_REMINDER;
import static com.golive.xess.merchant.presenter.WithDrawContract.DIALOG_STATUS_WITHDRAW;
import static com.golive.xess.merchant.presenter.WithDrawContract.DIALOG_STATUS_WITHDRAW_FAILED;

/**
 * Created by YangChun .
 * on 2017/3/17.
 */

public class CommonDialog extends Dialog implements WithDrawContract.View{

    @BindView(R.id.dialog_title_tv)
    TextView dialogTitleTv;
    @BindView(R.id.dialog_close_tv)
    TextView dialogCloseTv;
    @BindView(R.id.dialog_context_tv)
    TextView dialogContextTv;
    @BindView(R.id.left_dialog_bt)
    Button leftDialogBt;
    @BindView(R.id.right_dialog_bt)
    Button rightDialogBt;
    @BindView(R.id.text_rl)
    RelativeLayout textRl;
    @BindView(R.id.card_name_et)
    EditText cardNameEt;
    @BindView(R.id.card_num_et)
    EditText cardNumEt;
    @BindView(R.id.card_bank_et)
    EditText cardBankEt;
    @BindView(R.id.card_rl)
    RelativeLayout cardRl;
    @BindView(R.id.withdraw_to_bank_tv)
    TextView withdrawToBankTv;
    @BindView(R.id.withdraw_card_name_tv)
    TextView withdrawCardNameTv;
    @BindView(R.id.withdraw_card_num_tv)
    TextView withdrawCardNumTv;
    @BindView(R.id.card_affirm_rl)
    RelativeLayout cardAffirmRl;


    private int status = 0;
    double kidney = 0;
    private String withdrawType , withdrawKidney;//= "钱包里";
    private BaseActivity mContext;
    private WalletEntity mWalletEntity;

    @Inject
    WithDrawPresenter presenter;

    public CommonDialog(BaseActivity context, int status,String withdrawKidney, String withdrawType) {
        super(context, R.style.ShareDialog);
        this.mContext = context;
        this.status = status;
        this.withdrawType = withdrawType;
        this.withdrawKidney = withdrawKidney;
    }

    public CommonDialog(BaseActivity context, int status, WalletEntity walletEntity) {
        super(context, R.style.ShareDialog);
        this.mContext = context;
        this.status = status;
        this.mWalletEntity = walletEntity;
        try {
            this.kidney = Double.parseDouble(walletEntity.getCommission());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.kidney = 0;
        }
        if(kidney < 100 && status == DIALOG_STATUS_AFFIRM){
            this.status = DIALOG_STATUS_REMINDER;
        }
    }
    public CommonDialog(BaseActivity context, int status) {
        super(context, R.style.ShareDialog);
        this.mContext = context;
        this.status = status;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common);
        ButterKnife.bind(this);
        initView();
        DaggerWithDrawComponent.builder()
                .netComponent(XessApp.get(mContext).getNetComponent())
                .withDrawModule(new WithDrawModule(this))
                .build().inject(this);

    }

    private void initView() {
        switch (status) {
            case DIALOG_STATUS_AFFIRM:
                textRl.setVisibility(View.VISIBLE);
                cardRl.setVisibility(View.GONE);
                cardAffirmRl.setVisibility(View.GONE);
                dialogTitleTv.setText(mContext.getResourcesString(mContext, R.string.affirm_s));
                dialogContextTv.setText(mContext.getMessageFormatString(mContext, R.string.affirm_withdraw_s, kidney + ""));
                leftDialogBt.setText(mContext.getResourcesString(mContext, R.string.withdraw_to_balance_s));
                rightDialogBt.setText(mContext.getResourcesString(mContext, R.string.withdraw_to_card_s));
                break;
            case DIALOG_STATUS_WITHDRAW:
                textRl.setVisibility(View.VISIBLE);
                cardRl.setVisibility(View.GONE);
                cardAffirmRl.setVisibility(View.GONE);
                dialogTitleTv.setText(mContext.getResourcesString(mContext, R.string.withdraw_success_s));
                dialogContextTv.setText(mContext.getMessageFormatString(mContext, R.string.withdraw_context_s, withdrawKidney ,withdrawType));
                leftDialogBt.setVisibility(View.GONE);
                rightDialogBt.setText(mContext.getResourcesString(mContext, R.string.affirm_s));
                break;
            case DIALOG_STATUS_CARD:
                textRl.setVisibility(View.GONE);
                cardRl.setVisibility(View.VISIBLE);
                cardAffirmRl.setVisibility(View.GONE);
                dialogTitleTv.setText(mContext.getResourcesString(mContext, R.string.binding_bank_s));
                leftDialogBt.setVisibility(View.GONE);
                rightDialogBt.setText(mContext.getResourcesString(mContext, R.string.binding_affirm_s));
                break;
            case DIALOG_STATUS_CARD_AFFIRM:
                textRl.setVisibility(View.GONE);
                cardRl.setVisibility(View.GONE);
                cardAffirmRl.setVisibility(View.VISIBLE);
                dialogTitleTv.setText(mContext.getResourcesString(mContext, R.string.affirm_s));
                leftDialogBt.setVisibility(View.GONE);
                rightDialogBt.setText(mContext.getResourcesString(mContext, R.string.withdraw_affirm_s));
                withdrawCardNameTv.setText(mWalletEntity.getBankUserName());
                withdrawCardNumTv.setText(mWalletEntity.getBankNo());

                withdrawToBankTv.setText(mContext.getMessageFormatString(mContext,R.string.withdraw_to_bank_s,mWalletEntity.getCommission()));
                break;

            case DIALOG_STATUS_WITHDRAW_FAILED :
                textRl.setVisibility(View.VISIBLE);
                cardRl.setVisibility(View.GONE);
                cardAffirmRl.setVisibility(View.GONE);
                dialogTitleTv.setText(mContext.getResourcesString(mContext, R.string.withdraw_failed_s));
                dialogContextTv.setText("菜豆已经退回您的账号，请重试");
                leftDialogBt.setVisibility(View.GONE);
                rightDialogBt.setText(mContext.getResourcesString(mContext, R.string.affirm_s));
                break;
            case DIALOG_STATUS_REMINDER:
                textRl.setVisibility(View.VISIBLE);
                cardRl.setVisibility(View.GONE);
                cardAffirmRl.setVisibility(View.GONE);
                dialogTitleTv.setText("提示");
                dialogContextTv.setText("抱歉，100以上菜豆才能提现");
                leftDialogBt.setVisibility(View.GONE);
                rightDialogBt.setText(mContext.getResourcesString(mContext, R.string.affirm_s));
                break;
            default:

                break;
        }
    }

    @OnClick({R.id.dialog_close_tv, R.id.left_dialog_bt, R.id.right_dialog_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_close_tv:
                dismiss();
                break;
            case R.id.left_dialog_bt:
                leftClick();
                break;
            case R.id.right_dialog_bt:
                rightClick();
                break;
        }
    }

    void leftClick() {
        dismiss();
        switch (status) {
            case DIALOG_STATUS_AFFIRM://  提现至余额
                withDraw("1");
                break;
        }
    }

    void rightClick() {
        dismiss();
        switch (status) {
            case DIALOG_STATUS_AFFIRM://提现至银行卡
                //  是否已绑定卡
                String bankNo = mWalletEntity.getBankNo();
                if(!TextUtils.isEmpty(bankNo)) {//显示 银行卡确认并提现
                    new CommonDialog(mContext, DIALOG_STATUS_CARD_AFFIRM ,mWalletEntity).show();
                } else { //显示  绑定银行卡
                    new CommonDialog(mContext, DIALOG_STATUS_CARD).show();
                }
                break;
            case DIALOG_STATUS_CARD://绑定银行卡
                MyBindCard();
                break;
            case DIALOG_STATUS_CARD_AFFIRM://银行卡确认 并提现
                withDraw("2");
                break;
        }
    }

    //提现
    private void withDraw(String type) {
        WithdrawBody data = new WithdrawBody();
        data.setType(type);//1提现到账户，2提现到银行卡
        data.setDeviceNo(SharedPreferencesUtils.getString("deviceNo"));
        data.setStoreUid(SharedPreferencesUtils.getString("storeUid"));
        data.setKidneyBean(kidney+"");
        presenter.withDraw(data);
    }

    // 绑卡
    private void MyBindCard() {
        String name = cardNameEt.getText().toString();
        String bankInfo = cardBankEt.getText().toString();
        String bankNo = cardNumEt.getText().toString();
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(bankInfo) && !TextUtils.isEmpty(bankNo)) {
            BindCardBody body = new BindCardBody();
            body.setUserName(name);
            body.setBankInfo(bankInfo);
            body.setBankNo(bankNo);
            body.setMac(AppUtil.getMacByWifi());
            body.setDeviceNo(SharedPreferencesUtils.getString("deviceNo"));
            body.setOriginType(XessConfig._VERSION + "");
            body.setStoreNo(SharedPreferencesUtils.getString("storeNo"));
            presenter.bindCard(body);
        } else {
            Toast.makeText(mContext," 填写不完整！",Toast.LENGTH_SHORT).show();
        }
    }


/////////////////WithDrawContract.View///////////////////


    @Override
    public void showOnFailure(Throwable throwable, int type) {
        if(type == DIALOG_STATUS_CARD_AFFIRM) {

            new CommonDialog(mContext, DIALOG_STATUS_WITHDRAW_FAILED ).show();
        } else{
            new DialogErr(mContext,throwable.getMessage()).show();
        }
    }

    @Override
    public void successWithDraw(PayEvent payEvent,String withdrawKidney, String leftOrRight) {
        leftOrRight = !leftOrRight.equals("2") ? "钱包里":"银行卡";
        new CommonDialog(mContext, DIALOG_STATUS_WITHDRAW ,withdrawKidney ,leftOrRight ).show();
        payEvent.setResult("withdraw");
        RxBus.getInstance().post(payEvent);
    }

    @Override
    public void successBindCard(WalletEntity data) {
        new CommonDialog(mContext, DIALOG_STATUS_CARD_AFFIRM ,data).show();
    }


}
