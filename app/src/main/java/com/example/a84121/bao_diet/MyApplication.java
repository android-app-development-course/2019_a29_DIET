package com.example.a84121.bao_diet;

/**
 * Created by 84121 on 2019/11/26.
 */

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whs on 17/5/1.
 */

public class MyApplication extends Application {
    public List<Javabean> data =new ArrayList<>();
    private static Context context;

    public List<Javabean> getItemData() {
        return data;
    }

    public void addItemData(Javabean item) {
        data.add(item);
    }
    public static Context getContext(){
        return context;
    }
}