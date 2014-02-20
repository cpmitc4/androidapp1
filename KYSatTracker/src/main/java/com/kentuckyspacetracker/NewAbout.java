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
        pager.setAdapter(pageAdapter);
    }
    private List<Fragment> getFragments(){
        List<Fragment> fList = new ArrayList<Fragment>();

        //Populate Fragments before display
        fList.add(MyFragment.newInstance(getString(R.string.AboutStartH),getString(R.string.VT)));
        fList.add(MyFragment.newInstance(getString(R.string.AboutB1),getString(R.string.AboutH1)));
        fList.add(MyFragment.newInstance(getString(R.string.AboutB2),getString(R.string.AboutH2)));
        fList.add(MyFragment.newInstance(getString(R.string.AboutB3),getString(R.string.AboutH3)));
        fList.add(MyFragment.newInstance(getString(R.string.AboutB4),getString(R.string.AboutH4)));
        fList.add(MyFragment.newInstance(getString(R.string.AboutB5),getString(R.string.AboutH4)));
        fList.add(MyFragment.newInstance(getString(R.string.AboutB6),getString(R.string.AboutH6)));
        fList.add(MyFragment.newInstance(getString(R.string.AboutB7),getString(R.string.AboutH6)));
        fList.add(MyFragment.newInstance(getString(R.string.AboutB8),getString(R.string.AboutH8)));
        fList.add(MyFragment.newInstance(getString(R.string.AboutB10),getString(R.string.AboutH10)));
        fList.add(MyFragment.newInstance(getString(R.string.ENDB),getString(R.string.END)));

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
