package com.golive.xess.merchant.view.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    public final static int DIALOG_STATUS_AFFIRM = 0;//确认提现
    public final static int DIALOG_STATUS_WITHDRAW = 1;//提现成功
    private  int status = 0 , kidney = 0;
    private BaseActivity mContext;

    public Common_dialog(BaseActivity context, int status ,int kidney) {
        super(context, R.style.ShareDialog);
        this.mContext = context;
        this.status = status;
        this.kidney = kidney;
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
                dialogTitleTv.setText(mContext.getResourcesString(mContext,R.string.affirm_s));
                dialogContextTv.setText(mContext.getMessageFormatString(mContext,R.string.affirm_withdraw_s,kidney+""));
                leftDialogBt.setText(mContext.getResourcesString(mContext,R.string.withdraw_to_balance_s));
                rightDialogBt.setText(mContext.getResourcesString(mContext,R.string.withdraw_to_card_s));

                break;
            case DIALOG_STATUS_WITHDRAW:
                dialogTitleTv.setText(mContext.getResourcesString(mContext,R.string.withdraw_success_s));
                dialogContextTv.setText(mContext.getMessageFormatString(mContext,R.string.withdraw_context_s,kidney+""));
                leftDialogBt.setVisibility(View.GONE);
                rightDialogBt.setText(mContext.getResourcesString(mContext,R.string.affirm_s));
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
                Common_dialog dialog = new Common_dialog(mContext,1,10);
                dialog.show();
                dismiss();
                break;
            case R.id.right_dialog_bt:
                dismiss();
                break;
        }
    }
}
