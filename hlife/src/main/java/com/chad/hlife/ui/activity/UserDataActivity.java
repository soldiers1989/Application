package com.chad.hlife.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.mvp.presenter.user.UserDataPresenter;
import com.chad.hlife.mvp.view.IUserDataView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;

import butterknife.BindView;

public class UserDataActivity extends BaseMvpAppCompatActivity<IUserDataView, UserDataPresenter>
        implements IUserDataView {

    private static final String TAG = UserDataActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected UserDataPresenter onGetPresenter() {
        return new UserDataPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_user_data;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "initViews");
        initColor();
        initToolbar();
    }

    private void initColor() {
        LogUtil.d(TAG, "initColor");
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(AppConstant.COLOR_STATUS_BAR_BLUE));
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitle(R.string.user_data);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_back_light);
        mToolbar.setNavigationOnClickListener(view -> finish());
    }

    @Override
    protected void onInitData() {

    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
