package com.example.a84121.bao_diet;

/**
 * Created by 84121 on 2019/11/26.
 */


        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentStatePagerAdapter;
        import android.util.Log;

        import java.util.List;

public class MyTabFragmentAdapter extends FragmentStatePagerAdapter {

    public static final String TAG = "MyTabFragmentAdapter";

    private String[] titleArray;
    private List<Fragment> listFragments;

    public MyTabFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public int getCount() {
        Log.d(TAG,"listFragments.size() = " + listFragments.size());
        return listFragments.size();
    }

    public void addFragment(Fragment fragment){
        this.listFragments.add(fragment);
    }

    public void setFragments(List<Fragment> fragments){
        this.listFragments = fragments;
    }

    public void setTitlesArr (String[] titlesArr) {
        this.titleArray = titlesArr;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleArray[position];
    }
}
