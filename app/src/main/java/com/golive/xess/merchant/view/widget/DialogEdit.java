package com.golive.xess.merchant.view.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogEdit extends Dialog {

    @BindView(R.id.dialog_title_tv)
    TextView dialogTitleTv;
    @BindView(R.id.edit_et)
    EditText editEt;
    @BindView(R.id.right_dialog_bt)
    Button rightDialogBt;
    private BaseActivity mContext;

    public DialogEdit(BaseActivity context) {
        super(context, R.style.ShareDialog);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit);
        ButterKnife.bind(this);
        dialogTitleTv.setText("其他菜豆");
        rightDialogBt.setText("下一步");
    }


    @OnClick({R.id.dialog_close_tv, R.id.right_dialog_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_close_tv:
                dismiss();
                break;
            case R.id.right_dialog_bt:
                String num = editEt.getText().toString().trim();
                if(!TextUtils.isEmpty(num)) {
                    double i = Double.parseDouble(num);
                    new PayDialog(mContext,i).show();
                    dismiss();
                }

                break;
        }
    }
}
