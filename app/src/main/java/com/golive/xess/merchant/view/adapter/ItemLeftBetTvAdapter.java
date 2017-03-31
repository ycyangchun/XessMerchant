package com.golive.xess.merchant.view.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.golive.xess.merchant.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemLeftBetTvAdapter extends BaseAdapter {

    private List<String> objects = new ArrayList<String>();

    private LayoutInflater layoutInflater;

    public int index = 0;
    public ItemLeftBetTvAdapter(LayoutInflater inflater ,List<String> obj) {
        this.objects = obj;
        this.layoutInflater = inflater;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public String getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_left_bet_tv, parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews(position ,getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(int position, String object, ViewHolder holder) {
        holder.leftBetTv.setText(object);
        if(position == index){
            holder.leftBetTv.setBackgroundColor(Color.parseColor("#5E1E8C"));
        } else{
            holder.leftBetTv.setBackgroundColor(Color.parseColor("#00000000"));
        }
    }

    public void onItemSelect(int p){
        index = p;
        this.notifyDataSetChanged();
    }
    static class ViewHolder {
        @BindView(R.id.left_bet_tv) TextView leftBetTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
