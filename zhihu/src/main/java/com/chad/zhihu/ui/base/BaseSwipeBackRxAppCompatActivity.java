package com.chad.zhihu.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chad.zhihu.mvp.base.BasePresenter;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

public abstract class BaseSwipeBackRxAppCompatActivity<V, T extends BasePresenter<V>>
        extends RxAppCompatActivity implements BGASwipeBackHelper.Delegate {

    private Unbinder unbinder = null;

    protected T presenter = null;
    protected BGASwipeBackHelper bgaSwipeBackHelper = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initSwipeBackHelper();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        presenter = getPresenter();
        presenter.attachView((V) this);
        initViews();
        initData();
    }

    /**
     * 初始化SwipeBackHelper，在super.onCreate(savedInstanceState);之前
     */
    private void initSwipeBackHelper() {
        bgaSwipeBackHelper = new BGASwipeBackHelper(this, this);
    }

    /**
     * 是否支持滑动返回
     * 设置为true支持滑动返回
     * 设置为false不支持滑动返回
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 正在滑动返回
     * @param slideOffset 0 ~ 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {

    }

    /**
     * 没达到滑动返回的阈值
     * 取消滑动返回动作
     * 回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {

    }

    /**
     * 滑动返回执行完毕
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        if (bgaSwipeBackHelper != null) {
            bgaSwipeBackHelper.swipeBackward();
        }
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
        if (bgaSwipeBackHelper != null) {
            bgaSwipeBackHelper = null;
        }
        super.onDestroy();
    }

    protected abstract int getLayoutId();

    protected abstract T getPresenter();

    protected abstract void initViews();

    protected abstract void initData();
}
