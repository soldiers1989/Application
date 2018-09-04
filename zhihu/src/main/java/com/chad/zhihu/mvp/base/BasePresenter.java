package com.chad.zhihu.mvp.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<T> {

    private Reference<T> mViewReference = null;

    public void attachView(T view) {
        mViewReference = new WeakReference<>(view);
    }

    public void detachView() {
        if (mViewReference != null && mViewReference.get() != null) {
            mViewReference.clear();
            mViewReference = null;
        }
    }

    protected T getView() {
        return mViewReference == null ? null : mViewReference.get();
    }

    protected boolean isViewAttached() {
        return mViewReference != null && mViewReference.get() != null;
    }
}
