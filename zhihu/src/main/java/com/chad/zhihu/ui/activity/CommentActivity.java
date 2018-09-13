package com.chad.zhihu.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.chad.zhihu.R;
import com.chad.zhihu.app.Constant;
import com.chad.zhihu.ui.adapter.CommentPagerAdapter;
import com.chad.zhihu.ui.base.BaseRxAppCompatActivity;
import com.chad.zhihu.ui.fragment.LongCommentsFragment;
import com.chad.zhihu.ui.fragment.ShortCommentsFragment;
import com.chad.zhihu.util.ColorUtil;
import com.chad.zhihu.util.LogUtil;
import com.chad.zhihu.util.StringUtil;
import com.chad.zhihu.util.SystemStatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CommentActivity extends BaseRxAppCompatActivity {

    private static final String TAG = CommentActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab_comment)
    TabLayout mCommentTab;
    @BindView(R.id.pager_comment)
    ViewPager mPagerComment;

    private CommentPagerAdapter mCommentPagerAdapter = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        SystemStatusBarUtil.setStatusBarColor(this,
                ColorUtil.findRgbById(this, R.color.colorStatusBar));
        initToolbar();
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        int comments = intent.getIntExtra(Constant.EXTRA_COMMENTS, 0);
        int longComments = intent.getIntExtra(Constant.EXTRA_COMMENTS_LONG, 0);
        int shortComments = intent.getIntExtra(Constant.EXTRA_COMMENTS_SHORT, 0);
        mToolbar.setTitle(comments + StringUtil.findStringById(this,
                R.string.comment_toolbar_title));
        initCommentTab(longComments, shortComments);
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        mToolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    private void initCommentTab(int longComments, int shortComments) {
        LogUtil.d(TAG, "initCommentTab");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new LongCommentsFragment());
        fragments.add(new ShortCommentsFragment());
        List<String> titles = new ArrayList<>();
        titles.add(StringUtil.findStringById(this, R.string.comment_long)
                + "(" + longComments + ")");
        titles.add(StringUtil.findStringById(this, R.string.comment_short)
                + "(" + shortComments + ")");
        mCommentPagerAdapter = new CommentPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mPagerComment.setAdapter(mCommentPagerAdapter);
        mCommentTab.setupWithViewPager(mPagerComment);
    }
}
