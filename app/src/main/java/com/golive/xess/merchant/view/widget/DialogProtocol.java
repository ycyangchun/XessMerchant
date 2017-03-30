package com.golive.xess.merchant.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.golive.xess.merchant.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogProtocol extends Dialog {

    @BindView(R.id.protocol_wv)
    WebView wvProtocol;
    @BindView(R.id.dialog_title_tv)
    TextView dialogTitleTv;

    private Context context;
    public DialogProtocol(Context context, String themeResId) {
        super(context, R.style.ShareDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_protocol);
        ButterKnife.bind(this);
        dialogTitleTv.setText(context.getString(R.string.protocol_title));
        wvProtocol.setBackgroundColor(Color.argb(0, 0, 0, 0));
        wvProtocol.loadUrl("file:///android_asset/user_protocol.htm");
    }

    @OnClick(R.id.dialog_close_tv)
    public void onClick() {
        dismiss();
    }
}
