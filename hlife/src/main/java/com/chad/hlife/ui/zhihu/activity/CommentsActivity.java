package com.chad.hlife.ui.zhihu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.ui.base.BaseRxAppCompatActivity;
import com.chad.hlife.ui.zhihu.adapter.CommentsPagerAdapter;
import com.chad.hlife.ui.zhihu.fragment.LongCommentsFragment;
import com.chad.hlife.ui.zhihu.fragment.ShortCommentsFragment;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CommentsActivity extends BaseRxAppCompatActivity {

    private static final String TAG = CommentsActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.layout_tab)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_comments;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initColor();
        initToolbar();
    }

    private void initColor() {
        LogUtil.d(TAG, "initColor");
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(AppConstant.COLOR_STATUS_BAR_RED));
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_back_light);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        LogUtil.d(TAG, "handleIntent : intent = " + (intent == null ? "Null" : "Not Null"));
        if (intent == null) {
            return;
        }
        int commentsCount = intent.getIntExtra(AppConstant.EXTRA_COMMENTS_COUNT, -1);
        int longCommentsCount = intent.getIntExtra(AppConstant.EXTRA_COMMENTS_COUNT_LONG, -1);
        int shortCommentsCount = intent.getIntExtra(AppConstant.EXTRA_COMMENTS_COUNT_SHORT, -1);
        mToolbar.setTitle(commentsCount + getString(R.string.comment));
        initTabLayout(longCommentsCount, shortCommentsCount);
    }

    private void initTabLayout(int longCommentsCount, int shortCommentsCount) {
        LogUtil.d(TAG, "initTabLayout : longCommentsCount = " + longCommentsCount
                + " , shortCommentsCount = " + shortCommentsCount);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new LongCommentsFragment());
        fragments.add(new ShortCommentsFragment());
        List<String> titles = new ArrayList<>();
        titles.add(getString(R.string.long_comment) + "(" + longCommentsCount + ")");
        titles.add(getString(R.string.short_comment) + "(" + shortCommentsCount + ")");
        CommentsPagerAdapter commentsPagerAdapter = new CommentsPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(commentsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
