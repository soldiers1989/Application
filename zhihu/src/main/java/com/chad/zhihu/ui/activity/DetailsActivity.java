package com.chad.zhihu.ui.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.webkit.WebView;

import com.chad.zhihu.R;
import com.chad.zhihu.app.Constant;
import com.chad.zhihu.entity.zhihu.DetailsExtraInfo;
import com.chad.zhihu.entity.zhihu.DetailsInfo;
import com.chad.zhihu.hepler.ActivityHelper;
import com.chad.zhihu.glide.CustomGlideModule;
import com.chad.zhihu.mvp.zhihu.presenter.details.DetailsPresenter;
import com.chad.zhihu.mvp.zhihu.view.IDetailsView;
import com.chad.zhihu.ui.base.BaseSwipeBackRxAppCompatActivity;
import com.chad.zhihu.util.HtmlUtil;
import com.chad.zhihu.util.LogUtil;
import com.chad.zhihu.util.StringUtil;
import com.chad.zhihu.util.SystemStatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailsActivity extends BaseSwipeBackRxAppCompatActivity<IDetailsView, DetailsPresenter>
        implements IDetailsView {

    private static final String TAG = DetailsActivity.class.getSimpleName();

    @BindView(R.id.layout_appbar)
    AppBarLayout mLayoutAppBar;
    @BindView(R.id.image_preview)
    AppCompatImageView mImagePreview;
    @BindView(R.id.text_title)
    AppCompatTextView mTextTitle;
    @BindView(R.id.text_source)
    AppCompatTextView mTextSource;
    @BindView(R.id.web_detail)
    WebView mWebDetail;
    @BindView(R.id.text_like)
    AppCompatTextView mTextLike;
    @BindView(R.id.text_comment)
    AppCompatTextView mTextComment;

    private DetailsInfo mDetailsInfo = null;
    private DetailsExtraInfo mDetailsExtraInfo = null;

    private ArrayList<Integer> mStoryIds = null;

    private int mCurrentId;
    private int mCurrentIndex;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected DetailsPresenter getPresenter() {
        return new DetailsPresenter();
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        SystemStatusBarUtil.setTranslucentStatusBar(this);
        initAppBar();
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        Intent intent = getIntent();
        if (intent != null) {
            mStoryIds = intent.getIntegerArrayListExtra(Constant.EXTRA_ID_LIST);
            mCurrentId = intent.getIntExtra(Constant.EXTRA_ID, -1);
        }
        if (mCurrentId != -1) {
            presenter.getDetailsInfo(bindToLifecycle(), mCurrentId);
        } else {
            onError("");
        }
        if (mStoryIds != null) {
            for (int i = 0; i < mStoryIds.size(); i++) {
                if (mStoryIds.get(i) == mCurrentId) {
                    mCurrentIndex = i;
                }
            }
        }
    }

    private void initAppBar() {
        LogUtil.d(TAG, "initAppBar");
        mLayoutAppBar.addOnOffsetChangedListener((appBarLayout, i) -> {
            // 0全部展开
            if (i == 0) {
                SystemStatusBarUtil.unlockStatusBar(DetailsActivity.this);
            } else {
                SystemStatusBarUtil.lockStatusBar(DetailsActivity.this);
            }
        });
    }

    @OnClick(R.id.btn_up)
    public void onClickUp() {
        LogUtil.d(TAG, "onClickUp");
        if (mCurrentIndex == 0) {
            return;
        }
        mCurrentIndex--;
        presenter.getDetailsInfo(bindToLifecycle(), mStoryIds.get(mCurrentIndex));
    }

    @OnClick(R.id.btn_down)
    public void onClickDown() {
        LogUtil.d(TAG, "onClickDown");
        if (mCurrentIndex == mStoryIds.size() - 1) {
            return;
        }
        mCurrentIndex++;
        presenter.getDetailsInfo(bindToLifecycle(), mStoryIds.get(mCurrentIndex));
    }

    @OnClick(R.id.btn_share)
    public void onClickShare() {
        LogUtil.d(TAG, "onClickShare");
        ActivityHelper.share(this, mDetailsInfo.getTitle(), mDetailsInfo.getId());
    }

    @OnClick(R.id.btn_comment)
    public void onClickComment() {
        LogUtil.d(TAG, "onClickComment");
        ActivityHelper.startCommentActivity(this, mDetailsExtraInfo.getComments(),
                mDetailsExtraInfo.getLongComments(), mDetailsExtraInfo.getShortComments(),
                mDetailsInfo.getId());
    }

    @Override
    public void onDetailsInfo(DetailsInfo detailsInfo) {
        LogUtil.d(TAG, "onDetailsInfo : detailsInfo = " + detailsInfo);
        if (detailsInfo == null) {
            return;
        }
        mDetailsInfo = detailsInfo;
        CustomGlideModule.loadImage(getApplicationContext(), detailsInfo.getImage(), mImagePreview);

        mTextTitle.setText(detailsInfo.getTitle());
        mTextSource.setText(detailsInfo.getImage_source());

        String html = HtmlUtil.getHtml(detailsInfo);
        mWebDetail.loadData(html, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODED);
    }

    @Override
    public void onDetailsExtraInfo(DetailsExtraInfo detailsExtraInfo) {
        LogUtil.d(TAG, "onDetailsExtraInfo : detailsExtraInfo = " + detailsExtraInfo);
        if (detailsExtraInfo == null) {
            return;
        }
        mDetailsExtraInfo = detailsExtraInfo;
        mTextLike.setText(detailsExtraInfo.getPopularity() >= 99 ?
                StringUtil.findStringById(this, R.string.details_extra_info) :
                String.valueOf(detailsExtraInfo.getPopularity()));
        mTextComment.setText(detailsExtraInfo.getComments() >= 99 ?
                StringUtil.findStringById(this, R.string.details_extra_info) :
                String.valueOf(detailsExtraInfo.getComments()));
    }

    @Override
    public void onError(String msg) {
        LogUtil.d(TAG, "onError : msg = " + msg);
    }
}
