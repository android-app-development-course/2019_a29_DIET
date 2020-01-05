package com.example.a84121.bao_diet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by 84121 on 2019/11/28.
 */

public class Setting extends AppCompatActivity {
Button back;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            Log.d("c","着陆");
            super.onCreate(savedInstanceState);
            setContentView(R.layout.setting);
            back=(Button)findViewById(R.id.btn_goback);
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
