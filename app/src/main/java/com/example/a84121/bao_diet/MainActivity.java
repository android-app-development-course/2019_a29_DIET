package com.example.a84121.bao_diet;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.R.attr.name;
import static com.example.a84121.bao_diet.R.id.price;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private BottomNavigationView bottomNavigationView;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private MyTabFragment fragment3;
    private Fragment4 fragment4;
    private Fragment[] fragments;

    private int lastfragment;//用于记录上个选择的Fragment
    // private BallView ballView;
    MyHelper myHelper;
    public DBManager dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        dbHelper = new DBManager(this);
        dbHelper.openDatabase();
        dbHelper.closeDatabase();
//        Uri uri1 =Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getResources().getResourcePackageName(R.drawable.food4) + "/"+ getResources().getResourceTypeName(R.drawable.food4) + "/" + getResources().getResourceEntryName(R.drawable.food4));
//        Uri uri2 =Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getResources().getResourcePackageName(R.drawable.food3) + "/"+ getResources().getResourceTypeName(R.drawable.food3) + "/" + getResources().getResourceEntryName(R.drawable.food3));
//        Uri uri3 =Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getResources().getResourcePackageName(R.drawable.jianshen) + "/"+ getResources().getResourceTypeName(R.drawable.jianshen) + "/" + getResources().getResourceEntryName(R.drawable.jianshen));
//        Uri uri4 =Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getResources().getResourcePackageName(R.drawable.food2) + "/"+ getResources().getResourceTypeName(R.drawable.food2) + "/" + getResources().getResourceEntryName(R.drawable.food2));
//        Uri uri5 =Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getResources().getResourcePackageName(R.drawable.food1) + "/"+ getResources().getResourceTypeName(R.drawable.food1) + "/" + getResources().getResourceEntryName(R.drawable.food1));
//        Uri uri6 =Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getResources().getResourcePackageName(R.drawable.food4) + "/"+ getResources().getResourceTypeName(R.drawable.food4) + "/" + getResources().getResourceEntryName(R.drawable.food4));
//        Uri uri11 =Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getResources().getResourcePackageName(R.drawable.star) + "/"+ getResources().getResourceTypeName(R.drawable.star) + "/" + getResources().getResourceEntryName(R.drawable.star));
//        Uri uri7 =Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getResources().getResourcePackageName(R.drawable.wwb) + "/"+ getResources().getResourceTypeName(R.drawable.wwb) + "/" + getResources().getResourceEntryName(R.drawable.wwb));
//        Uri uri8 =Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getResources().getResourcePackageName(R.drawable.xiaozhu) + "/"+ getResources().getResourceTypeName(R.drawable.xiaozhu) + "/" + getResources().getResourceEntryName(R.drawable.xiaozhu));
//        Uri uri9 =Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getResources().getResourcePackageName(R.drawable.xiaozhu2) + "/"+ getResources().getResourceTypeName(R.drawable.xiaozhu2) + "/" + getResources().getResourceEntryName(R.drawable.xiaozhu2));
//        Uri uri10 =Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getResources().getResourcePackageName(R.drawable.wwb) + "/"+ getResources().getResourceTypeName(R.drawable.wwb) + "/" + getResources().getResourceEntryName(R.drawable.wwb));
//        Uri uri12 =Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getResources().getResourcePackageName(R.drawable.xiaozhu) + "/"+ getResources().getResourceTypeName(R.drawable.xiaozhu) + "/" + getResources().getResourceEntryName(R.drawable.xiaozhu));
//        Log.d("这是uri1",uri1.getPath());
//        Log.d("这是uri2",uri2.getPath());
//        Log.d("这是uri3",uri3.getPath());
//        Log.d("这是uri4",uri4.getPath());
//        Log.d("这是uri5",uri5.getPath());
//        Log.d("这是uri6",uri6.getPath());
//        Log.d("这是uri7",uri7.getPath());
//        Log.d("这是uri8",uri8.getPath());
//        Log.d("这是uri9",uri9.getPath());
//        Log.d("这是uri10",uri10.getPath());
//        Log.d("这是uri11",uri11.getPath());
//        Log.d("这是uri12",uri12.getPath());
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS| WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            //window.setNavigationBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//修改状态栏中的图标文字为深色
        }
        setContentView(R.layout.activity_firstpage);
        initFragment();
    }



    //初始化fragment和fragment数组
    private void initFragment()
    {
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
//        fragment3 = new Fragment3();
        fragment3 = new MyTabFragment();
        fragment4=new Fragment4();
        fragments = new Fragment[]{fragment1,fragment2,fragment3,fragment4};
        lastfragment=0;
        getSupportFragmentManager().beginTransaction().replace(R.id.mainview,fragment1).show(fragment1).commit();
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bv_bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(changeFragment);
    }

    //判断选择的菜单
    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.menu_firstpage:
                {
                    if(lastfragment!=0)
                    {
                        switchFragment(lastfragment,0);
                        lastfragment=0;
                    }
                    return true;
                }
                case R.id.menu_explore:
                {
                    if(lastfragment!=1)
                    {
                        switchFragment(lastfragment,1);
                        lastfragment=1;
                    }
                    return true;
                }
                case R.id.menu_community:
                {
                    if(lastfragment!=2)
                    {
                        switchFragment(lastfragment,2);
                        lastfragment=2;
                    }
                    return true;
                }
                case R.id.menu_me:{
                    if(lastfragment!=3)
                    {
                        switchFragment(lastfragment,3);
                        lastfragment=3;
                    }
                    return true;
                }
            }
            return false;
        }
    };
    //切换Fragment
    private void switchFragment(int lastfragment,int index)
    {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if(!fragments[index].isAdded())
        {
            transaction.add(R.id.mainview,fragments[index]).commit();
        }
//        transaction.show(fragments[index]).commitAllowingStateLoss();
        else
        transaction.show(fragments[index]).commit();
    }
    @Override
    public void onClick(View v){}
    class MyHelper extends SQLiteOpenHelper {
        public MyHelper(Context context) {
            super(context, "itcast.db", null, 2);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE information(_id  INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20), price INTEGER)");
        }
        // 当数据库的版本号增加时调用
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  }
        //每次成功打开数据库后首先被执行
        public void onOpen(SQLiteDatabase db) {super.onOpen(db); }
    }

}
