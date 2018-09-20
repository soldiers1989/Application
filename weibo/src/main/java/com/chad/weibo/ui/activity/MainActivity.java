package com.chad.weibo.ui.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.weibo.R;
import com.chad.weibo.entity.User;
import com.chad.weibo.eventbus.EventMessage;
import com.chad.weibo.eventbus.EventType;
import com.chad.weibo.glide.CustomGlideModule;
import com.chad.weibo.glide.GlideApp;
import com.chad.weibo.helper.ActivityHelper;
import com.chad.weibo.helper.WeiBoAuthHelper;
import com.chad.weibo.mvp.presenter.main.MainPresenter;
import com.chad.weibo.mvp.view.IMainView;
import com.chad.weibo.ui.base.BaseMvpAppCompatActivity;
import com.chad.weibo.util.LogUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseMvpAppCompatActivity<IMainView, MainPresenter>
        implements IMainView {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.layout_drawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.view_navigation)
    NavigationView mNavigationView;
    AppCompatImageView mCoverImagePhone;
    SimpleDraweeView mUserAvatar;
    AppCompatTextView mUserName;

    private ActionBarDrawerToggle mActionBarDrawerToggle = null;
    private User mUser = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        initToolbar();
        initDrawer();
        initNavigation();
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        // 设置返回键可用
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initDrawer() {
        LogUtil.d(TAG, "initDrawer");
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close);
        mActionBarDrawerToggle.syncState(); // 将Toolbar与Drawer同步
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
    }

    private void initNavigation() {
        LogUtil.d(TAG, "initNavigation");
        MenuItem defaultMenuItem = mNavigationView.getMenu().getItem(0);
        setNavigationItemChecked(defaultMenuItem);

        mNavigationView.setNavigationItemSelectedListener(menuItem -> {
            // TODO: 2018/9/17 切换页面等逻辑
            setNavigationItemChecked(menuItem);
            return false;
        });

        View view = mNavigationView.getHeaderView(0);
        mCoverImagePhone = view.findViewById(R.id.cover_image_phone);
        mUserAvatar = view.findViewById(R.id.user_avatar);
        mUserName = view.findViewById(R.id.user_name);

        mUserAvatar.setOnClickListener(v -> ActivityHelper.startUserActivity(this));
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        getUser();
    }

    private void getUser() {
        LogUtil.d(TAG, "getUser");
        Oauth2AccessToken accessToken = WeiBoAuthHelper.getInstance().getOauth2AccessToken();
        if (accessToken != null) {
            String access_token = accessToken.getToken();
            long uid = Long.parseLong(accessToken.getUid());
            presenter.getUser(bindToLifecycle(), access_token, uid);
        }
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    private void setNavigationItemChecked(MenuItem menuItem) {
        LogUtil.d(TAG, "setNavigationItemChecked : menuItem = " + menuItem);
        if (menuItem == null) {
            return;
        }
        menuItem.setCheckable(true);
        menuItem.setChecked(true);
        mToolbar.setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();
    }

    @Override
    protected void onStop() {
        LogUtil.d(TAG, "onPause");
        EventBus.getDefault().post(new EventMessage(EventType.TYPE_USER, mUser));
        super.onStop();
    }

    @Override
    public void onUser(User user) {
        LogUtil.d(TAG, "onUser : user = " + (user == null ? null : "Not Null"));
        if (user == null) {
            return;
        }
        mUser = user;
        CustomGlideModule.load(this, user.getCover_image_phone(), mCoverImagePhone);
        mUserAvatar.setImageURI(user.getAvatar_large());
        mUserName.setText(user.getScreen_name());
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError : message = " + ((Throwable) object).getMessage());
    }
}
