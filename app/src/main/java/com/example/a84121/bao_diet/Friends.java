package com.example.a84121.bao_diet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by 84121 on 2019/12/16.
 */

public class Friends extends AppCompatActivity implements View.OnClickListener {
    Button goback;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_friends);
        goback=(Button)findViewById(R.id.btn_goback);
        clickback();
    }
    @Override
    public void onClick(View v) {}
    public void clickback() {
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                Log.d("按返回了","");
                Intent mIntent=new Intent();//没有任何参数（意图），只是用来传递数据
                setResult(RESULT_OK,mIntent);
                finish();
            }
        });}
}
