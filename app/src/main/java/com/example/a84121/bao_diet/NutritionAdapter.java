package com.example.a84121.bao_diet;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
  Created by 黎活宝 on 2019/12/17.
 */

public class NutritionAdapter extends BaseAdapter {
    List<FruitsAndVeg> list;
    Context inContext;
    Inflater iF;

    public NutritionAdapter(List<FruitsAndVeg> l, Context c){
        this.list=l;
        this.inContext=c;
    }

    @Override
    public int getCount() {return list.size();}

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        public TextView title;
        public TextView Cal;
        public TextView Fat;
        public TextView Prot;
        public TextView Carb;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(inContext).inflate(R.layout.fruits_veg_item,null);
            holder.title= (TextView)convertView.findViewById(R.id.Fru_Veg_title);
            holder.Cal= (TextView)convertView.findViewById(R.id.Cal);
            holder.Fat= (TextView)convertView.findViewById(R.id.Fat);
            holder.Prot= (TextView)convertView.findViewById(R.id.Prot);
            holder.Carb= (TextView)convertView.findViewById(R.id.Carb);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        FruitsAndVeg FV = list.get(position);
        String name=FV.getName();
        holder.title.setText(name);
        holder.Cal.setText(FV.getCal());
        holder.Fat.setText(FV.getFat());
        holder.Prot.setText(FV.getProt());
        holder.Carb.setText(FV.getCarb());

        if(FV.isSelect()){
            holder.title.setTextColor(Color.parseColor("#94ceb8"));
        }else {
            holder.title.setTextColor(Color.parseColor("#464646"));
        }

        return convertView;
    }
}
