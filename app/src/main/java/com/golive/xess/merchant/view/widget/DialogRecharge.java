package com.golive.xess.merchant.view.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseActivity;

import java.text.DecimalFormat;
import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.golive.xess.merchant.R.style.ShareDialog;


public class DialogRecharge extends Dialog {

    @BindView(R.id.dialog_close_tv)
    TextView dialogCloseTv;
    @BindView(R.id.recharge_bt_2)
    Button rechargeBt2;
    @BindView(R.id.recharge_bt_1)
    Button rechargeBt1;
    @BindView(R.id.recharge_bt_3)
    Button rechargeBt3;
    @BindView(R.id.recharge_bt_5)
    Button rechargeBt5;
    @BindView(R.id.recharge_bt_4)
    Button rechargeBt4;
    @BindView(R.id.recharge_bt_6)
    Button rechargeBt6;
    @BindView(R.id.dialog_title_tv)
    TextView dialogTitleTv;

    private BaseActivity mContext;
    Double arr[];

    public DialogRecharge(BaseActivity context) {
        super(context, ShareDialog);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_recharge);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        dialogTitleTv.setText("菜豆充值");
        arr = new Double[]{200d, 1000d, 5000d, 10000d, 20000d, -1d};
        rechargeBt1.setText(getFormatS(arr[0]));
        rechargeBt2.setText(getFormatS(arr[1]));
        rechargeBt3.setText(getFormatS(arr[2]));
        rechargeBt4.setText(getFormatS(arr[3]));
        rechargeBt5.setText(getFormatS(arr[4]));
        rechargeBt6.setText(getFormatS(arr[5]));

    }


    @OnClick({R.id.recharge_bt_2, R.id.recharge_bt_1, R.id.recharge_bt_3, R.id.recharge_bt_5, R.id.recharge_bt_4, R.id.recharge_bt_6, R.id.dialog_close_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recharge_bt_1:
                new PayDialog(mContext,arr[0]).show();
                dismiss();
                break;
            case R.id.recharge_bt_2:
                new PayDialog(mContext,arr[1]).show();
                dismiss();
                break;
            case R.id.recharge_bt_3:
                new PayDialog(mContext,arr[2]).show();
                dismiss();
                break;
            case R.id.recharge_bt_4:
                new PayDialog(mContext,arr[3]).show();
                dismiss();
                break;
            case R.id.recharge_bt_5:
                new PayDialog(mContext,arr[4]).show();
                dismiss();
                break;
            case R.id.recharge_bt_6:
                new DialogEdit(mContext).show();
                dismiss();
                break;
            case R.id.dialog_close_tv:
                dismiss();
                break;
        }
    }

    public String getFormatS(Double... arguments) {
        String format;
        if (arguments[0] != -1) {
            DecimalFormat df = new DecimalFormat("###,###");
            format = df.format(arguments[0]);
        } else
            format = "其他";
        return MessageFormat.format(mContext.getResources().getString(R.string.recharge_kidney_s), format);

    }
}
