package com.golive.xess.merchant.view.adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.model.entity.OrdersEntity;
import com.golive.xess.merchant.utils.EnumUtils;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemBetAdapter extends BaseAdapter {

    private List<LinkedTreeMap> objects ;

    private LayoutInflater layoutInflater;
    private BetItemClickListener betItemClickListener;

    public ItemBetAdapter(LayoutInflater mInflater ,List<LinkedTreeMap> obj , BetItemClickListener listener) {
        this.betItemClickListener = listener;
        this.layoutInflater = mInflater;
        this.objects =obj;
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
        /*holder.orderTimeTv.setText(order.getCreateTime());
        holder.mobileNumTv.setText(order.getMobile());
        holder.lotteryTypeTv.setText(order.getLid());
        holder.issueTv.setText("第?期");
        holder.numTv.setText(order.getInvestNum()+"注");
        holder.betKidneyTv.setText(order.getAmount()+"彩豆");
        holder.betStatusTv.setText(order.getOrderStateDesc());*/
        holder.orderTimeTv.setText((String)order.get("createTime"));
        holder.mobileNumTv.setText((String)order.get("mobile"));
        holder.lotteryTypeTv.setText(EnumUtils.getName((String)order.get("lid")));
        holder.issueTv.setText("");
        holder.numTv.setText((String)order.get("investNum")+"注");
        holder.betKidneyTv.setText((Double)order.get("amount")+"彩豆");
        holder.betStatusTv.setText((String)order.get("orderStateDesc"));

        holder.detail_tv.setText("详情");
        holder.optionTv.setText("代付");
        holder.detail_tv.setTag(R.id.details_id,position);
        holder.optionTv.setTag(R.id.option_id,position);
        final String orderNo = (String)order.get("orderNo");
        holder.detail_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betItemClickListener.betItemClick(v,position,orderNo ,"detail");
            }
        });
        holder.optionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betItemClickListener.betItemClick(v,position,orderNo,"option");
            }
        });
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
