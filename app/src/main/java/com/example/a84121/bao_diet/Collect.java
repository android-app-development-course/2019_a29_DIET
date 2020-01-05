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
 * Created by 84121 on 2019/12/17.
 */

public class Collect extends AppCompatActivity {
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collect);
        back=(Button)findViewById(R.id.btn_goback);
        ListView lvMain = (ListView) findViewById(R.id.lv_main2);
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        map.put("avatar", R.drawable.star);
        map.put("nickname", "佩奇");
        map.put("newsSource", R.drawable.food1);
        map.put("body", "真好吃");
        list.add(map);

        map = new HashMap<>();
        map.put("avatar", R.drawable.xiaozhu);
        map.put("nickname", "派大星");
        map.put("newsSource", R.drawable.food2);
        map.put("body", "嘿嘿");
        list.add(map);


        MycollectAdapter adapter = new MycollectAdapter(this);
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
