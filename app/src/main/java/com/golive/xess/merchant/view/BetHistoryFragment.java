package com.golive.xess.merchant.view;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.XessConfig;
import com.golive.xess.merchant.base.BaseFragment;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerBetComponent;
import com.golive.xess.merchant.di.modules.BetModule;
import com.golive.xess.merchant.model.api.body.BetBody;
import com.golive.xess.merchant.model.api.body.PayBody;
import com.golive.xess.merchant.presenter.BetContract;
import com.golive.xess.merchant.presenter.BetPresenter;
import com.golive.xess.merchant.view.adapter.ItemBetAdapter;
import com.golive.xess.merchant.view.adapter.ItemLeftBetTvAdapter;
import com.google.gson.internal.LinkedTreeMap;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by YangChun .
 * on 2017/3/7.
 * 投注记录
 */

public class BetHistoryFragment extends BaseFragment implements BetContract.View, ItemBetAdapter.BetItemClickListener {


    @BindView(R.id.bet_left_lv)
    ListView betLeftLv;
    @BindView(R.id.bet_lv)
    ListView bet_lv;

    LayoutInflater mInflater;
    ItemBetAdapter adapter;
    List<LinkedTreeMap> linkedTreeMaps;//加分页的时候在处理
    @Inject
    BetPresenter presenter;
    @BindView(R.id.bet_time_start_et)
    TextView betTimeStartEt;
    @BindView(R.id.bet_time_end_et)
    TextView betTimeEndEt;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bet, container, false);
        mInflater = inflater;
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        DaggerBetComponent.builder()
                .netComponent(XessApp.get(activity).getNetComponent())
                .betModule(new BetModule(this)).build().inject(this);

        BetBody body;
        if (XessConfig._VERSION == XessConfig._PERSONAL)
            body = new BetBody(storeUid, "0", "10");
        else
            body = new BetBody(storeUid, "0", "10");
        presenter.query(body);
    }

    private void initView() {
        List arr = Arrays.asList("全部订单", "中奖订单", "未开奖订单");
        betLeftLv.setAdapter(new ItemLeftBetTvAdapter(mInflater, arr));
        betLeftLv.setSelection(0);

    }

    /////////////////BetContract.View////////////////

    @Override
    public void successPay(List<LinkedTreeMap> payEntityList) {

    }

    @Override
    public void showOnFailure(Throwable throwable, int type) {
        Toast.makeText(activity, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    int focusPosition = -1;//Selected焦点在哪个position

    @Override
    public void successQuery(List<LinkedTreeMap> ordersEntityList) {
        adapter = new ItemBetAdapter(mInflater, ordersEntityList, this);
        bet_lv.setAdapter(adapter);

        bet_lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //设置焦点
                if (focusPosition != position) {
                    focusPosition = position;
                    adapter.setItemFocus(parent, view, position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    /////////////////BetContract.View////////////////
    //////////////ItemBetAdapter.BetItemClickListener //////////////

    @Override
    public void betItemClick(View v, int position, String orderNo, String type) {
        System.out.println(type + " position " + position + " orderNo " + orderNo);
        Intent intent = new Intent();
        if ("detail".equals(type)) {
            intent.setClass(activity, DialogBetDetailActivity.class);
            intent.putExtra("orderNo", orderNo);
            activity.startActivity(intent);
        }
    }
    //////////////ItemBetAdapter.BetItemClickListener //////////////
    @OnClick({R.id.bet_time_start_et, R.id.bet_time_end_et , R.id.bet_query_bt ,R.id.bet_statement_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bet_time_start_et:
                showPopupWindow(betTimeStartEt);
                break;
            case R.id.bet_time_end_et:
                showPopupWindow(betTimeEndEt);
                break;
            case R.id.bet_query_bt:
                break;
            case R.id.bet_statement_bt:
                PayBody payBody = new PayBody();
                payBody.setDeviceNo(deviceNo);
                payBody.setStoreUid(storeUid);
                payBody.setOids("201703090918506047175100124#201703090759354116649100119");
                presenter.batchPay(payBody);
                break;
        }
    }

    private Calendar calendar;
    private int year;
    private int month;
    private int day;

    private void showPopupWindow(final TextView mView) {
        View contentView = LayoutInflater.from(activity).inflate(
                R.layout.popup_datepicker, null);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        DatePicker datePicker = (DatePicker) contentView.findViewById(R.id.myDatePicker);
        // 获取日历对象
        calendar = Calendar.getInstance();
        // 获取当前对应的年、月、日的信息
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        mView.setText(year+" "+month+" "+day);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mView.setText(year+" "+monthOfYear+" "+dayOfMonth);
            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("mengdd", "onTouch : ");
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 设置好参数之后再show
        popupWindow.showAsDropDown(mView);

    }
}
