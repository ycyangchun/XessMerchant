package com.golive.xess.merchant.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.golive.xess.merchant.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemBetAdapter extends BaseAdapter {

    private List<Integer> objects ;

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemBetAdapter(Context context,List<Integer> obj) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects =obj;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Integer getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_bet, parent ,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Integer object, ViewHolder holder) {
        holder.orderTimeTv.setText("2017-12-12 11:11:11");
        holder.mobileNumTv.setText("1234567897");
        holder.lotteryTypeTv.setText("双色球");
        holder.issueTv.setText("第123456期");
        holder.numTv.setText("1000注");
        holder.betKidneyTv.setText("2000彩豆");
        holder.betStatusTv.setText("中200彩豆");
        holder.detail_tv.setText("详情");
        holder.optionTv.setText("代付");

    }

    static class ViewHolder {
        @BindView(R.id.order_time_tv)
        TextView orderTimeTv;
        @BindView(R.id.mobile_num_tv)
        TextView mobileNumTv;
        @BindView(R.id.lottery_type_tv)
        TextView lotteryTypeTv;
        @BindView(R.id.issue_tv)
        TextView issueTv;
        @BindView(R.id.num_tv)
        TextView numTv;
        @BindView(R.id.bet_kidney_tv)
        TextView betKidneyTv;
        @BindView(R.id.bet_status_tv)
        TextView betStatusTv;
        @BindView(R.id.option_tv)
        TextView optionTv;
        @BindView(R.id.detail_tv)
        TextView detail_tv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
