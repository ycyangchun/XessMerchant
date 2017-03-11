package com.golive.xess.merchant.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.golive.xess.merchant.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemBetAdapter extends BaseAdapter {

    private List<Integer> objects ;

    private LayoutInflater layoutInflater;
    private BetItemClickListener betItemClickListener;

    public ItemBetAdapter(LayoutInflater mInflater ,List<Integer> obj , BetItemClickListener listener) {
        this.betItemClickListener = listener;
        this.layoutInflater = mInflater;
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
        initializeViews(getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(Integer object, ViewHolder holder, final int position) {
        holder.orderTimeTv.setText("2017-12-12 11:11:11");
        holder.mobileNumTv.setText("1234567897");
        holder.lotteryTypeTv.setText("双色球");
        holder.issueTv.setText("第123456期");
        holder.numTv.setText("1000注");
        holder.betKidneyTv.setText("2000彩豆");
        holder.betStatusTv.setText("中200彩豆");
        holder.detail_tv.setText("详情");
        holder.optionTv.setText("代付");
        holder.detail_tv.setTag(R.id.details_id,position);
        holder.optionTv.setTag(R.id.option_id,position);
        holder.detail_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betItemClickListener.betItemClick(v,position,"detail");
            }
        });
        holder.optionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betItemClickListener.betItemClick(v,position,"option");
            }
        });
    }

    public interface  BetItemClickListener{
        void betItemClick(View v,int position, String type);
    }
    Button bt;
    public void setItemFocus(AdapterView<?> parent, View view, final int position){
        bt = (Button) view.findViewById(R.id.detail_bt);
        bt.postDelayed(new Runnable() {//Android加载刷新UI的时候，是从左到右，从上到下的顺序，正在加载的过程中，
            // 如果此时requestFocus(),的话，有可能此时还没把整个界面刷新好，导致requestFocus无效。
            @Override
            public void run() {
                bt.requestFocus();
            }
        }, 150);

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
        @BindView(R.id.option_bt)
        Button optionTv;
        @BindView(R.id.detail_bt)
        Button detail_tv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
