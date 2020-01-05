package com.example.a84121.bao_diet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by 84121 on 2019/12/17.
 */

public class MycollectAdapter extends BaseAdapter {
    List<Map<String, Object>> list;
    LayoutInflater inflater;

    public MycollectAdapter(Context context){
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
        MycollectAdapter.ViewHolder holder = null;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.collect_item, null);
            holder = new MycollectAdapter.ViewHolder();
            holder.head = (RoundImageView) convertView.findViewById(R.id.collect_head);
            holder.collect_id = (TextView)convertView.findViewById(R.id.collect_id);
            holder.collect_pic = (ImageView)convertView.findViewById(R.id.collect_pic);
            holder.collect_text = (TextView) convertView.findViewById(R.id.collect_text);
            convertView.setTag(holder);
        } else {
            holder = (MycollectAdapter.ViewHolder) convertView.getTag();
        }

        Map map = list.get(position);
        holder.head.setImageResource((Integer) map.get("avatar"));
        holder.collect_id.setText((String)map.get("nickname"));
        holder.collect_pic.setImageResource((Integer) map.get("newsSource"));
        holder.collect_text.setText((String)map.get("body"));

        return convertView;
    }

    public static class ViewHolder {
        ImageView collect_pic;

        TextView collect_id,collect_text;
        RoundImageView head;

    }
}
