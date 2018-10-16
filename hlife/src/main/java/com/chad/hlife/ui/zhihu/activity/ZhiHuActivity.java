package com.chad.hlife.ui.zhihu.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.ui.base.BaseRxAppCompatActivity;
import com.chad.hlife.ui.zhihu.fragment.HomeFragment;
import com.chad.hlife.ui.zhihu.fragment.SectionsFragment;
import com.chad.hlife.ui.zhihu.fragment.ThemesFragment;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ZhiHuActivity extends BaseRxAppCompatActivity implements AHBottomNavigation.OnTabSelectedListener {

    public static final String TAG = ZhiHuActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigation_bottom)
    AHBottomNavigation mAhBottomNavigation;

    private List<Fragment> mFragments;

    private int mSelectedPosition = 0;

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_zhihu;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(AppConstant.COLOR_STATUS_BAR_RED));
        initToolbar();
        initBottomNavigation();
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitle(R.string.zhihu);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_close_light);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void initBottomNavigation() {
        LogUtil.d(TAG, "initBottomNavigation");
        AHBottomNavigationItem homeAhBottomNavigationItem = new AHBottomNavigationItem(
                getString(R.string.home),
                R.drawable.ic_navigation_home);
        AHBottomNavigationItem themesAhBottomNavigationItem = new AHBottomNavigationItem(
                getString(R.string.themes),
                R.drawable.ic_navigation_themes);
        AHBottomNavigationItem sectionsAhBottomNavigationItem = new AHBottomNavigationItem(
                getString(R.string.sections),
                R.drawable.ic_navigation_sections);

        mAhBottomNavigation.addItem(homeAhBottomNavigationItem);
        mAhBottomNavigation.addItem(themesAhBottomNavigationItem);
        mAhBottomNavigation.addItem(sectionsAhBottomNavigationItem);

        mAhBottomNavigation.setColored(false);
        mAhBottomNavigation.setForceTint(false);
        mAhBottomNavigation.setBehaviorTranslationEnabled(true);
        // 设置Tab上title状态
        mAhBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        // 设置Tab选中颜色e
        mAhBottomNavigation.setAccentColor(getResources().getColor(R.color.colorPrimaryRed));
        // 设置Tab未选中颜色
        mAhBottomNavigation.setInactiveColor(Color.BLACK);
        // 设置默认的背景颜色
        mAhBottomNavigation.setDefaultBackgroundColor(Color.WHITE);
        // 设置当前选中Tab
        mAhBottomNavigation.setCurrentItem(0);
        // 设置Tab选中监听器
        mAhBottomNavigation.setOnTabSelectedListener(this);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        initFragment();
    }

    private void initFragment() {
        LogUtil.d(TAG, "initFragment");
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new ThemesFragment());
        mFragments.add(new SectionsFragment());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_fragment, mFragments.get(0)).commit();
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        LogUtil.d(TAG, "onTabSelected : position = " + position
                + " , mSelectedPosition = " + mSelectedPosition + " , wasSelected = " + wasSelected);
        if (mFragments == null || mFragments.size() < 3 || mSelectedPosition == position) {
            return false;
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!mFragments.get(position).isAdded()) {
            fragmentTransaction.hide(mFragments.get(mSelectedPosition))
                    .add(R.id.content_fragment, mFragments.get(position)).commit();
        } else {
            fragmentTransaction.hide(mFragments.get(mSelectedPosition))
                    .show(mFragments.get(position)).commit();
        }
        mSelectedPosition = position;
        return true;
    }
}
