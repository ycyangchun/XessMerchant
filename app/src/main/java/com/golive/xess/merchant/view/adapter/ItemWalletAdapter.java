package com.golive.xess.merchant.view.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.golive.xess.merchant.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemWalletAdapter extends BaseAdapter {

    private List<Integer> objects;

    private LayoutInflater layoutInflater;

    public ItemWalletAdapter(LayoutInflater mInflater ,List<Integer> obj) {
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
            convertView = layoutInflater.inflate(R.layout.item_wallet, parent ,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Integer object, ViewHolder holder) {
        System.out.println("------------");
        holder.itemOptionTv.setText("同步院线-消费");
        holder.itemMoneyTv.setText("彩豆");
        holder.itemDateTv.setText("2017-03-10 16:02:01");
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
