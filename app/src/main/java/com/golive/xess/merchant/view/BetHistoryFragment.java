package com.golive.xess.merchant.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseFragment;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerBetComponent;
import com.golive.xess.merchant.di.modules.BetModule;
import com.golive.xess.merchant.presenter.BetContract;
import com.golive.xess.merchant.presenter.BetPresenter;
import com.golive.xess.merchant.view.adapter.ItemBetAdapter;
import com.golive.xess.merchant.view.adapter.ItemLeftBetTvAdapter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        presenter.query(null);
    }

    private void initView() {
        List arr = Arrays.asList("全部订单", "中奖订单", "未开奖订单");
        betLeftLv.setAdapter(new ItemLeftBetTvAdapter(mInflater,arr));
        betLeftLv.setSelection(0);

    }

    /////////////////BetContract.View////////////////
    @Override
    public void showOnFailure(Throwable throwable, int type) {

    }

    int focusPosition = -1;//Selected焦点在哪个position
    @Override
    public void successQuery() {
        List list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        adapter = new ItemBetAdapter(mInflater, list, this);
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
    public void betItemClick(View v, int position, String type) {
        System.out.println(type+" position "+position);
    }

    //////////////ItemBetAdapter.BetItemClickListener //////////////

}
