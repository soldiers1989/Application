package com.chad.read.ui.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.chad.read.R;
import com.chad.read.ui.base.BaseAppCompatActivity;
import com.chad.read.ui.fragment.ColumnFragment;
import com.chad.read.ui.fragment.FindFragment;
import com.chad.read.ui.fragment.MineFragment;
import com.chad.read.ui.fragment.SelectionFragment;
import com.chad.read.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * 主Activity，管理Fragment
 */
public class MainActivity extends BaseAppCompatActivity implements AHBottomNavigation.OnTabSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation mAHBottomNavigation;

    private List<Fragment> mFragments = null;
    private int mCurrentItemPosition = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        initToolbar();
        initAHBottomNavigation();
        LogUtil.d(TAG, "initViews");
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new SelectionFragment());
        mFragments.add(new FindFragment());
        mFragments.add(new ColumnFragment());
        mFragments.add(new MineFragment());

        getFragmentManager().beginTransaction()
                .add(R.id.content_fragment, mFragments.get(0))
                .add(R.id.content_fragment, mFragments.get(1))
                .add(R.id.content_fragment, mFragments.get(2))
                .add(R.id.content_fragment, mFragments.get(3))
                .show(mFragments.get(0)).commit();

        LogUtil.d(TAG, "initData");
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        LogUtil.d(TAG, "initToolbar");
    }

    private void initAHBottomNavigation() {
        AHBottomNavigationItem selectionItem = new AHBottomNavigationItem(
                getString(R.string.main_navigation_selection), R.drawable.ic_main_navigation_selection);
        AHBottomNavigationItem findItem = new AHBottomNavigationItem(
                getString(R.string.main_navigation_find), R.drawable.ic_main_navigation_find);
        AHBottomNavigationItem columnItem = new AHBottomNavigationItem(
                getString(R.string.main_navigation_column), R.drawable.ic_main_navigation_column);
        AHBottomNavigationItem mineItem = new AHBottomNavigationItem(
                getString(R.string.main_navigation_mine), R.drawable.ic_main_navigation_mine);

        mAHBottomNavigation.addItem(selectionItem);
        mAHBottomNavigation.addItem(findItem);
        mAHBottomNavigation.addItem(columnItem);
        mAHBottomNavigation.addItem(mineItem);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mAHBottomNavigation.setDefaultBackgroundColor(getColor(R.color.colorBottomNavigationBackground));
        } else {
            mAHBottomNavigation.setDefaultBackgroundColor(getResources()
                    .getColor(R.color.colorBottomNavigationBackground));
        }
        mAHBottomNavigation.setAccentColor(R.color.colorBottomNavigationAccent);
        mAHBottomNavigation.setInactiveColor(R.color.colorBottomNavigationInactive);
        mAHBottomNavigation.setBehaviorTranslationEnabled(true);
        mAHBottomNavigation.setCurrentItem(0);
        mAHBottomNavigation.setOnTabSelectedListener(this);
        LogUtil.d(TAG, "initAHBottomNavigation");
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        LogUtil.d(TAG, "onTabSelected : position = " + position
                + " , mCurrentItemPosition = " + mCurrentItemPosition);
        if (position == mCurrentItemPosition) {
            return false;
        }
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.hide(mFragments.get(mCurrentItemPosition));
        if (!mFragments.get(position).isAdded()) {
            fragmentTransaction.add(R.id.content, mFragments.get(position));
        }
        fragmentTransaction.show(mFragments.get(position)).commit();
        mCurrentItemPosition = position;
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onDestroy() {
        if (mFragments != null) {
            mFragments.clear();
            mFragments = null;
        }
        mCurrentItemPosition = 0;
        LogUtil.d(TAG, "onDestroy");
        super.onDestroy();
    }
}
