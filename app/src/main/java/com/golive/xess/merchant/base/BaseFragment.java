package com.golive.xess.merchant.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.golive.xess.merchant.utils.MerchantUtils;
import com.golive.xess.merchant.view.MainActivity;

import java.text.MessageFormat;

/**
 * Created by YangChun .
 * on 2017/3/6.
 */

public abstract class BaseFragment extends Fragment {
    public MainActivity activity;
    public String deviceNo ,storeUid,storeNo,onlineNo;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        deviceNo = MerchantUtils.getDeviceNo();
        storeUid = MerchantUtils.getStoreUid();
        storeNo = MerchantUtils.getStoreNo();
        onlineNo = MerchantUtils.getOnlineNo();
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 获取资源文件字符串
     *
     * @param context
     * @param stringId
     * @param arguments
     * @return
     */
    public String getMessageFormatString(Context context, int stringId, Object... arguments) {
        return MessageFormat.format(context.getResources().getString(stringId), arguments);
    }
}
