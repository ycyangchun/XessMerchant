package com.golive.xess.merchant.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.golive.xess.merchant.utils.AppUtil;
import com.golive.xess.merchant.utils.DeviceUtils;
import com.golive.xess.merchant.utils.DisplayUtils;
import com.golive.xess.merchant.utils.MerchantUtils;
import com.orhanobut.logger.Logger;

import java.text.MessageFormat;

/**
 * Created by YangChun .
 * on 2017/3/6.
 */

public abstract class BaseActivity extends FragmentActivity {
    private int height;
    private int width;
    public String deviceNo ,storeUid ,password ,storeNo ,onlineNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        height = DisplayUtils.getScreenHeight(this);
        width = DisplayUtils.getScreenWidth(this);
        deviceNo = MerchantUtils.getDeviceNo();
        if(TextUtils.isEmpty(deviceNo)){
            deviceNo = AppUtil.getDeviceId(this);
            MerchantUtils.setDeviceNo(deviceNo);
        }
        onlineNo = MerchantUtils.getOnlineNo();
        if(TextUtils.isEmpty(onlineNo)){
            onlineNo = DeviceUtils.getDeviceNo(this);
            MerchantUtils.setOnlineNo(onlineNo);
        }
        storeUid = MerchantUtils.getStoreUid();
        password = MerchantUtils.getPassword();
        storeNo = MerchantUtils.getStoreNo();
        Logger.d("height "+height+" width "+width +" 是否竖屏 "+DisplayUtils.isPortrait(this) );
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
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
    public  String getResourcesString(Context context, int stringId ) {
        return context.getResources().getString(stringId);
    }

}
