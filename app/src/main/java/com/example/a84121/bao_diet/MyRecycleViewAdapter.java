package com.example.a84121.bao_diet;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 黎活宝 on 2019/11/26.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> {
    private List<ItemBean> itemBeanList;

    public MyRecycleViewAdapter(List<ItemBean> itemBeanList){
        this.itemBeanList=itemBeanList;
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView grid_img;
        private TextView product_name;
        private TextView product_price;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            grid_img=(ImageView)itemView.findViewById(R.id.iv);
            product_name=(TextView)itemView.findViewById(R.id.tv_text);
            product_price=(TextView)itemView.findViewById(R.id.price);
        }
    }
    public MyRecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_staggere_grid_item,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }
    public void onBindViewHolder(@NonNull MyRecycleViewAdapter.MyViewHolder holder,int position){
        //设置显示的图片
        holder.grid_img.setImageResource(itemBeanList.get(position).getImgId());
        //设置显示的商品名称
        holder.product_name.setText(itemBeanList.get(position).getProduct_name());
        //设置显示的商品价格
        holder.product_price.setText(itemBeanList.get(position).getProduct_price());
    }
    public int getItemCount(){
        return itemBeanList.size();
    }

}
