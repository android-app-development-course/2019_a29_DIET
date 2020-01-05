package com.example.a84121.bao_diet;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黎活宝 on 2019/11/22.
 */

public class EX_mallFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyRecycleViewAdapter recycleViewAdapter;
    private List<ItemBean> itemBeanList=new ArrayList<>();
    private SearchView searchView_mall;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_mall, null);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        searchView_mall=(SearchView)getActivity().findViewById(R.id.searchView_mall);
        //searchView_mall.setIconifiedByDefault(false);
        //searchView_mall.setSubmitButtonEnabled(true);
        initRecycleView();
    }

    public void initRecycleView(){
        recyclerView=(RecyclerView)getActivity().findViewById(R.id.recycleView);
        //将ItemBean中的getList的列表全部添加到itemBeanList中
        itemBeanList.addAll(ItemBean.getList());
        //实例化recycleViewAdapter并调用带参数的构造方法传一个itemBeanList的列表
        recycleViewAdapter=new MyRecycleViewAdapter(itemBeanList);
        //定义布局管理器为网格管理器，设置一行放两个
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(EX_mallFragment.this.getActivity(),2);
        //设置布局管理器为线性布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置适配器
        recyclerView.setAdapter(recycleViewAdapter);
    }

    class MyDecoration extends RecyclerView.ItemDecoration{
        public void getItemOffsets(@NonNull Rect outRect,@NonNull View view,
                                   @NonNull RecyclerView parent,@NonNull RecyclerView.State state){
            super.getItemOffsets(outRect,view,parent,state);
            outRect.set(10,10,10,10);
        }
    }
}
