package com.example.a84121.bao_diet;

/**
 * Created by 84121 on 2019/11/23.
 */

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 黎活宝 on 2019/11/20.
 */

public class Fragment3 extends Fragment {
    MyApplication application;
    private View view;
    private RecyclerView recyclerView;
//    private List<Javabean> data =new ArrayList<>();
    private BeautyAdapter adapter;
    private int flag=0;
    StaggeredGridLayoutManager recyclerViewLayoutManager;
    /**加载图片前的时间**/
    Long mLoadStart = 0L;
    /**加载图片完成的时间**/
    Long mLoadEndt = 0L;
    /**读取进度**/
    public final static int LOAD_PROGRESS =0;
    private SQLiteDatabase database;
    ArrayList<Javabean> CITY;
    SwipeRefreshLayout swipeRefreshView;


    /**标志读取进度结束**/
    public final static int LOAD_COMPLETE = 1;

//    Handler handler = new Handler(){
//        public void handleMessage(Message msg){
//            switch(msg.what){
//                case LOAD_PROGRESS:
//                    break;
//                case LOAD_COMPLETE:
//                    data = (List<Javabean>) msg.obj;
//                    break;
//            }
//            super.handleMessage(msg);
//        }
//    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        application = (MyApplication)getActivity().getApplication();
        view = inflater.inflate(R.layout.community_page, container, false);
        swipeRefreshView = (SwipeRefreshLayout)view.findViewById(R.id.refresh);
// 设置颜色属性的时候一定要注意是引用了资源文件还是直接设置16进制的颜色，因为都是int值容易搞混
// 设置下拉进度的背景颜色，默认就是白色的
        swipeRefreshView.setProgressBackgroundColorSchemeResource(android.R.color.white);
// 设置下拉进度的主题颜色
        swipeRefreshView.setColorSchemeResources(R.color.round_green, R.color.colorPrimary, R.color.colorPrimaryDark);

//        LoadImage();
        database = SQLiteDatabase.openOrCreateDatabase(DBManager.DB_PATH + "/" + DBManager.DB_NAME, null);
        getCity();
//        initRecyclerView();
        database.close();
      //  initData();
        initRecyclerView();
       shuaxin();
        return view;
    }


    private void getCity() {
       Javabean beauty;
        Cursor cur = database.rawQuery("SELECT * FROM community2", null);

        if (cur != null) {

            if (cur.moveToFirst()) {
                do {
                    String user = cur.getString(cur.getColumnIndex("user"));
                    String text = cur.getString(cur.getColumnIndex("community_text"));
                    String id = cur.getString(cur.getColumnIndex("head"));
                    String pic = cur.getString(cur.getColumnIndex("community_pic"));
                    Log.d("获取数据",pic);
                    Log.d("获取数据",id);
                    int pic_int=getImageResourceId(pic);
                    int id_int=getImageResourceId(id);
                    beauty = new Javabean(text, pic_int,id_int, user);
                   // beauty = new Javabean("嘿嘿真好吃", R.drawable.food3, R.drawable.xiaozhu, "佩奇");
                    Log.d("beauty的值",beauty.output());
                    application.data.add(0, beauty);
//                    taxicity.add(city);
                } while (cur.moveToNext());

            }
//            return taxicity;
        }
    }


    public void shuaxin(){
    final Handler handler = new Handler();
// 下拉时触发SwipeRefreshLayout的下拉动画，动画完毕之后就会回调这个方法
    swipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
           Log.d("再刷新哦","成功");
            // 开始刷新，设置当前为刷新状态
            //swipeRefreshLayout.setRefreshing(true);

            // 这里是主线程
            // 一些比较耗时的操作，比如联网获取数据，需要放到子线程去执行
            new Thread(){
                @Override
                public void run () {
                    super.run();

                    //同步加载网络数据
                    //加载数据 完毕后 关闭刷新状态 切回主线程
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("再刷新哦111","成功");
                            database = SQLiteDatabase.openOrCreateDatabase(DBManager.DB_PATH + "/" + DBManager.DB_NAME, null);
                            application.data.clear();
                            getCity();
//        initRecyclerView();
                            database.close();
                            initRecyclerView();
                            // 加载完数据设置为不刷新状态，将下拉进度收起来
                            swipeRefreshView.setRefreshing(false);
                        }
                    }, 100);
                }
            }.start();
        }
    });
}
    public int getImageResourceId(String name) {
        R.drawable drawables=new R.drawable();
        //默认的id
        int resId=0x7f02000b;
        try {
            //根据字符串字段名，取字段//根据资源的ID的变量名获得Field的对象,使用反射机制来实现的
            java.lang.reflect.Field field=R.drawable.class.getField(name);
            //取值
            resId=(Integer)field.get(drawables);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resId;
    }
    @Override public void onDestroy() {
        super.onDestroy();
        application.data.clear();
        application.data = null;
    }
 void initData() {

        Javabean beauty;
        beauty = new Javabean("今天的午餐今天的午餐今天的午餐今天不当回事酒吧的实打阿萨德擦拭成大事的西安市西安市西安市实的国语歌对于诶按对的hiU盾和的呀UI大UI等于IE有多少嘎达打他答复他是否他对她上大学阿方大同是飞机向对方水电费他发烧的风雅的答复他衣服也挺帅的反弹撒地方鱼头豆腐大师多喜爱是的擦似懂非懂完全的午餐今天的午餐今天的午餐今天的午餐今天的午餐今天的午餐", R.drawable.food4, R.drawable.wwb, "天线宝宝");
        application.data.add(beauty);
        beauty = new Javabean("嘿嘿真好吃", R.drawable.food3, R.drawable.xiaozhu, "佩奇");
        application.data.add(beauty);
        beauty = new Javabean("姐姐做的早餐", R.drawable.jianshen, R.drawable.xiaozhu2, "乔治");
        application.data.add(beauty);
        beauty = new Javabean("每日分享", R.drawable.food2, R.drawable.wwb, "天线宝宝");
        application.data.add(beauty);
        beauty = new Javabean("嘿嘿", R.drawable.food1, R.drawable.star, "派大星");
        application.data.add(beauty);
        beauty = new Javabean("马甲线练成计划", R.drawable.majiaxian, R.drawable.xiaozhu, "佩奇");
        application.data.add(beauty);


   }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    private void initRecyclerView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerViewLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        adapter = new BeautyAdapter(application.data, getActivity());
        //设置adapter
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BeautyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, Javabean data) {
                //此处进行监听事件的业务处理
                        Log.d("Fra","这zale");

                Log.d("点赞数",String.valueOf(data.like));
//                        Toast.makeText(getActivity(), data.getName(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), CommunityDetail.class);
                Intent intent = new Intent(getActivity(), CommunityDetail.class);
                intent.putExtra("position",recyclerView.getChildAdapterPosition(view));
                intent.putExtra("data", data);
                Log.d("Fra","这没问题");
                startActivityForResult(intent,1);

            }
        });    }
    public void onActivityResult(int requestCode, int resultCode, Intent thisdata) {
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    boolean likeFlag = thisdata.getBooleanExtra("likeFlag",false);
                    boolean collectFlag = thisdata.getBooleanExtra("collectFlag",false);
                    int position=thisdata.getIntExtra("position",-1);
                    Log.d("位置",String.valueOf(position));
                    Javabean p=application.data.get(position);
                    if(likeFlag)p.addLike();
                    if(collectFlag)p.addCollect();
                }
                break;
            default:
        }
    }

}

