package com.golive.xess.merchant.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangChun .
 * on 2017/3/7.
 * 主界面
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.tl_title)
    CommonTabLayout tlTitle;
    @BindView(R.id.content_vp)
    ViewPager contentVp;
    private Context mContext;
    private String[] mTitles;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        initView();
    }

    private void initView() {
        mTitles = getResources().getStringArray(R.array.main_arr);

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i]));
        }

        tlTitle.setTabData(mTabEntities);
        contentVp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tlTitle.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                tlTitle.setCurrentTab(position);
                contentVp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        contentVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tlTitle.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        contentVp.setCurrentItem(0);

    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public Fragment getItem(int position) {//只会在新建 Fragment 时执行一次
            Fragment f = null;
            if (0 == position)
                f = new PersonInfoFragment();
            else if (1 == position)
                f = new BetHistoryFragment();
            else if (2 == position)
                f = new WalletFragment();
            else if (3 == position)
                f = new SetFragment();
            return f;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;//FragmentStatePagerAdapter 在会在因 POSITION_NONE
            // 触发调用的 destroyItem() 中
            // 真正的释放资源，重新建立一个新的 Fragment
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return super.isViewFromObject(view, object);
        }
    }

    class TabEntity implements CustomTabEntity {
        String titles;

        public TabEntity(String titles) {
            this.titles = titles;
        }

        @Override
        public String getTabTitle() {
            return titles;
        }

        @Override
        public int getTabSelectedIcon() {
            return 0;
        }

        @Override
        public int getTabUnselectedIcon() {
            return 0;
        }
    }
}
