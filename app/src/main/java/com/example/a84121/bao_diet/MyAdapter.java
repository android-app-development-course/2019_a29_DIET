package com.example.a84121.bao_diet;

/**
 * Created by 84121 on 2019/12/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {

    List<Map<String, Object>> list;
    LayoutInflater inflater;

    public MyAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

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
        ViewHolder holder = null;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.dingdan_item, null);
            holder = new ViewHolder();
            holder.date = (TextView) convertView.findViewById(R.id.dingdan_date);
            holder.goods = (TextView)convertView.findViewById(R.id.dingdan_goods);
            holder.price = (TextView)convertView.findViewById(R.id.dingdan_price);
            holder.dingdan_pic = (ImageView) convertView.findViewById(R.id.dingdan_pic);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Map map = list.get(position);
        holder.dingdan_pic.setImageResource((Integer) map.get("avatar"));
        holder.date.setText((String)map.get("nickname"));
        holder.goods.setText((String)map.get("newsSource"));
        holder.price.setText((String)map.get("body"));

        return convertView;
    }

    public static class ViewHolder {
        ImageView dingdan_pic;
        TextView nickname;
        TextView newsSource;
        TextView date,goods,price;

    }
}