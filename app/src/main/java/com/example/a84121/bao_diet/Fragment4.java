package com.example.a84121.bao_diet;

/**
 * Created by 84121 on 2019/11/23.
 */
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 黎活宝 on 2019/11/20.
 */

public class Fragment4 extends Fragment {
    private TextView textView;
    RoundImageView user;
    Button me_data,my_page,my_page2,setting,me_buy,me_collect,btn_message,me_body;

    //    @Nullable
    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.me_page,container,false);
        me_data=(Button)view.findViewById(R.id.me_data);
        my_page=(Button)view.findViewById(R.id.my_page);
        my_page2=(Button)view.findViewById(R.id.my_page2);
        me_buy=(Button)view.findViewById(R.id.me_buy);
        me_collect=(Button)view.findViewById(R.id.me_collect);
        setting=(Button)view.findViewById(R.id.btn_setting);
        btn_message=(Button)view.findViewById(R.id.btn_message);
        me_body=(Button)view.findViewById(R.id.me_body);
        click();
        return view;

    }
    public void click() {
        my_page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                Intent mIntent=new Intent(getActivity(), MyPage.class);//没有任何参数（意图），只是用来传递数据
                startActivity(mIntent);
            }
        });

        me_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                Intent mIntent=new Intent(getActivity(), FifteenActivity.class);//没有任何参数（意图），只是用来传递数据
                startActivity(mIntent);
            }
        });
        me_body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                Intent mIntent=new Intent(getActivity(), FifteenActivity2.class);//没有任何参数（意图），只是用来传递数据
                startActivity(mIntent);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                Intent mIntent=new Intent(getActivity(), Setting.class);//没有任何参数（意图），只是用来传递数据
                startActivity(mIntent);
            }
        });
        me_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                Intent mIntent=new Intent(getActivity(), Dingdan.class);//没有任何参数（意图），只是用来传递数据
                startActivity(mIntent);
            }
        });
        me_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                Intent mIntent=new Intent(getActivity(), Collect.class);//没有任何参数（意图），只是用来传递数据
                startActivity(mIntent);
            }
        });
        btn_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                Intent mIntent=new Intent(getActivity(), Message.class);//没有任何参数（意图），只是用来传递数据
                startActivity(mIntent);
            }
        });
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        textView=(TextView)getActivity().findViewById(R.id.textView3);
//    }
}
