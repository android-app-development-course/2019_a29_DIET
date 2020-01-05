package com.example.a84121.bao_diet;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 Created by 黎活宝 on 2019/11/23.
 */

public class Fragment_seafood extends Fragment {

    private ListView listView;
    //private ArrayList<FruitsAndVeg> SeaFoodList;
    private List<FruitsAndVeg> SeaFoodList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.seafood,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SeaFoodList=new ArrayList<>();

        initData();
    }
    public void initData() {
        String name="seafood";
        SQLdm s = new SQLdm();
        SQLiteDatabase db = s.openDatabase(getActivity());
        Cursor cursor = db.rawQuery("select * from seafood_nutrition", null);
        listView = (ListView) getActivity().findViewById(R.id.seafood_list);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        Nutrition_List seafood_nutrition_list=new Nutrition_List();
        seafood_nutrition_list.initData(name,cursor,listView,SeaFoodList,inflater,getActivity());


    }
}
