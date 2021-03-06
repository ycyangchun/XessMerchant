package com.golive.xess.merchant.view;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseFragment;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerBetComponent;
import com.golive.xess.merchant.di.modules.BetModule;
import com.golive.xess.merchant.model.api.body.AccountBody;
import com.golive.xess.merchant.model.api.body.BetBody;
import com.golive.xess.merchant.model.api.body.ReplacePayBody;
import com.golive.xess.merchant.model.entity.AccountEntity;
import com.golive.xess.merchant.model.entity.CommonEntity;
import com.golive.xess.merchant.model.entity.MarketEntity;
import com.golive.xess.merchant.model.entity.PageEntity;
import com.golive.xess.merchant.presenter.BetContract;
import com.golive.xess.merchant.presenter.BetPresenter;
import com.golive.xess.merchant.utils.AppUtil;
import com.golive.xess.merchant.utils.RxBus;
import com.golive.xess.merchant.view.adapter.ItemBetAdapter;
import com.golive.xess.merchant.view.adapter.ItemLeftBetTvAdapter;
import com.golive.xess.merchant.view.widget.AccountDialog;
import com.golive.xess.merchant.view.widget.DialogErr;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

import static com.golive.xess.merchant.presenter.BetContract.GAINDATA;
import static com.golive.xess.merchant.presenter.BetContract.GAINMORE;

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
    @BindView(R.id.bet_time_start_et)
    TextView betTimeStartEt;
    @BindView(R.id.bet_time_end_et)
    TextView betTimeEndEt;
    @BindView(R.id.bet_orders_tv)
    TextView betOrdersTv;
    @BindView(R.id.note_tv)
    TextView noteTv;
    @BindView(R.id.bet_money_tv)
    TextView betMoneyTv;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.bet_mobile_et)
    EditText betMobileEt;
    @BindView(R.id.market_tv)
    TextView marketTv;
    @BindView(R.id.wallet_pb)
    ProgressBar walletPb;

    @Inject
    BetPresenter presenter;

    LayoutInflater mInflater;
    ItemBetAdapter adapter;
    ItemLeftBetTvAdapter adapterLeft;//left  list
    List<LinkedTreeMap> linkedTreeMaps;//加分页的时候在处理

    private int pageSize = 10;
    private static int pageNo = 0;
    private BetBody body;
    private List<String> arrTitle;
    Subscription rxSubscription;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bet, container, false);
        mInflater = inflater;
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

        DaggerBetComponent.builder()
                .netComponent(XessApp.get(activity).getNetComponent())
                .betModule(new BetModule(this)).build().inject(this);

        initView();
        initRxBus();
    }

    // 推送更新数据
    public void initRxBus() {
        rxSubscription = RxBus.getInstance().toObserverable(CommonEntity.class)
                .subscribe(new Action1<CommonEntity>() {
                    @Override
                    public void call(CommonEntity push) {
                        if("Yes".equals(push.getMsg())){
                            Toast.makeText(activity, "收到一条新的代付订单", Toast.LENGTH_SHORT).show();
                            try {
                                adapterLeft.onItemSelect(1);
                                setParamToGetList(body,1);
                                getListRefreshData();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

    }
    private void initView() {
        ////////////左侧 list//////////////
        arrTitle = Arrays.asList("全部订单", "需代付", "已代付", "中奖订单", "未开奖订单");
        adapterLeft = new ItemLeftBetTvAdapter(mInflater, arrTitle);
        betLeftLv.setAdapter(adapterLeft);
        betLeftLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapterLeft.onItemSelect(position);
                setParamToGetList(body,position);
                getListRefreshData();
            }
        });
        //////////////右侧 list//////////////
        body = new BetBody(storeUid,deviceNo, pageNo + "", pageSize + "");
        linkedTreeMaps = new ArrayList<>();
        adapter = new ItemBetAdapter(mInflater, activity, linkedTreeMaps, this);
        //获取全部数据
        getListRefreshData();
        //销售额
        presenter.market(new ReplacePayBody(storeUid, deviceNo));
        bet_lv.setAdapter(adapter);
        bet_lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 当不滚动时
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    // 判断是否滚动到底部
                    if (view.getLastVisiblePosition() == view.getCount() - 1 ) {
                        if(linkedTreeMaps.size() % pageSize == 0) {// 取余数 是pagaSiza 的倍数时，可以加载更多
                            //加载更多功能的代码
                            pageNo++;
                            getListMoreData();
                        } else {
                            if(linkedTreeMaps.size() > pageSize) //数据太少时，不提示
                                Toast.makeText(activity, "已无更多", Toast.LENGTH_SHORT).show();
                        }
                    }
                    // 判断滚动到顶部
                    if (view.getFirstVisiblePosition() == 0) {
                        getListRefreshData();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    /*
    需代付  invest_state : 10210  origin：1
    已代付   invest_state ：10200  origin：1
    中奖订单  invest_state: ：10200   order_state: 10300  win_state: 10400 origin：1
    未开奖订单  invest_state: ：10200   order_state: 10300  win_state: 10401 origin：1
    */
    //获取列表数据
    public void setParamToGetList(BetBody betBody , int position){
        switch(position){
            case 0:
                betBody.setParam("","","","");
                break;
            case 1:
                betBody.setParam("10210","","","1");
                break;
            case 2:
                betBody.setParam("10200","","","1");
                break;
            case 3:
                betBody.setParam("10200","10300","10400","1");
                break;
            case 4:
                betBody.setParam("10200","10300","10401","1");
                break;
        }
    }

    /////////////////BetContract.View////////////////

    @Override
    public void successAccount(AccountEntity accountEntity) {
        AccountDialog accountDialog = new AccountDialog(activity, accountEntity);
        accountDialog.show();
        accountDialog.setCanceledOnTouchOutside(true);
    }

    @Override
    public void successPay(List<LinkedTreeMap> payEntityList) {
        String describe = "";
        for (LinkedTreeMap map : payEntityList) {
            describe += (String) map.get("describe") + "\n";
        }

        getListRefreshData();
        Toast.makeText(activity, describe, Toast.LENGTH_LONG).show();

    }

    @Override
    public void showOnFailure(Throwable throwable, int type ,int gain) {
        new DialogErr(activity, throwable.getMessage()).show();
        if (gain == GAINMORE) {
            pageNo--;
        }
    }


    @Override
    public void successQuery(List<LinkedTreeMap> ordersEntityList, PageEntity.DataBean.OtherBean otherBean
            ,int gain) {
        if(ordersEntityList != null && ordersEntityList.size() != 0) {
            if (gain == GAINDATA) linkedTreeMaps.clear();
            linkedTreeMaps.addAll(ordersEntityList);
            adapter.notifyDataSetChanged();
        } else{
            if (gain == GAINMORE) {
                pageNo--;
                Toast.makeText(activity, "已无更多", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "暂无数据", Toast.LENGTH_SHORT).show();
            }
        }

        if (otherBean != null) {
            betOrdersTv.setText(getMessageFormatString(activity, R.string.bet_orders_s, otherBean.getSingular()));
            noteTv.setText(getMessageFormatString(activity, R.string.bet_note_s, otherBean.getNum()));
            betMoneyTv.setText(getMessageFormatString(activity, R.string.bet_money_s, (otherBean.getAmount() / 100) + ""));
        } else {
            betOrdersTv.setText(getMessageFormatString(activity, R.string.bet_orders_s, "0"));
            noteTv.setText(getMessageFormatString(activity, R.string.bet_note_s, "0"));
            betMoneyTv.setText(getMessageFormatString(activity, R.string.bet_money_s, "0"));
        }
    }

    @Override
    public void successMarket(MarketEntity marketEntity) {
        if (marketEntity != null) {
            marketTv.setText(getMessageFormatString(activity, R.string.bet_day_money_s, marketEntity.getDayNum(),
                    marketEntity.getDayAmount() / 100 + "",
                    marketEntity.getMonthNum(), marketEntity.getMonthAmount() / 100 + ""));
        }
    }

    @Override
    public void loadProgress() {
        walletPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        walletPb.setVisibility(View.GONE);
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
        } else if ("option_10210".equals(type)) {// 需代付  invest_state : 10210  origin：1
            bathPay(orderNo);
        }
    }

    //////////////ItemBetAdapter.BetItemClickListener //////////////

    @OnCheckedChanged(R.id.checkBox)
    public void checkBox(CompoundButton buttonView, boolean isChecked) {
        if (adapter == null) return;
        if (isChecked)
            adapter.selectAll();
        else
            adapter.againstAll();
    }


    @OnClick({R.id.bet_time_start_et, R.id.bet_time_end_et, R.id.bet_query_bt, R.id.bet_statement_bt, R.id.bet_bath_pay_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bet_time_start_et:
                showPopupWindow(betTimeStartEt);
                break;
            case R.id.bet_time_end_et:
                showPopupWindow(betTimeEndEt);
                break;
            case R.id.bet_query_bt:
                clickQuery();
                break;
            case R.id.bet_statement_bt:
                String mobile = betMobileEt.getText().toString().trim();
                if (!TextUtils.isEmpty(mobile))
                    presenter.account(new AccountBody(mobile));
                else
                    Toast.makeText(activity, "未输入手机号！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bet_bath_pay_bt:
                String p = adapter.getPayListS();
                if (!TextUtils.isEmpty(p))
                    bathPay(p);
                else
                    Toast.makeText(activity, "未选择代付项！", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    // 代付
    private void bathPay(String orderNo) {
        ReplacePayBody payBody = new ReplacePayBody();
        payBody.setDeviceNo(deviceNo);
        payBody.setStoreUid(storeUid);
        payBody.setOids(orderNo);
        presenter.batchPay(payBody);
    }
    // 输入条件查询
    private void clickQuery() {
        linkedTreeMaps.clear();
        adapter.notifyDataSetChanged();
        String st = betTimeStartEt.getText().toString().trim();
        String et = betTimeEndEt.getText().toString().trim();
        String mo = betMobileEt.getText().toString().trim();
        if(!TextUtils.isEmpty(mo) && !AppUtil.isMobileNO(mo)){
            Toast.makeText(activity, "手机格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        body.conditionParam(st,et,mo,"");
        presenter.query(body, GAINDATA);
//        body.clearConditionParam();
    }

    //获取 获更新数据
    private void getListRefreshData() {
        pageNo = 0;
        linkedTreeMaps.clear();
        adapter.notifyDataSetChanged();
        if(body != null && presenter != null ) {
            body.setPageNo(pageNo + "");
            presenter.query(body, GAINDATA);
        }
    }
    //获取 更多
    private void getListMoreData() {
        body.setPageNo(pageNo+"");
        presenter.query(body,GAINMORE);
    }

    ////////////////////////日期控件////////////////////////////
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
        mView.setText(year + " " + month + " " + day);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mView.setText(year + " " + monthOfYear + " " + dayOfMonth);
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

    ////////////////////////////////////////////////////////



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (!rxSubscription.isUnsubscribed()) {
            rxSubscription.unsubscribe();
        }
    }
}
