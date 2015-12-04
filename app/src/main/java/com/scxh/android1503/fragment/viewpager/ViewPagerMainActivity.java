package com.scxh.android1503.fragment.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.scxh.android1503.R;
import com.scxh.android1503.fragment.tab.TabOneFragment;
import com.scxh.android1503.fragment.tab.TabThreeFragment;
import com.scxh.android1503.fragment.tab.TabTwoFragment;

import java.util.ArrayList;

public class ViewPagerMainActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private RadioButton mOneRadioBtn,mTwoRadioBtn,mThreeRadioBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_main_layout);
        mViewPager = (ViewPager) findViewById(R.id.fragment_viewpager);
        mRadioGroup = (RadioGroup) findViewById(R.id.tab_radio_group);
        mOneRadioBtn = (RadioButton) findViewById(R.id.tab_radio_one);
        mTwoRadioBtn = (RadioButton) findViewById(R.id.tab_radio_two);
        mThreeRadioBtn = (RadioButton) findViewById(R.id.tab_radio_three);

        ArrayList<Fragment> data = new ArrayList<Fragment>();
        ininFragmentData(data);

        MyFragmentPagerViewAdatper adapter = new MyFragmentPagerViewAdatper(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        adapter.setDataFragmentList(data);


        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab_radio_one:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.tab_radio_two:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.tab_radio_three:
                        mViewPager.setCurrentItem(2);
                        break;
                }

            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mOneRadioBtn.setChecked(true);
                        break;
                    case 1:
                        mTwoRadioBtn.setChecked(true);
                        break;
                    case 2:

                        mThreeRadioBtn.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public void ininFragmentData(ArrayList<Fragment> data) {
        data.add(TabOneFragment.newInstance());
        data.add(new TabTwoFragment());
        data.add(TabThreeFragment.newInstance());
    }

    public class MyFragmentPagerViewAdatper extends FragmentPagerAdapter {
        ArrayList<Fragment> dataFragmentList = new ArrayList<Fragment>();

        public void setDataFragmentList(ArrayList<Fragment> data) {
            dataFragmentList = data;
            notifyDataSetChanged();
        }

        public MyFragmentPagerViewAdatper(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return dataFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return dataFragmentList.size();
        }
    }

}
