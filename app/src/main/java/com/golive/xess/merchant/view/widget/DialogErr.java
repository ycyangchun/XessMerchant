package com.golive.xess.merchant.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.golive.xess.merchant.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogErr extends Dialog {

    @BindView(R.id.dialog_context_tv)
    TextView dialogContextTv;

    private String content;
    public DialogErr(Context context, String msg) {
        super(context, R.style.ShareDialog);
        this.content = msg;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_err);
        ButterKnife.bind(this);
        dialogContextTv.setText(content);
    }


    @OnClick({R.id.dialog_close_tv, R.id.right_dialog_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_close_tv:
            case R.id.right_dialog_bt:
                dismiss();
                break;
        }
    }
}
