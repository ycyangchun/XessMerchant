package com.golive.xess.merchant.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.golive.xess.merchant.utils.DisplayUtils;
import com.orhanobut.logger.Logger;

import java.text.MessageFormat;

/**
 * Created by YangChun .
 * on 2017/3/6.
 */

public abstract class BaseActivity extends FragmentActivity {
    private int height;
    private int width;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        height = DisplayUtils.getScreenHeight(this);
        width = DisplayUtils.getScreenWidth(this);
        Logger.d("height "+height+" width "+width +" 是否竖屏 "+DisplayUtils.isPortrait(this));
        if( height > width && DisplayUtils.isPortrait(this)){
            requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    /**
     * 获取资源文件字符串
     *
     * @param context
     * @param stringId
     * @param arguments
     * @return
     */
    public String getMessageFormatString(Context context, int stringId ,Object ... arguments ) {
        return MessageFormat.format(context.getResources().getString(stringId),arguments);
    }
}
