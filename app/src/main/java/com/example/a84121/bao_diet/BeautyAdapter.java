package com.example.a84121.bao_diet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by 84121 on 2019/11/23.
 */

public class BeautyAdapter extends RecyclerView.Adapter<BeautyAdapter.BeautyViewHolder> {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 数据集合
     */
    private List<Javabean> data;
    Javabean beauty;

    public BeautyAdapter(List<Javabean> data, Context context) {
        this.data = data;
        this.mContext = context.getApplicationContext();
    }

    @Override
    public BeautyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item 布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem, parent, false);

        return new BeautyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BeautyViewHolder holder, int position) {
        //将数据设置到item上
        Log.d("onBindViewHolder","调用了");
        beauty = data.get(position);
        holder.beautyImage.setImageResource(beauty.getImageId());
        holder.nameTv.setText(beauty.getName());
        holder.beautyUser.setImageResource(beauty.getTouxiang());
        holder.userTv.setText(beauty.getUser());
    }



    @Override
    public int getItemCount() {
        return data.size();
    }

     class BeautyViewHolder extends RecyclerView.ViewHolder {
        ImageView beautyImage;
        TextView nameTv;
        RoundImageView beautyUser;
        TextView userTv;

          public BeautyViewHolder(View itemView) {
            super(itemView);
            beautyImage = ( ImageView)itemView.findViewById(R.id.image_item);
            nameTv = (TextView)itemView.findViewById(R.id.name_item);
            beautyUser = (RoundImageView)itemView.findViewById(R.id.image_user);
            userTv = (TextView)itemView.findViewById(R.id.user_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //可以选择直接在本位置直接写业务处理

                    //此处回传点击监听事件
                    if(onItemClickListener!=null){
                        onItemClickListener.OnItemClick(v, data.get(getLayoutPosition()));
                        Log.d("BeautyAdapter",data.get(getLayoutPosition()).toString());
                    }
                }
            });
        }
    }
    public static interface OnItemClickListener {
        /**
         * 接口中的点击每一项的实现方法，参数自己定义
         *
         * @param view 点击的item的视图
         * @param data 点击的item的数据
         */
        public void OnItemClick(View view, Javabean data);
    }

    //需要外部访问，所以需要设置set方法，方便调用
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}