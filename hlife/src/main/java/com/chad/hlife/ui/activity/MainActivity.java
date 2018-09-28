package com.chad.hlife.ui.activity;

import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.chad.hlife.R;
import com.chad.hlife.ui.base.BaseRxAppCompatActivity;
import com.chad.hlife.ui.fragment.AboutFragment;
import com.chad.hlife.ui.fragment.BooksStoreFragment;
import com.chad.hlife.ui.fragment.FilmInfoFragment;
import com.chad.hlife.ui.fragment.FilmTicketFragment;
import com.chad.hlife.ui.fragment.HistoryFragment;
import com.chad.hlife.ui.fragment.JokeFragment;
import com.chad.hlife.ui.fragment.NewsFragment;
import com.chad.hlife.ui.fragment.QueryFragment;
import com.chad.hlife.ui.fragment.SettingsFragment;
import com.chad.hlife.ui.fragment.WifiFragment;
import com.chad.hlife.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseRxAppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.layout_drawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.view_navigation)
    NavigationView mNavigationView;

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private List<Fragment> mFragments;

    private int mLastFragmentPosition;

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "initViews");
        initToolbar();
        initDrawerLayout();
        initNavigationView();
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitle("");
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initDrawerLayout() {
        LogUtil.d(TAG, "initDrawerLayout");
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState(); // 将Toolbar与Drawer同步
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
    }

    private void initNavigationView() {
        LogUtil.d(TAG, "initNavigationView");
        mNavigationView.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.item_news:
                    switchFragment(0);
                    break;
                case R.id.item_history:
                    switchFragment(1);
                    break;
                case R.id.item_joke:
                    switchFragment(2);
                    break;
                case R.id.item_film_info:
                    switchFragment(3);
                    break;
                case R.id.item_film_ticket:
                    switchFragment(4);
                    break;
                case R.id.item_books_store:
                    switchFragment(5);
                    break;
                case R.id.item_wifi:
                    switchFragment(6);
                    break;
                case R.id.item_query:
                    switchFragment(7);
                    break;
                case R.id.item_about:
                    switchFragment(8);
                    break;
                case R.id.item_settings:
                    switchFragment(9);
                    break;
                default:
                    break;
            }
            setNavigationItemChecked(menuItem);
            return false;
        });
        setNavigationItemChecked(mNavigationView.getMenu().getItem(0));
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "initData");
        initFragment();
    }

    private void initFragment() {
        LogUtil.d(TAG, "initFragment");
        mFragments = new ArrayList<>();
        mFragments.add(new NewsFragment());
        mFragments.add(new HistoryFragment());
        mFragments.add(new JokeFragment());
        mFragments.add(new FilmInfoFragment());
        mFragments.add(new FilmTicketFragment());
        mFragments.add(new BooksStoreFragment());
        mFragments.add(new WifiFragment());
        mFragments.add(new QueryFragment());
        mFragments.add(new AboutFragment());
        mFragments.add(new SettingsFragment());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_fragment, mFragments.get(0)).commit();
    }

    private void setNavigationItemChecked(MenuItem menuItem) {
        LogUtil.d(TAG, "setNavigationItemChecked : menuItem = "
                + (menuItem == null ? "Null" : "Not Null"));
        if (menuItem == null) {
            return;
        }
        menuItem.setCheckable(true);
        menuItem.setChecked(true);
        mToolbar.setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();
    }

    public void switchFragment(int position) {
        LogUtil.d(TAG, "switchFragment : mLastFragmentPosition = " + mLastFragmentPosition
                + " , position = " + position);
        if (mLastFragmentPosition == position) {
            return;
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!mFragments.get(position).isAdded()) {
            fragmentTransaction.hide(mFragments.get(mLastFragmentPosition))
                    .add(R.id.content_fragment, mFragments.get(position)).commit();
        } else {
            fragmentTransaction.hide(mFragments.get(mLastFragmentPosition))
                    .show(mFragments.get(position)).commit();
        }
        mLastFragmentPosition = position;
    }
}
