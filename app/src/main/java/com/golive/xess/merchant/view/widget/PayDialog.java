package com.golive.xess.merchant.view.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.golive.xess.merchant.R;
import com.golive.xess.merchant.XessConfig;
import com.golive.xess.merchant.base.BaseActivity;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.NetComponent;
import com.golive.xess.merchant.model.api.body.PayBody;
import com.golive.xess.merchant.model.entity.CommonEntity;
import com.golive.xess.merchant.model.entity.PayEntity;
import com.golive.xess.merchant.model.entity.PayEvent;
import com.golive.xess.merchant.utils.AppUtil;
import com.golive.xess.merchant.utils.RxBus;
import com.golive.xess.merchant.utils.SharedPreferencesUtils;

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class PayDialog extends Dialog {


    @BindView(R.id.dialog_title_tv)
    TextView dialogTitleTv;
    @BindView(R.id.dialog_close_tv)
    TextView dialogCloseTv;
    @BindView(R.id.weixin_pay_iv)
    ImageView weixinPayIv;
    @BindView(R.id.alipay_pay_iv)
    ImageView alipayPayIv;
    @BindView(R.id.pay_money_tv)
    TextView payMoneyTv;
    @BindView(R.id.alipay_logo_iv)
    ImageView alipayLogoIv;
    @BindView(R.id.alipay_pb)
    ProgressBar alipayPb;
    @BindView(R.id.alipay_wv)
    WebView svWebView;

    private BaseActivity mContext;
    private Double payNum;
    Subscription rxSubscription;
    public PayDialog(BaseActivity context, Double num) {
        super(context, R.style.ShareDialog);
        this.mContext = context;
        this.payNum = num;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay);
        ButterKnife.bind(this);
        dialogTitleTv.setText("菜豆充值");
        payMoneyTv.setText(getFormatString(payNum, payNum / 100));
        initWebView();
        initData();
        initRxBus();
    }

    private void initRxBus() {
        rxSubscription = RxBus.getInstance().toObserverable(PayEvent.class)
                .subscribe(new Action1<PayEvent>() {
                    @Override
                    public void call(PayEvent studentEvent) {
                        dismiss();
                    }
                });
    }

    private void initData() {
        NetComponent netComponent = XessApp.get(mContext).getNetComponent();
        PayBody body = new PayBody();
        body.setBean(payNum + "");
        body.setClientType("TEST-TCL");
        body.setCoin_type("0");//钱币类型 0 菜豆 1 金币
        body.setDeviceId(SharedPreferencesUtils.getString("deviceNo"));
        body.setLhqId(SharedPreferencesUtils.getString("lhqId"));
        body.setMac(AppUtil.getMacByWifi(mContext));
        body.setProportion("0.01");
        body.setUserNo(SharedPreferencesUtils.getString("storeUid"));
        body.setType(XessConfig._VERSION+"");

        netComponent.getApiService().payCenter(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonEntity<PayEntity>>() {
                    @Override
                    public void call(CommonEntity<PayEntity> payEntityCommonEntity) {
                        String code = payEntityCommonEntity.getCode();
                        String msg = payEntityCommonEntity.getMsg();
                        if ("0".equals(code)) {
                            showPic(payEntityCommonEntity.getData());
                        } else {
                            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(mContext, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }



    @OnClick(R.id.dialog_close_tv)
    public void onClick() {
        dismiss();

    }

    public String getFormatString(Object... arguments) {
        return MessageFormat.format(mContext.getResources().getString(R.string.pay_money_s), arguments);
    }

    private void showPic(PayEntity data) {
        if (data != null) {
            Glide.with(mContext).load(data.getWxPayQr()).into(weixinPayIv);
            mQrcodeUrl = data.getAliPayQr();
            loadZhifubaoWebPage();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        deleteZhifubaoParams();
        if (!rxSubscription.isUnsubscribed()){
            rxSubscription.unsubscribe();
        }
    }


    ////////////////////////////////////////////////////////////////////////////
    //=============================支付宝============================

    public static final int RETRY_DELYED = 10 * 1000;            // 10秒还没加载成功，则重试
    public static final int JSCALL_DELYED = 1000;            // 1秒查执行js
    public static final int REFRESH_QRCODE_DELAYED = 110 * 1000;// 110秒后刷新二维码

    private String mQrcodeUrl;
    private boolean mLoadSuccess = false;
    private int mStep = 0;

    private void resetZhifubaoParams() {
        mQrcodeUrl = "";
        mLoadSuccess = false;
        mStep = 0;
    }
    public void deleteZhifubaoParams() {
        removeMessageWithHandler(handler, M_ZhifubaoQueryWeb);
        removeMessageWithHandler(handler, M_ZhifubaoReloadWeb);
        removeMessageWithHandler(handler, M_ZhifubaoFetchQrCode);
        removeMessageWithHandler(handler, M_ZhifubaodDrawQrCode);
        removeMessageWithHandler(handler, M_ZhifubaodRefresh);
    }
    private void initWebView() {
        WebSettings webSettings = svWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("gb2312");
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        svWebView.addJavascriptInterface(new AndroidBridge(), "android");
        svWebView.setWebChromeClient(new MyWebChromeClient());
        svWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //super.onReceivedSslError(view, handler, error);
                //handler.cancel(); 默认的处理方式，WebView变成空白页
                handler.proceed(); // 接受证书
                // handleMessage(Message msg); 其他处理
            }


        });
        svWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    public class AndroidBridge {
        @JavascriptInterface
        public void callAndroid(String arg) {
            sendMessageWithBundle(handler ,M_ZhifubaodDrawQrCode, "imagedata", arg);
        }
    }

    final class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            result.confirm();
            return true;
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (100 == newProgress) {
                if (0 == mStep) {
                    removeMessageWithHandler(handler, M_ZhifubaoFetchQrCode);
                    handler.sendEmptyMessageDelayed( M_ZhifubaoFetchQrCode, JSCALL_DELYED);
                }
                mStep++;
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    @SuppressLint("NewApi")
    private void fetchQrCode() {
        String jsCode = /*SettingHelper.getInstance().getZhifubaoJavascript();
        if (StringHelper.isBlank(jsCode))
			jsCode =*/ "document.getElementsByTagName('canvas')[0].toDataURL('image/png')";
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                svWebView.evaluateJavascript("javascript:" + jsCode + ";", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        sendMessageWithBundle(handler, M_ZhifubaodDrawQrCode, "imagedata", value);
                    }
                });
            } else {
                svWebView.loadUrl("javascript:window.android.callAndroid(" + jsCode + ");");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void refreshQrCode() {
        removeMessageWithHandler(handler, M_ZhifubaoQueryWeb);
        removeMessageWithHandler(handler, M_ZhifubaoReloadWeb);
        removeMessageWithHandler(handler, M_ZhifubaoFetchQrCode);
        removeMessageWithHandler(handler, M_ZhifubaodDrawQrCode);
        removeMessageWithHandler(handler, M_ZhifubaodRefresh);
        if (alipayPayIv != null) alipayPayIv.setImageBitmap(null);
        alipayPb.setVisibility(View.VISIBLE);
        alipayLogoIv.setVisibility(View.GONE);
        loadZhifubaoWebPage();
    }
    private void loadZhifubaoWebPage() {
        alipayPb.setVisibility(View.VISIBLE);
        alipayLogoIv.setVisibility(View.GONE);
        try {
            svWebView.clearCache(true);
            svWebView.addJavascriptInterface(new AndroidBridge(), "android");
            svWebView.loadUrl(mQrcodeUrl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        removeMessageWithHandler(handler, M_ZhifubaoReloadWeb);
        handler.sendEmptyMessageDelayed(M_ZhifubaoReloadWeb, RETRY_DELYED);
    }

    private void drawQrcode(final String value) {
        try {
            String data = value.replace("data:image/png;base64,", "");
            byte[] decode = Base64.decode(data, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
            if (null != bitmap) {
                alipayPayIv.setImageBitmap(bitmap);
                alipayPb.setVisibility(View.GONE);
                alipayLogoIv.setVisibility(View.VISIBLE);
                removeMessageWithHandler(handler, M_ZhifubaodRefresh);
//                handler.sendEmptyMessageDelayed( M_ZhifubaodRefresh, REFRESH_QRCODE_DELAYED);
                mLoadSuccess = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //=============================消息处理============================
    private final int M_HideLoading = 5;
    private final int M_ZhifubaoQueryWeb = 7;
    private final int M_ZhifubaoReloadWeb = 8;
    private final int M_ZhifubaoFetchQrCode = 9;
    private final int M_ZhifubaodDrawQrCode = 10;
    private final int M_ZhifubaodRefresh = 11;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case M_HideLoading:
                    alipayPb.setVisibility(View.GONE);
                    break;
                case M_ZhifubaoQueryWeb:
                    loadZhifubaoWebPage();
                    break;
                case M_ZhifubaoReloadWeb:
                    if (!mLoadSuccess) {
                        loadZhifubaoWebPage();
                    }
                    break;
                case M_ZhifubaoFetchQrCode:
                    fetchQrCode();
                    break;
                case M_ZhifubaodDrawQrCode:
                    drawQrcode(msg.getData().getString("imagedata"));
                    break;
                case M_ZhifubaodRefresh:
                    refreshQrCode();
                    break;
            }
        }

    };

    public void removeMessageWithHandler(Handler handler, int what) {
        if (handler != null) {
            try {
                handler.removeMessages(what);
            } catch (Exception ex) {

            }
        }
    }

    public static void sendMessageWithBundle(Handler handler, int what, Object... pair) {
        if (handler != null) {
            Message msg = Message.obtain(handler);
            msg.what = what;
            msg.setData(putBundle(pair));
            handler.sendMessage(msg);
        }
    }

    public static Bundle putBundle(Object... args) {
        Bundle data = new Bundle();
        if (args != null && args.length >= 2) {
            for (int i = 0, count = args.length; i < count; i += 2) {
                Object okey = args[i];
                Object ovalue = args[i + 1];
                if (okey == null || ovalue == null)
                    continue;
                if (okey instanceof String) {
                    String key = (String) okey;
                    if (ovalue instanceof String)
                        data.putString(key, (String) ovalue);
                    else if (ovalue instanceof Integer)
                        data.putInt(key, (Integer) ovalue);
                    else if (ovalue instanceof Boolean)
                        data.putBoolean(key, (Boolean) ovalue);
                    else if (ovalue instanceof Long)
                        data.putLong(key, (Long) ovalue);
                }
            }
        }
        return data;
    }


}
