package com.chad.hlife.ui.activity;

import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.AppSettings;
import com.chad.hlife.entity.weibo.WeiBoUserInfo;
import com.chad.hlife.glide.CustomGlideModule;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.main.MainPresenter;
import com.chad.hlife.mvp.view.IMainView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.ui.fragment.HistoryFragment;
import com.chad.hlife.ui.fragment.NewsFragment;
import com.chad.hlife.ui.fragment.SettingsFragment;
import com.chad.hlife.ui.fragment.OilPriceFragment;
import com.chad.hlife.ui.fragment.RecipeFragment;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseMvpAppCompatActivity<IMainView, MainPresenter>
        implements IMainView {

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
    protected MainPresenter onGetPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "initViews");
        StatusBarUtil.setStatusBarColor(this, AppConstant.COLOR_STATUS_BAR_BLUE);
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
                    setNavigationItemChecked(menuItem);
                    break;
                case R.id.item_history:
                    switchFragment(1);
                    setNavigationItemChecked(menuItem);
                    break;
                case R.id.item_oil_prices:
                    switchFragment(2);
                    setNavigationItemChecked(menuItem);
                    break;
                case R.id.item_recipe:
                    switchFragment(3);
                    setNavigationItemChecked(menuItem);
                    break;
                case R.id.item_zhihu:
                    ActivityHelper.startZhiHuActivity(this);
                    mDrawerLayout.closeDrawers();
                    break;
                case R.id.item_tao_ticket:
                    ActivityHelper.startTaoTicketActivity(this);
                    mDrawerLayout.closeDrawers();
                    break;
                case R.id.item_settings:
                    switchFragment(4);
                    setNavigationItemChecked(menuItem);
                    break;
                default:
                    break;
            }
            return false;
        });
        setNavigationItemChecked(mNavigationView.getMenu().getItem(0));
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "initData");
        initFragment();
        getUserInfo();
    }

    private void initFragment() {
        LogUtil.d(TAG, "initFragment");
        mFragments = new ArrayList<>();
        mFragments.add(new NewsFragment());
        mFragments.add(new HistoryFragment());
        mFragments.add(new OilPriceFragment());
        mFragments.add(new RecipeFragment());
        mFragments.add(new SettingsFragment());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_fragment, mFragments.get(0)).commit();
    }

    public void getUserInfo() {
        LogUtil.d(TAG, "getUserInfo");
        if (AppSettings.getInstance().getLoginStatus()) {
            Oauth2AccessToken accessToken = presenter.getOauth2AccessToken();
            if (accessToken != null) {
                String access_token = accessToken.getToken();
                long uid = Long.parseLong(accessToken.getUid());
                presenter.getWeiBoUserInfo(bindToLifecycle(), access_token, uid);
            }
        }
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

    @Override
    public void onWeiBoUserInfo(WeiBoUserInfo weiBoUserInfo) {
        LogUtil.d(TAG, "onWeiBoUserInfo : weiBoUserInfo = " + weiBoUserInfo);
        if (weiBoUserInfo == null) {
            return;
        }
        View view = mNavigationView.getHeaderView(0);
        AppCompatImageView userWall = view.findViewById(R.id.image_user_wall);
        SimpleDraweeView userAvatar = view.findViewById(R.id.drawee_user_avatar);
        AppCompatTextView userName = view.findViewById(R.id.text_user_name);
        CustomGlideModule.loadCenterCrop(this, weiBoUserInfo.getCover_image_phone(), userWall);
        userAvatar.setImageURI(weiBoUserInfo.getAvatar_large());
        userName.setText(weiBoUserInfo.getName());
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
