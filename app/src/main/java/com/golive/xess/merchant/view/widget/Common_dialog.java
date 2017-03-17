package com.golive.xess.merchant.view.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by YangChun .
 * on 2017/3/17.
 */

public class Common_dialog extends Dialog {

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
    TextView withdrawCardNameEt;
    @BindView(R.id.withdraw_card_num_tv)
    TextView withdrawCardNumEt;
    @BindView(R.id.card_affirm_rl)
    RelativeLayout cardAffirmRl;

    public final static int DIALOG_STATUS_AFFIRM = 0;//确认提现
    public final static int DIALOG_STATUS_WITHDRAW = 1;//提现成功
    public final static int DIALOG_STATUS_CARD = 2;//绑定银行卡
    public final static int DIALOG_STATUS_CARD_AFFIRM = 3;//银行卡 确认


    private int status = 0, kidney = 0;
    private BaseActivity mContext;

    public Common_dialog(BaseActivity context, int status, int kidney) {
        super(context, R.style.ShareDialog);
        this.mContext = context;
        this.status = status;
        this.kidney = kidney;
    }

    public Common_dialog(BaseActivity context, int status) {
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
                dialogContextTv.setText(mContext.getMessageFormatString(mContext, R.string.withdraw_context_s, kidney + ""));
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
                rightDialogBt.setText(mContext.getResourcesString(mContext, R.string.binding_affirm_s));
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
            case DIALOG_STATUS_AFFIRM:
                new Common_dialog(mContext, 1, 10).show();
                break;
        }
    }

    void rightClick() {
        dismiss();
        switch (status) {
            case DIALOG_STATUS_AFFIRM:
                //  是否已绑定卡
                new Common_dialog(mContext, 2).show();
                break;
            case DIALOG_STATUS_WITHDRAW:

                break;
            case DIALOG_STATUS_CARD:
                new Common_dialog(mContext, 3).show();
                break;
            case DIALOG_STATUS_CARD_AFFIRM:

                break;
        }
    }
}
