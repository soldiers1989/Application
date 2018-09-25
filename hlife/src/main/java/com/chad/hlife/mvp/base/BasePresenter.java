package com.chad.hlife.mvp.base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V> {

    private WeakReference<V> mViewReference = null;

    public void attachView(V view) {
        mViewReference = new WeakReference<>(view);
    }

    public void detachView() {
        if (mViewReference != null && mViewReference.get() != null) {
            mViewReference.clear();
            mViewReference = null;
        }
    }

    protected V getView() {
        return mViewReference == null? null: mViewReference.get();
    }

    protected boolean isAttachView() {
        return mViewReference != null && mViewReference.get() != null;
    }
}
