package com.example.a84121.bao_diet;

/**
 * Created by 84121 on 2019/11/26.
 */


        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.design.widget.TabLayout;
        import android.support.v4.app.Fragment;
        import android.support.v4.view.ViewPager;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;


        import java.util.ArrayList;
        import java.util.List;

public class MyTabFragment extends Fragment {
    private View viewContent;
    private TabLayout my_tablayout;
    private ViewPager my_viewpager;
    public static MyTabFragment myTabFragment;
    Button add_friend,fabu;
    private int mode = TabLayout.MODE_FIXED;

    public MyTabFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.fragment_tab,container,false);
        initConentView(viewContent);
        initData();
        add_friend=(Button)viewContent.findViewById(R.id.btn_add_friend);
        fabu=(Button)viewContent.findViewById(R.id.btn_fabu);
        click();
        return viewContent;
    }
    public void click() {
        add_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
               Intent mIntent=new Intent(getActivity(), Friends.class);//没有任何参数（意图），只是用来传递数据
                //跳转加好友页面
                startActivity(mIntent);
            }
        });


        fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
//        跳转发布页面
                Intent mIntent=new Intent(getActivity(), Fabu.class);//没有任何参数（意图），只是用来传递数据
                startActivity(mIntent);

            }
        });
    }
    public void initConentView(View viewContent) {
        this.my_tablayout = (TabLayout) viewContent.findViewById(R.id.my_tablayout);
        this.my_viewpager = (ViewPager) viewContent.findViewById(R.id.my_viewpager);
    }

    public void initData() {
        //创建一个viewpager的adapter
        MyTabFragmentAdapter adapter = new MyTabFragmentAdapter(getFragmentManager());
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new Fragment3());
        fragments.add(new Fragment5());
        String[] titlesArr = {"发现", "好友"};
        adapter.setTitlesArr(titlesArr);
        adapter.setFragments(fragments);
        this.my_viewpager.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来
        this.my_tablayout.setupWithViewPager(this.my_viewpager);
        my_tablayout.setTabMode(mode);
    }

}
