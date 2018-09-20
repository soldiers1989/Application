package com.chad.weibo.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.weibo.R;
import com.chad.weibo.entity.User;
import com.chad.weibo.eventbus.EventMessage;
import com.chad.weibo.eventbus.EventType;
import com.chad.weibo.glide.CustomGlideModule;
import com.chad.weibo.glide.GlideApp;
import com.chad.weibo.ui.adapter.UserTabAdapter;
import com.chad.weibo.ui.base.BaseRxAppCompatActivity;
import com.chad.weibo.ui.fragment.UserSheetFragment;
import com.chad.weibo.ui.fragment.UserStatusFragment;
import com.chad.weibo.util.LogUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class UserActivity extends BaseRxAppCompatActivity {

    private static final String TAG = UserActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.cover_image_phone)
    AppCompatImageView mCoverImagePhone;
    @BindView(R.id.user_avatar)
    SimpleDraweeView mUserAvatar;
    @BindView(R.id.user_name)
    AppCompatTextView mUserName;
    @BindView(R.id.count)
    AppCompatTextView mCount;
    @BindView(R.id.description)
    AppCompatTextView mDescription;
    @BindView(R.id.layout_tab)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        initToolbar();
        initUserTab();
        // https://www.jianshu.com/p/5f8f1b684631?mType=Group
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        setSupportActionBar(mToolbar);
    }

    private void initUserTab() {
        LogUtil.d(TAG, "initUserTab");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new UserSheetFragment());
        fragments.add(new UserStatusFragment());
        List<String> titles = new ArrayList<>();
        titles.add(getString(R.string.sheet));
        titles.add(getString(R.string.status));
        UserTabAdapter userTabAdapter = new UserTabAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(userTabAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData;");
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUser(EventMessage eventMessage) {
        LogUtil.d(TAG, "onUser : eventMessage = " + (eventMessage == null ? null : "Not Null"));
        if (eventMessage == null) {
            return;
        }
        switch (eventMessage.getType()) {
            case EventType.TYPE_USER:
                handleUser((User) eventMessage.getObject());
                break;
            default:
                break;
        }
    }

    private void handleUser(User user) {
        LogUtil.d(TAG, "handleUser : user = " + (user == null ? null : "Not Null"));
        if (user == null) {
            return;
        }
        CustomGlideModule.load(this, user.getCover_image_phone(), mCoverImagePhone);
        mUserAvatar.setImageURI(user.getAvatar_large());
        mUserName.setText(user.getScreen_name());
        mCount.setText(getString(R.string.friends) + "\0" + user.getFriends_count()
                + "\0\0|\0\0"
                + getString(R.string.followers) + "\0" + user.getFollowers_count());
        mDescription.setText(getString(R.string.description) + user.getDescription());
    }

    @Override
    protected void onDestroy() {
        LogUtil.d(TAG, "onDestroy");
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
