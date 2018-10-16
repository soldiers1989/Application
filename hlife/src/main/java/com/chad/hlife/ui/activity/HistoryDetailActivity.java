package com.chad.hlife.ui.activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.eventbus.EventMessage;
import com.chad.hlife.eventbus.EventType;
import com.chad.hlife.ui.base.BaseRxAppCompatActivity;
import com.chad.hlife.ui.view.loading.DoubleCircleLoadingView;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class HistoryDetailActivity extends BaseRxAppCompatActivity {

    private static final String TAG = HistoryDetailActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.text_event)
    AppCompatTextView mTextEvent;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;
    @BindView(R.id.view_loading)
    DoubleCircleLoadingView mLoadingView;

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_history_detail;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(AppConstant.COLOR_STATUS_BAR_BLACK));
        mLoadingView.setColor(getResources().getColor(AppConstant.COLOR_STATUS_BAR_BLUE));
        initToolbar();
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setNavigationIcon(R.drawable.ic_close_dark);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        EventBus.getDefault().register(this);
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        LogUtil.d(TAG, "handleIntent : intent = " + (intent == null ? "Null" : "Not Null"));
        if (intent == null) {
            return;
        }
        String title = intent.getStringExtra(AppConstant.EXTRA_TITLE);
        if (!TextUtils.isEmpty(title)) {
            mToolbar.setTitle(title);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHistoryEvent(EventMessage eventMessage) {
        LogUtil.d(TAG, "onHistoryEvent : eventMessage = " + (eventMessage == null ? null : "Not Null"));
        if (eventMessage == null) {
            return;
        }
        switch (eventMessage.getType()) {
            case EventType.TYPE_HISTORY:
                mTextEvent.setText((String) eventMessage.getObject());
                if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
                    mLoading.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        LogUtil.d(TAG, "onDestroy");
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
