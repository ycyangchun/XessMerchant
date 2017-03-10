package com.golive.xess.merchant.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseFragment;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerWalletComponent;
import com.golive.xess.merchant.di.modules.WalletModule;
import com.golive.xess.merchant.presenter.WalletContract;
import com.golive.xess.merchant.presenter.WalletPresenter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.list;

/**
 * Created by YangChun .
 * on 2017/3/7.
 * 钱包
 */

public class WalletFragment extends BaseFragment implements WalletContract.View{

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.currently_kidney_tv)
    TextView currentlyKidneyTv;
    @BindView(R.id.win_kidney_tv)
    TextView winKidneyTv;
    @BindView(R.id.top_award_tv)
    TextView topAwardTv;
    @BindView(R.id.win_count_tv)
    TextView winCountTv;
    @BindView(R.id.commission_kidney_tv)
    TextView commissionKidneyTv;
    @BindView(R.id.recharge_bt)
    Button rechargeBt;
    @BindView(R.id.withdraw_bt)
    Button withdrawBt;
    @BindView(R.id.wallet_lv)
    ListView walletLv;

    LayoutInflater mInflater;
    @Inject
    WalletPresenter presenter;

    List list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = inflater;
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);
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
        DaggerWalletComponent.builder()
                .netComponent(XessApp.get(activity).getNetComponent())
                .walletModule(new WalletModule(this)).build().inject(this);
        presenter.getWalletData();
        list = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14);
        walletLv.setAdapter(new MyAdapter());
    }

    ///////////////////WalletContract.View////////////////////////
    @Override
    public void dataFailed(Throwable throwable) {

    }

    @Override
    public void dataSuccess() {
        System.out.println("===================");
    }
    ///////////////////WalletContract.View////////////////////////

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_wallet, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.item_option_tv.setText(position+" 同步院线-消费");
            viewHolder.item_money_tv.setText(position+" 彩豆");
            viewHolder.item_date_tv.setText(position+" 2017-03-10 16:02:01");
            return convertView;
        }

        public class ViewHolder{
            TextView item_option_tv ;
            TextView item_money_tv;
            TextView item_date_tv ;
            public ViewHolder(View convertView){
                item_option_tv = (TextView) convertView.findViewById(R.id.item_option_tv);
                item_money_tv = (TextView) convertView.findViewById(R.id.item_money_tv);
                item_date_tv = (TextView) convertView.findViewById(R.id.item_date_tv);
            }
        }
    }
}
