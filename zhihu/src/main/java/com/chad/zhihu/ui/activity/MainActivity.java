package com.chad.zhihu.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.chad.zhihu.R;
import com.chad.zhihu.util.ColorUtil;
import com.chad.zhihu.util.StringUtil;
import com.chad.zhihu.ui.base.BaseRxAppCompatActivity;
import com.chad.zhihu.ui.fragment.ColumnFragment;
import com.chad.zhihu.ui.fragment.DailyFragment;
import com.chad.zhihu.ui.fragment.HomeFragment;
import com.chad.zhihu.ui.fragment.MineFragment;
import com.chad.zhihu.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseRxAppCompatActivity implements AHBottomNavigation.OnTabSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigation_bottom)
    AHBottomNavigation mAhBottomNavigation;

    private List<Fragment> fragmentList = null;

    private int selectedPosition = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mian;
    }

    @Override
    protected void initViews() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        initBottomNavigation();
        LogUtil.d(TAG, "initViews");
    }

    @Override
    protected void initData() {
        initFragmentList();
        LogUtil.d(TAG, "initData");
    }

    private void initBottomNavigation() {
        AHBottomNavigationItem ahBottomNavigationItemHome = new AHBottomNavigationItem(
                StringUtil.findStringById(getApplicationContext(), R.string.main_navigation_home),
                R.drawable.ic_navigation_home_selected);
        AHBottomNavigationItem ahBottomNavigationItemDaily = new AHBottomNavigationItem(
                StringUtil.findStringById(getApplicationContext(), R.string.main_navigation_daily),
                R.drawable.ic_navigation_daily_selected);
        AHBottomNavigationItem ahBottomNavigationItemColumn = new AHBottomNavigationItem(
                StringUtil.findStringById(getApplicationContext(), R.string.main_navigation_column),
                R.drawable.ic_navigation_column_selected);
        AHBottomNavigationItem ahBottomNavigationItemMine = new AHBottomNavigationItem(
                StringUtil.findStringById(getApplicationContext(), R.string.main_navigation_mine),
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
                R.color.colorNavigationAccent));
        // 设置Tab未选中颜色
        mAhBottomNavigation.setInactiveColor(ColorUtil.findRgbById(getApplicationContext(),
                R.color.colorNavigationInactive));
        // 设置默认的背景颜色
        mAhBottomNavigation.setDefaultBackgroundColor(ColorUtil.findRgbById(getApplicationContext(),
                R.color.colorNavigationBackground));
        // 设置当前选中Tab
        mAhBottomNavigation.setCurrentItem(0);
        // 设置Tab选中监听器
        mAhBottomNavigation.setOnTabSelectedListener(this);
        LogUtil.d(TAG, "initBottomNavigation");
    }

    private void initFragmentList() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new DailyFragment());
        fragmentList.add(new ColumnFragment());
        fragmentList.add(new MineFragment());

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_fragment, fragmentList.get(0));
        fragmentTransaction.commit();
        LogUtil.d(TAG, "initFragmentList");
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        LogUtil.d(TAG, "onTabSelected : position = " + position
                + " , selectedPosition = " + selectedPosition + " , wasSelected = " + wasSelected);
        if (fragmentList == null || fragmentList.size() < 4 || selectedPosition == position) {
            return false;
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(fragmentList.get(selectedPosition));
        if (!fragmentList.get(position).isAdded()) {
            fragmentTransaction.add(R.id.content_fragment, fragmentList.get(position));
        }
        fragmentTransaction.show(fragmentList.get(position));
        fragmentTransaction.commit();
        selectedPosition = position;
        return true;
    }

    @Override
    protected void onDestroy() {
        if (fragmentList != null) {
            fragmentList.clear();
            fragmentList = null;
        }
        LogUtil.d(TAG, "onDestroy");
        super.onDestroy();
    }
}
