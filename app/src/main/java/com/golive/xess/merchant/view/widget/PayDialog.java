package com.golive.xess.merchant.view.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseActivity;

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayDialog extends Dialog {


    @BindView(R.id.dialog_title_tv)
    TextView dialogTitleTv;
    @BindView(R.id.dialog_close_tv)
    TextView dialogCloseTv;
    @BindView(R.id.weixin_pay_iv)
    ImageView weixinPayIv;
    @BindView(R.id.alipay_pay_iv)
    ImageView alipayPayIv;
    @BindView(R.id.pay_money_tv)
    TextView payMoneyTv;

    private BaseActivity mContext;
    private Double payNum;

    public PayDialog(BaseActivity context, Double num) {
        super(context, R.style.ShareDialog);
        this.mContext = context;
        this.payNum = num;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay);
        ButterKnife.bind(this);
        dialogTitleTv.setText("菜豆充值");
        payMoneyTv.setText(getFormatString(payNum , payNum/100));
    }

    @OnClick(R.id.dialog_close_tv)
    public void onClick() {
        dismiss();
    }

    public String getFormatString(Object ... arguments ) {
        return MessageFormat.format(mContext.getResources().getString(R.string.pay_money_s),arguments);
    }
}
