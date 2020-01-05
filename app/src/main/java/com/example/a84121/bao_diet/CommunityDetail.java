package com.example.a84121.bao_diet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sackcentury.shinebuttonlib.ShineButton;

/**
 * Created by 84121 on 2019/11/24.
 */

public class CommunityDetail extends AppCompatActivity {
    int imageid;
    String name;
    int touxiang,position;
    String user;
    ImageView beautyImage;
    TextView nameTv,userTv,like_num,collect_num;
    RoundImageView beautyUser;
    Button back,share;
    int like,collect;
    boolean likeFlag=false;
    boolean collectFlag=false;
    Javabean login;
    private  int flag=0;
    private Handler mHandler = new Handler();
    private Thread newThread;
    ShineButton star,heart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("c","着陆");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_detail);
        setData();
        beautyImage = ( ImageView)findViewById(R.id.image_item);
        nameTv = (TextView)findViewById(R.id.name_item);
        beautyUser = (RoundImageView)findViewById(R.id.image_user);
        userTv = (TextView)findViewById(R.id.user_item);
        back=(Button)findViewById(R.id.btn_goback);
        share=(Button)findViewById(R.id.btn_share);
        star=(ShineButton)findViewById(R.id.po_image1);
        heart=(ShineButton)findViewById(R.id.po_image0);
        like_num = (TextView)findViewById(R.id.like_num);
        collect_num = (TextView)findViewById(R.id.collect_num);
        beautyImage.setImageResource(imageid);
        nameTv.setText(name);
        beautyUser.setImageResource(touxiang);
        userTv.setText(user);
        like_num.setText(String.valueOf(like));
        collect_num.setText(String.valueOf(collect));
        click();
    }
    public void click() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                Intent mIntent=new Intent();//没有任何参数（意图），只是用来传递数据
                mIntent.putExtra("likeFlag",likeFlag);
                mIntent.putExtra("position",position);
                mIntent.putExtra("collectFlag",collectFlag);
                setResult(RESULT_OK,mIntent);
                finish();
            }
        });
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                likeFlag=!likeFlag;
                if(likeFlag)like_num.setText(String.valueOf(like+1));
                else like_num.setText(String.valueOf(like));
            }
        });
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                collectFlag=!collectFlag;
                if(collectFlag)collect_num.setText(String.valueOf(collect+1));
                else collect_num.setText(String.valueOf(collect));
            }
        });
    }
    public void setData(){
        Intent intent = getIntent();
        position=intent.getIntExtra("position",-1);
        login = (Javabean) intent.getSerializableExtra("data");
        imageid=login.getImageId();
        name=login.getName();
        user=login.getUser();
        touxiang=login.getTouxiang();
        like=login.getLike();
        collect=login.getCollect();

    }


}
