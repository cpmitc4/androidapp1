package com.kentuckyspacetracker;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the activity associated with the virtual tour section of the app.
 *
 * This activity relies heavily upon altered sample code using fragments.
 */
public class NewAbout extends FragmentActivity {

    MyPageAdapter pageAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.newaboutlayout);

        //Set Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        List<Fragment> fragments = getFragments();
        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        TextView text1 = (TextView)findViewById(R.layout.myfragment_layout);
        pager.setAdapter(pageAdapter);
    }
    private List<Fragment> getFragments(){
        List<Fragment> fList = new ArrayList<Fragment>();

        //Populate Fragments before display
        //About KySpace
        fList.add(MyFragment.newInstance(getString(R.string.AboutB1),getString(R.string.AboutH1), 1));
        //About Satellites
        fList.add(MyFragment.newInstance(getString(R.string.k2about), getString(R.string.AboutH2), 2));
        fList.add(MyFragment.newInstance(getString(R.string.tlogoabout), getString(R.string.AboutH2), 3));
        fList.add(MyFragment.newInstance(getString(R.string.eagle250satabout), getString(R.string.AboutH2), 4));
        fList.add(MyFragment.newInstance(getString(R.string.cxbnabout), getString(R.string.AboutH2), 5));


        fList.add(MyFragment.newInstance(getString(R.string.AboutB3),getString(R.string.AboutH3),1));
        fList.add(MyFragment.newInstance(getString(R.string.AboutB8),getString(R.string.AboutH8),6));


        return fList;
    }

    class MyPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }
        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }




}
