package com.golive.xess.merchant.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseActivity;
import com.golive.xess.merchant.model.api.body.AccountBody;
import com.golive.xess.merchant.model.entity.AccountEntity;

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangChun .
 * on 2017/3/20.
 */

public class AccountDialog extends Dialog {

    @BindView(R.id.ssq_kidney_tv)
    TextView ssqKidneyTv;
    @BindView(R.id.dlt_kidney_tv)
    TextView dltKidneyTv;
    @BindView(R.id.kuai_3_kidney_tv)
    TextView kuai3KidneyTv;
    @BindView(R.id.eleven_kidney_tv)
    TextView elevenKidneyTv;
    @BindView(R.id.soccer_kidney_tv)
    TextView soccerKidneyTv;
    @BindView(R.id.head_img)
    ImageView headImg;
    @BindView(R.id.mobiles_tv)
    TextView mobilesTv;
    @BindView(R.id.sum_kidney_tv)
    TextView sumKidneyTv;

    private BaseActivity mContext;
    AccountEntity body;
    public AccountDialog(BaseActivity context ,AccountEntity accountBody) {
        super(context, R.style.ShareDialog);
        this.mContext = context;
        this.body = accountBody;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_account);
        ButterKnife.bind(this);
        initData(body);
    }

    public void initData(AccountEntity accountBody){
        if(accountBody !=null){
            ssqKidneyTv.setText(getMessageFormatString(R.string.ssq_kidney_s,accountBody.getSsqAmount()+""));
            dltKidneyTv.setText(getMessageFormatString(R.string.dlt_kidney_s,accountBody.getDltAmount()+""));
            kuai3KidneyTv.setText(getMessageFormatString(R.string.kuai_3_kidney_s,accountBody.getK3Amount()+""));
            elevenKidneyTv.setText(getMessageFormatString(R.string.eleven_kidney_s,accountBody.getElevenChoseFiveAmount()+""));
            soccerKidneyTv.setText(getMessageFormatString(R.string.soccer_kidney_s,accountBody.getFootBallAmount()+""));
            mobilesTv.setText(getMessageFormatString(R.string.mobiles_s,accountBody.getMobile()+""));
            sumKidneyTv.setText(getMessageFormatString(R.string.sum_kidney_s,accountBody.getTotalAmount()+""));
            Glide.with(mContext).load(accountBody.getHeadImg()).error(R.drawable.ic_head).into(headImg);
        }
    }

    public String getMessageFormatString(int stringId ,Object ... arguments ) {
        return MessageFormat.format(mContext.getResources().getString(stringId),arguments);
    }
}
