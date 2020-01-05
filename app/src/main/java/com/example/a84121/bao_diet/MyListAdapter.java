package com.example.a84121.bao_diet;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MyListAdapter extends BaseAdapter {
    private List<User> mlist;
    private Context context;

    public long getItemId(int position) {
        return 0;
    }

    public MyListAdapter(List<User> mlist,Context context){
        this.mlist=mlist;
        this.context=context;
    }
    public int getCount(){
        return mlist.size();
    }
    public Object getItem(int position){
        return mlist.get(position);
    }
    public static class ViewHolder{
        public TextView mListViewText;
        public TextView flag;
        public LinearLayout each_item;
    }
    public View getView(final int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_layout,null);
            holder.mListViewText= (TextView)convertView.findViewById(R.id.listViewText);
            holder.flag=(TextView)convertView.findViewById(R.id.flag);
            holder.each_item=(LinearLayout)convertView.findViewById(R.id.each_item);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        String name=mlist.get(position).getName();
        holder.mListViewText.setText(name);

        //true和false用来解决服用问题，如果当前标记为选中状态那么item字体颜色变红
        //显示红色竖条，背景变为白色
        if(mlist.get(position).isSelect()){
            holder.mListViewText.setTextColor(Color.parseColor("#71d6ac"));
            holder.mListViewText.setTextSize(17);
            holder.flag.setVisibility(View.VISIBLE);
            holder.each_item.setBackgroundColor(Color.WHITE);
        }else{
            //非选中状态，item字体颜色为灰色，隐藏红色竖条，背景色为灰色
            holder.mListViewText.setTextColor(Color.DKGRAY);
            holder.mListViewText.setTextSize(16);
            holder.flag.setVisibility(View.INVISIBLE);
            holder.each_item.setBackgroundColor(Color.parseColor("#eaeaea"));
        }
        return convertView;
    }
}
