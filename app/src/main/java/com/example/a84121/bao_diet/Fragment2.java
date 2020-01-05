package com.example.a84121.bao_diet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class Fragment2 extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_page, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout);
        viewPager = (ViewPager) getActivity().findViewById(R.id.view_pager);
        initToolBar();
    }

    private void initToolBar(){
        List<String> tabTitle=new ArrayList<>();
        tabTitle.add("营养百科");
        tabTitle.add("健康商城");
        tabTitle.add("优选食谱");

        List<Fragment> list_fragment = new ArrayList<>();
        list_fragment.add(new EX_nutritionFragment());
        list_fragment.add(new EX_mallFragment());
        list_fragment.add(new EX_reciptFragment());

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getChildFragmentManager(), list_fragment, tabTitle);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);//将tablayout与viewpager关联起来
    }
}
