package com.example.a84121.bao_diet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 黎活宝 on 2019/11/26.
 */

public class MultiplexingFragment extends Fragment {
    private String name;
    private String name2;
    private TextView mText;

    public MultiplexingFragment(){}

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle arguments=getArguments();
        if(arguments!=null){
            name=arguments.getString("string");
            name2=arguments.getString("string2");
        }
    }

    //复用的静态方法，两个参数都是String类型的，代表从Activity里传过来的一个集合的Name，一个数组的url地址
    public static Fragment getMultiplexing(String string,String string2){
        MultiplexingFragment multiplexingFragment=new MultiplexingFragment();
        Bundle bundle=new Bundle();
        //得到并赋值给string 和string2 通过bundle传递
        bundle.putString("string",string);
        bundle.putString("string2",string2);
        multiplexingFragment.setArguments(bundle);
        return multiplexingFragment;
    }

    private void initView(View inflate){
        mText=(TextView)inflate.findViewById(R.id.mText);
        mText.setText(name);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View inflate=inflater.inflate(R.layout.item_fragment,container,false);
        initView(inflate);
        return inflate;

    }
}
