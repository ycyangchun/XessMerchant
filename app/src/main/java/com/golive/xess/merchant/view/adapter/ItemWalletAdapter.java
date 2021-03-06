package com.golive.xess.merchant.view.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.utils.EnumUtils;
import com.google.gson.internal.LinkedTreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemWalletAdapter extends BaseAdapter {

    private List<LinkedTreeMap> objects;

    private LayoutInflater layoutInflater;

    public ItemWalletAdapter(LayoutInflater mInflater,List<LinkedTreeMap> obj) {
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
            convertView = layoutInflater.inflate(R.layout.item_wallet, parent ,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(LinkedTreeMap map, ViewHolder holder) {
        holder.itemOptionTv.setText((String) map.get("actionTypeDesc"));

        holder.itemDateTv.setText((String)map.get("createTime"));
        String actionType  = (String) map.get("actionType");
        //1投注 2充值 3零花钱兑换 4消费 5提现 6退款 7返奖8注册赠送
        if("2".equals(actionType) || "3".equals(actionType) ||"6".equals(actionType) ||"7".equals(actionType) ||"8".equals(actionType)){
            holder.itemMoneyTv.setTextColor(Color.parseColor("#00E6FD"));
            holder.itemMoneyTv.setText("+"+map.get("peaConsume"));
        } else {
            holder.itemMoneyTv.setTextColor(Color.parseColor("#bb79ff"));
            holder.itemMoneyTv.setText("-"+map.get("peaConsume"));
        }
    }

    static class ViewHolder {
        @BindView(R.id.item_option_tv)
        TextView itemOptionTv;
        @BindView(R.id.item_money_tv)
        TextView itemMoneyTv;
        @BindView(R.id.item_date_tv)
        TextView itemDateTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
