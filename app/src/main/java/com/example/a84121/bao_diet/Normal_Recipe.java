package com.example.a84121.bao_diet;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黎活宝 on 2019/12/18.
 */

public class Normal_Recipe extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ImageButton exit_btn;
    private ListView listView;
    private FrameLayout frameLayout;
    private List<User> mList=new ArrayList<>();
    private List<Fragment> fragmentList=new ArrayList<>();
    private FragmentManager fragmentManager;
    private MyListAdapter myListAdapter;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            //window.setNavigationBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//修改状态栏中的图标文字为深色
        }
        setContentView(R.layout.normal_recipe);
        exit_btn=(ImageButton)findViewById(R.id.exit_btn);
        listView=(ListView)findViewById(R.id.recipe_title1);
        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
    }
    private void initData(){
        //左边listView集合添加数据，适配器适配
        listViewData();
        //添加fragment，复用fragment
        //multiplexFragment();
        //默认选中ListView第一条item
        /**replese(0);**/
        //ListView第一条item的Select为true
        for(int i=0;i<mList.size();i++){
            mList.get(i).setSelect(false);
        }
        mList.get(0).setSelect(true);
        //listView点击事件
        listView.setOnItemClickListener(this);
    }
    /*
    * listViewData() 左边listView集合添加数据*/
    private void listViewData(){
        String name[]={"家常菜谱","每日三餐","人群","功效"};
        if(mList.size()==0) {
            for (int i = 0; i < 4; i++)
                mList.add(new User(name[i]));
        }
        //适配器适配
        myListAdapter=new MyListAdapter(mList,getApplicationContext());
        listView.setAdapter(myListAdapter);
    }
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        //切换fragment
        /**replese(position);**/
        //在bean类里写的一个标记 boolean类型的isSelect是关键，默认无状态并设置
        // get set方法
        //集合里所有数据的Select设置为flase,position下标所对应的item的Select为true，
        // 刷新适配器。
        for(int i=0;i<mList.size();i++){
            mList.get(i).setSelect(false);
        }
        mList.get(position).setSelect(true);
        myListAdapter.notifyDataSetChanged();
    }
    /*
    * replese(int position) 根据点击事件的下标切换fragment页面*/
    /**private void replese(int position){
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        for(int i=0;i<fragmentList.size();i++){
            Fragment fragment=fragmentList.get(i);
            transaction.hide(fragment);
        }
        transaction.show(fragmentList.get(position)).commit();
    }**/
}
