package com.golive.xess.merchant.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;


/**
 * Created by YangChun .
 * on 2017/3/31.
 */

public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnScrollChangeListener(OnScrollChangeListener l) {
        super.setOnScrollChangeListener(l);

    }


}
