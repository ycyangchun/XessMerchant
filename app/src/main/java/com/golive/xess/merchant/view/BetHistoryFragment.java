package com.golive.xess.merchant.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.XessConfig;
import com.golive.xess.merchant.base.BaseFragment;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerBetComponent;
import com.golive.xess.merchant.di.modules.BetModule;
import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.api.body.BetBody;
import com.golive.xess.merchant.presenter.BetContract;
import com.golive.xess.merchant.presenter.BetPresenter;
import com.golive.xess.merchant.utils.Base64Util;
import com.golive.xess.merchant.utils.Des3Util;
import com.golive.xess.merchant.utils.SharedPreferencesUtils;
import com.golive.xess.merchant.view.adapter.ItemBetAdapter;
import com.golive.xess.merchant.view.adapter.ItemLeftBetTvAdapter;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by YangChun .
 * on 2017/3/7.
 * 投注记录
 */

public class BetHistoryFragment extends BaseFragment implements BetContract.View , ItemBetAdapter.BetItemClickListener {


    @BindView(R.id.bet_left_lv)
    ListView betLeftLv;
    @BindView(R.id.bet_lv)
    ListView bet_lv;

    LayoutInflater mInflater;
    ItemBetAdapter adapter;
    List<LinkedTreeMap> linkedTreeMaps;//加分页的时候在处理
    @Inject
    BetPresenter presenter;

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
        if(XessConfig._VERSION == XessConfig._PERSONAL)
            body = new BetBody("",deviceNo,"userNo","0","10");
        else
            body = new BetBody(storeNo,deviceNo,"","0","10");
        presenter.query(body);
    }

    private void initView() {
        List arr = Arrays.asList("全部订单", "中奖订单", "未开奖订单");
        betLeftLv.setAdapter(new ItemLeftBetTvAdapter(mInflater,arr));
        betLeftLv.setSelection(0);

    }

    /////////////////BetContract.View////////////////
    @Override
    public void showOnFailure(Throwable throwable,int type) {
        Toast.makeText(activity,throwable.getMessage(),Toast.LENGTH_SHORT).show();
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
                if(focusPosition != position) {
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
    public void betItemClick(View v, int position,String orderNo,String type) {
        System.out.println(type+" position "+position+" orderNo "+orderNo);
        Intent intent = new Intent();
        if("detail".equals(type)){
            intent.setClass(activity,DialogBetDetailActivity.class);
            intent.putExtra("orderNo",orderNo);
            activity.startActivity(intent);
        }
    }

    //////////////ItemBetAdapter.BetItemClickListener //////////////

}
