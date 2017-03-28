package com.golive.xess.merchant.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.golive.xess.merchant.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogProtocol extends Dialog {

    @BindView(R.id.protocol_tv)
    TextView protocolTv;
    @BindView(R.id.dialog_title_tv)
    TextView dialogTitleTv;

    public DialogProtocol(Context context, String themeResId) {
        super(context, R.style.ShareDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_protocol);
        ButterKnife.bind(this);
        dialogTitleTv.setText("Xess mini 用户服务协议");
        protocolTv.setText("");
    }

    @OnClick(R.id.dialog_close_tv)
    public void onClick() {
        dismiss();
    }
}
