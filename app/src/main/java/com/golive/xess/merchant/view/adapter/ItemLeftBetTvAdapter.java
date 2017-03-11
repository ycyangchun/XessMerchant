package com.golive.xess.merchant.view.adapter;

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
        initializeViews(getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(String object, ViewHolder holder) {
        holder.leftBetTv.setText(object);
    }

    static class ViewHolder {
        @BindView(R.id.left_bet_tv) TextView leftBetTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
