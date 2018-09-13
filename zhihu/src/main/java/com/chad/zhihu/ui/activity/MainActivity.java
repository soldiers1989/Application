package com.chad.zhihu.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.chad.zhihu.R;
import com.chad.zhihu.ui.fragment.ThemesFragment;
import com.chad.zhihu.util.ColorUtil;
import com.chad.zhihu.util.StringUtil;
import com.chad.zhihu.ui.base.BaseRxAppCompatActivity;
import com.chad.zhihu.ui.fragment.SectionsFragment;
import com.chad.zhihu.ui.fragment.HomeFragment;
import com.chad.zhihu.ui.fragment.MineFragment;
import com.chad.zhihu.util.LogUtil;
import com.chad.zhihu.util.SystemStatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseRxAppCompatActivity implements AHBottomNavigation.OnTabSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigation_bottom)
    AHBottomNavigation mAhBottomNavigation;

    private List<Fragment> mFragmentList = null;

    private int mSelectedPosition = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mian;
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        SystemStatusBarUtil.setStatusBarColor(this,
                ColorUtil.findRgbById(this, R.color.colorStatusBar));
        initToolbar();
        initBottomNavigation();
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        initFragmentList();
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    private void initBottomNavigation() {
        LogUtil.d(TAG, "initBottomNavigation");
        AHBottomNavigationItem ahBottomNavigationItemHome = new AHBottomNavigationItem(
                StringUtil.findStringById(this, R.string.main_navigation_home),
                R.drawable.ic_navigation_home_selected);
        AHBottomNavigationItem ahBottomNavigationItemDaily = new AHBottomNavigationItem(
                StringUtil.findStringById(this, R.string.main_navigation_themes),
                R.drawable.ic_navigation_daily_selected);
        AHBottomNavigationItem ahBottomNavigationItemColumn = new AHBottomNavigationItem(
                StringUtil.findStringById(this, R.string.main_navigation_sections),
                R.drawable.ic_navigation_column_selected);
        AHBottomNavigationItem ahBottomNavigationItemMine = new AHBottomNavigationItem(
                StringUtil.findStringById(this, R.string.main_navigation_mine),
                R.drawable.ic_navigation_mine_selected);

        mAhBottomNavigation.addItem(ahBottomNavigationItemHome);
        mAhBottomNavigation.addItem(ahBottomNavigationItemDaily);
        mAhBottomNavigation.addItem(ahBottomNavigationItemColumn);
        mAhBottomNavigation.addItem(ahBottomNavigationItemMine);

        mAhBottomNavigation.setColored(false);
        mAhBottomNavigation.setForceTint(false);
        mAhBottomNavigation.setBehaviorTranslationEnabled(true);
        // 设置Tab上title状态
        mAhBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        // 设置Tab选中颜色
        mAhBottomNavigation.setAccentColor(ColorUtil.findRgbById(getApplicationContext(),
                R.color.colorBottomTabAccent));
        // 设置Tab未选中颜色
        mAhBottomNavigation.setInactiveColor(ColorUtil.findRgbById(getApplicationContext(),
                R.color.colorBottomTabInactive));
        // 设置默认的背景颜色
        mAhBottomNavigation.setDefaultBackgroundColor(ColorUtil.findRgbById(getApplicationContext(),
                R.color.colorBottomTabBackground));
        // 设置当前选中Tab
        mAhBottomNavigation.setCurrentItem(0);
        // 设置Tab选中监听器
        mAhBottomNavigation.setOnTabSelectedListener(this);
    }

    private void initFragmentList() {
        LogUtil.d(TAG, "initFragmentList");
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new ThemesFragment());
        mFragmentList.add(new SectionsFragment());
        mFragmentList.add(new MineFragment());

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_fragment, mFragmentList.get(0));
        fragmentTransaction.commit();
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        LogUtil.d(TAG, "onTabSelected : position = " + position
                + " , mSelectedPosition = " + mSelectedPosition + " , wasSelected = " + wasSelected);
        if (mFragmentList == null || mFragmentList.size() < 4 || mSelectedPosition == position) {
            return false;
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(mFragmentList.get(mSelectedPosition));
        if (!mFragmentList.get(position).isAdded()) {
            fragmentTransaction.add(R.id.content_fragment, mFragmentList.get(position));
        }
        fragmentTransaction.show(mFragmentList.get(position));
        fragmentTransaction.commit();
        mSelectedPosition = position;
        return true;
    }

    @Override
    protected void onDestroy() {
        LogUtil.d(TAG, "onDestroy");
        if (mFragmentList != null) {
            mFragmentList.clear();
            mFragmentList = null;
        }
        super.onDestroy();
    }
}
