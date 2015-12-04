package com.scxh.android1503.fragment.tab;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.scxh.android1503.R;

public class TabMainActivity extends FragmentActivity {
    private RadioGroup mRadioGroup;
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_main_layout);

        mRadioGroup = (RadioGroup) findViewById(R.id.tab_radio_group);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(),android.R.id.tabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("首页"),TabOneFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("搜索"),TabTwoFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("设置"),TabOneFragment.class, null);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab_radio_one:
                        mTabHost.setCurrentTabByTag("tab1");
                        break;
                    case R.id.tab_radio_two:
                        mTabHost.setCurrentTabByTag("tab2");
                        break;
                    case R.id.tab_radio_three:
                        mTabHost.setCurrentTabByTag("tab3");
                        break;
                }

            }
        });
        ((RadioButton) mRadioGroup.getChildAt(0)).toggle(); //默认选中第一项
    }
}
