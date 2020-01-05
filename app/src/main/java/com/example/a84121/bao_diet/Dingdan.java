package com.example.a84121.bao_diet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 84121 on 2019/12/16.
 */

public class Dingdan extends AppCompatActivity {
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dingdan);
        back=(Button)findViewById(R.id.btn_goback);
        ListView lvMain = (ListView) findViewById(R.id.lv_main);
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        map.put("avatar", R.drawable.chicken);
        map.put("nickname", "订单日期：2019-12-12");
        map.put("newsSource", "全家桶");
        map.put("body", "实付：18元");
        list.add(map);

        map = new HashMap<>();
        map.put("avatar", R.drawable.chicken_breast);
        map.put("nickname", "订单日期：2019-12-12");
        map.put("newsSource", "盐焗鸡");
        map.put("body", "实付：18元");
        list.add(map);

        map = new HashMap<>();
        map.put("avatar", R.drawable.dinner);
        map.put("nickname","订单日期：2019-12-12");
        map.put("newsSource", "鸡胸肉");
        map.put("body", "实付：18元");
        list.add(map);

        map = new HashMap<>();
        map.put("avatar", R.drawable.food2);
        map.put("nickname", "订单日期：2019-12-12");
        map.put("newsSource", "牛肉饼");
        map.put("body", "实付：18元");
        list.add(map);

        map = new HashMap<>();
        map.put("avatar", R.drawable.egg);
        map.put("nickname", "订单日期：2019-12-12");
        map.put("newsSource", "好吃的");
        map.put("body", "实付：18元");
        list.add(map);



        MyAdapter adapter = new MyAdapter(this);
        adapter.setList(list);
        lvMain.setAdapter(adapter);
        click();
    }
    public void click() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                Intent mIntent=new Intent();//没有任何参数（意图），只是用来传递数据
                setResult(RESULT_OK,mIntent);
                finish();
            }
        });

    }
}
