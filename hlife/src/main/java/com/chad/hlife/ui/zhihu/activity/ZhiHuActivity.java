package com.chad.hlife.ui.zhihu.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.chad.hlife.R;
import com.chad.hlife.ui.base.BaseRxAppCompatActivity;
import com.chad.hlife.util.LogUtil;

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
        initToolbar();
        initBottomNavigation();
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitle(R.string.zhihu);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_back_light);
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
        // 设置Tab选中颜色
        mAhBottomNavigation.setAccentColor(getResources().getColor(R.color.colorPrimary));
        // 设置Tab未选中颜色
        mAhBottomNavigation.setInactiveColor(getResources().getColor(R.color.colorText));
        // 设置默认的背景颜色
        mAhBottomNavigation.setDefaultBackgroundColor(Color.WHITE);
        // 设置当前选中Tab
        mAhBottomNavigation.setCurrentItem(0);
        // 设置Tab选中监听器
        mAhBottomNavigation.setOnTabSelectedListener(this);
    }

    @Override
    protected void onInitData() {

    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        LogUtil.d(TAG, "onTabSelected : position = " + position
                + " , mSelectedPosition = " + mSelectedPosition + " , wasSelected = " + wasSelected);
        if (mFragments == null || mFragments.size() < 3 || mSelectedPosition == position) {
            return false;
        }
        return false;
    }
}
