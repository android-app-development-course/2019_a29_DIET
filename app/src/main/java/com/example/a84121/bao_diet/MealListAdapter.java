package com.example.a84121.bao_diet;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 黎活宝 on 2019/11/26.
 */

public class MealListAdapter extends BaseAdapter implements View.OnClickListener {
    private List<String> mList;
    private Context mContext;
    private InnerItemOnclickListener mListener;

    public MealListAdapter(List<String> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        // TODO 自动生成的方法存根
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO 自动生成的方法存根
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO 自动生成的方法存根
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.meal_item_list,
                    null);
            viewHolder.bt1 = (ImageButton) convertView.findViewById(R.id.add_meal_item_btn);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.meal_item_each);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.bt1.setOnClickListener(this);
        viewHolder.bt1.setTag(position);
        viewHolder.tv.setText(mList.get(position));
        return convertView;
    }

    public final static class ViewHolder {
        ImageButton bt1;
        TextView tv;
    }

    interface InnerItemOnclickListener {
        void itemClick(View v);
    }

    public void setOnInnerItemOnClickListener(InnerItemOnclickListener listener){
        this.mListener=listener;
    }

    @Override
    public void onClick(View v) {
        mListener.itemClick(v);
    }
}
