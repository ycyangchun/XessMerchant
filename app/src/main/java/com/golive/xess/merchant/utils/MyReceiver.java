package com.golive.xess.merchant.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.entity.CommonEntity;
import com.golive.xess.merchant.model.entity.PayEvent;
import com.golive.xess.merchant.view.LoginActivity;
import com.golive.xess.merchant.view.widget.DialogErr;
import com.golive.xess.merchant.view.widget.DialogLogin;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hwangjr.rxbus.annotation.Subscribe;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p/>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";


    @Override
    @Subscribe
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        Log.i(TAG,"[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.i(TAG,"[MyReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.i(TAG,"[MyReceiver] 接收到推送下来的自定义消息");
            dealMessage(context, bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.i(TAG,"[MyReceiver] 接收到推送下来的通知");

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.i(TAG, "[MyReceiver] 用户点击打开了通知");

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.i(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
        } else {
            Log.i(TAG,"[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }
                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

    private void dealMessage(Context context, Bundle bundle) {
        String title ;
        String data ;
        try {
        	 title = bundle.getString(JPushInterface.EXTRA_TITLE);
             data = bundle.getString(JPushInterface.EXTRA_MESSAGE);
             data = Des3Util.getInstance(ApiService.SECRET_KEY, ApiService.SECRET_VALUE).decode(Base64Util.decode(data));
            Log.i(TAG, "data：" + data);
            switch (title) {
                case "alipay":
                    payment(context, data);
                    break;
                case "wxpay":
                    payment(context, data);
                    break;
                case "pushOrder":
                    pushOrder(context, data);
                    break;
                case "forcedOffLine":
                    offline(context, data);
                    break;
            }
        } catch (Exception e) {
            Log.e(TAG, "推送消息处理异常:" + e.getMessage());
        }
        
    }


    private void payment(Context context, String data) {
        Gson gson = new Gson();
        Type cla = new TypeToken<PayEvent>() {
        }.getType();
        PayEvent payEvent = gson.fromJson(data,cla);
        RxBus.getInstance().post(payEvent);
    }

    private void pushOrder(Context context, String data) {
        try {

            /*Gson gson = new Gson();
            Type cla = new TypeToken<CommonEntity>() {
            }.getType();
            CommonEntity commonEntity = gson.fromJson(data,cla);
            String code = commonEntity.getCode();
            String msg = commonEntity.getMsg();
            if("0".equals(code) && "success".equals(msg)) {
                RxBus.getInstance().post("Yes");
            }*/
            RxBus.getInstance().post("Yes");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void offline(Context context, String data) {
        //{"code":"0","msg":"该账户在其它客户端登录，系统强制退出!","history":"0CE13C14C920185748CF8B4BBEAADFF5","current":"4FA5B217C7CAF44059196262569E8DE3"}
        try {
            Gson gson = new Gson();
            Type cla = new TypeToken<HashMap<String,String>>() {
            }.getType();
            HashMap map = gson.fromJson(data,cla);
            String msg = (String)map.get("msg");
            String current = (String)map.get("current");
            if(!current.equals(MerchantUtils.getOnlineNo())) {
                RxBus.getInstance().post(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
