package com.golive.xess.merchant.view.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.model.entity.OrdersEntity;
import com.golive.xess.merchant.utils.EnumUtils;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemBetAdapter extends BaseAdapter {

    private List<LinkedTreeMap> objects ;

    private LayoutInflater layoutInflater;
    private BetItemClickListener betItemClickListener;
    private List<String> payList;//代付list
    private boolean isSelectAll = false;
    private Context mContext;
    public ItemBetAdapter(LayoutInflater mInflater ,Context context ,List<LinkedTreeMap> obj , BetItemClickListener listener) {
        this.betItemClickListener = listener;
        this.layoutInflater = mInflater;
        this.objects =obj;
        this.mContext = context;
        payList = new ArrayList<>();
    }

    //全选
    public void selectAll(){
        isSelectAll = true;
        payList.clear();
        for(LinkedTreeMap map : objects){
            payList.add((String) map.get("orderNo"));
        }
        this.notifyDataSetChanged();
    }
    //反全选
    public void againstAll(){
        isSelectAll = false;
        payList.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public LinkedTreeMap getItem(int position) {
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

    private void initializeViews(LinkedTreeMap order, ViewHolder holder, final int position) {

        if(isSelectAll) {
            holder.item_cb.setChecked(true);
        } else {
            holder.item_cb.setChecked(false);
        }
        holder.orderTimeTv.setText((String)order.get("createTime"));
        holder.mobileNumTv.setText((String)order.get("mobile"));
        holder.lotteryTypeTv.setText(EnumUtils.Lottery.getName((String)order.get("lid")));
        holder.issueTv.setText((String)order.get("issue"));
        holder.numTv.setText(order.get("investNum")+"注");
        holder.betKidneyTv.setText(order.get("amount")+"菜豆");
        holder.betStatusTv.setText((String)order.get("orderStateDesc"));

        holder.detail_tv.setText("详情");

        holder.detail_tv.setTag(R.id.details_id,position);
        holder.optionTv.setTag(R.id.option_id,position);
        final String orderNo = (String)order.get("orderNo");
        final String investState = (String)order.get("investState");
        final String origin= (String)order.get("origin");
        if("10210".equals(investState)){//"investState":"10210",
            holder.optionTv.setVisibility(View.VISIBLE);
            holder.optionTv.setText("代付");
            holder.optionTv.setBackgroundResource(R.drawable.bet_pay_selector);
        } else if(!"10210".equals(investState) && "1".equals(origin)){
            holder.optionTv.setVisibility(View.VISIBLE);
            holder.optionTv.setText("已代付");
            holder.optionTv.setBackgroundResource(R.color.transparent);
        } else {
            holder.optionTv.setVisibility(View.GONE);
        }
        holder.detail_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betItemClickListener.betItemClick(v,position,orderNo ,"detail");
            }
        });
        holder.optionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betItemClickListener.betItemClick(v,position,orderNo,"option_"+investState);
            }
        });

        holder.item_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(!payList.contains(orderNo))
                        payList.add(orderNo);
                } else{
                    if(payList.contains(orderNo))
                        payList.remove(orderNo);
                }
            }
        });


    }

    public String getPayListS(){
        String s = "";
        for(String p : payList){
            s += p+"#";
        }
        if(s.endsWith("#"))
            s = s.substring(0,s.lastIndexOf("#"));
        return s;
    }

    public interface  BetItemClickListener{
        void betItemClick(View v,int position,String orderNo, String type);
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
        @BindView(R.id.item_cb)
        CheckBox item_cb;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
