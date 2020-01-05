package com.example.a84121.bao_diet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 84121 on 2019/11/26.
 */

public class Fragment5 extends Fragment {
//    private TextView textView;
//    //    @Nullable
//    @Override
////    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view=inflater.inflate(R.layout.me_page,container,false);
//        return view;
//    }
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        textView=(TextView)getActivity().findViewById(R.id.textView3);
//    }
private View view;
    private RecyclerView recyclerView;
    MyApplication application;
    private BeautyAdapter adapter;
    private List<Javabean> friend =new ArrayList<>();
    private ArrayList<Integer> tag = new ArrayList<>();
    private int flag=0;
    StaggeredGridLayoutManager recyclerViewLayoutManager;
    /**加载图片前的时间**/
    Long mLoadStart = 0L;
    /**加载图片完成的时间**/
    Long mLoadEndt = 0L;
    /**读取进度**/
    public final static int LOAD_PROGRESS =0;


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
        view = inflater.inflate(R.layout.community_page2, container, false);
        Log.d("来好友也了","scsdfcsdcsc");
//        LoadImage();
        return view;
    }
    @Override public void onDestroy() {
        super.onDestroy();
        application.data.clear();
        application.data = null;
    }

    private void initData() {
        Log.d("data大小",String.valueOf(application.data.size()));
        for(int i=0;i< application.data.size();i++){
            Javabean p= application.data.get(i);
            if(p.getUser().equals("佩奇")){
                Log.d("好友",p.output());
                tag.add(i);
                friend.add(p);}
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initRecyclerView();

    }


    private void initRecyclerView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);
        recyclerViewLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        adapter = new BeautyAdapter(friend, getActivity());
        //设置adapter
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BeautyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, Javabean data) {
                //此处进行监听事件的业务处理
                Log.d("Fra","这zale");

                Log.d("点赞数",String.valueOf(data.like));
//                        Toast.makeText(getActivity(), data.getName(), Toast.LENGTH_SHORT).show();
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
                    Javabean p= application.data.get(tag.get(position));
                    if(likeFlag)p.addLike();
                    if(collectFlag)p.addCollect();
                }
                break;
            default:
        }
    }
}
