package com.example.a84121.bao_diet;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

/**
  Created by 黎活宝 on 2019/12/17.
 */

public class Nutrition_List implements AdapterView.OnItemClickListener{

    ListView listView;
    List<FruitsAndVeg> list;
    //ArrayList<FruitsAndVeg> aL;
    LayoutInflater iF;
    Context inContext;
    NutritionAdapter nutritionAdapter;

    public void initData(String title, Cursor cursor, final ListView listView, List<FruitsAndVeg> arrayList, LayoutInflater inflater, Context context){
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(title));
            String Cal = cursor.getString(cursor.getColumnIndex("Cal（kCal/100g）"));
            String Fat = cursor.getString(cursor.getColumnIndex("Fat(g/100g)"));
            String Prot = cursor.getString(cursor.getColumnIndex("Prot(g/100g)"));
            String Carb = cursor.getString(cursor.getColumnIndex("Carb(g/100g)"));
            FruitsAndVeg FV = new FruitsAndVeg(name, Cal, Fat, Prot, Carb);
            arrayList.add(FV);
        }
        this.listView=listView;
        this.list=arrayList;
        //this.aL=arrayList;
        this.iF=inflater;
        this.inContext=context;

        nutritionAdapter=new NutritionAdapter(list,inContext);
        listView.setAdapter(nutritionAdapter);

        listView.setOnItemClickListener(this);
    }
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id){
        for(int i=0;i<list.size();i++){
            list.get(i).setSelect(false);
        }
        list.get(position).setSelect(true);
        nutritionAdapter.notifyDataSetChanged();
    }
}
