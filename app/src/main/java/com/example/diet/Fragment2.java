package com.example.diet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.button;


public class Fragment2 extends Fragment {

    //Tab标题
    private String[] title=new String[]{"营养百度","健康商城","优选食谱"};
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabLayout.Tab tabAtOne;
    private TabLayout.Tab tabAttwo;
    private TabLayout.Tab tabAtthree;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.explore_page,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /*private void initView(){
        viewPager=(ViewPager)viewPager.findViewById(R.id.view_pager);
        tabLayout=(TabLayout)tabLayout.findViewById(R.id.tab_layout);
        //使用适配器将ViewPager与Fragment绑定在一起
        //viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        //将TabLayout与ViewPager绑定
        tabLayout.setupWithViewPager(viewPager);

        //获取单个Tab
        tabAtOne=tabLayout.getTabAt(0);
        tabAttwo=tabLayout.getTabAt(1);
        tabAtthree=tabLayout.getTabAt(2);
        //设置Tab文字
        tabAtOne.setText(title[0]);
        tabAttwo.setText(title[1]);
        tabAtthree.setText(title[2]);
    }*/
}
