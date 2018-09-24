package com.chad.weibo.ui.view.textview;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.chad.weibo.util.LogUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class AutoTextView extends AppCompatTextView {

    private static final String TAG = AutoTextView.class.getSimpleName();

    private static AutoCacheMap<String, SpannableString> mSpannableCache;
    private static List<AutoTextView> mAutoTextViews;
    private static LinkedBlockingQueue<String> mTextQueue;

    private String mSpannableKey;
    private String mContent;

    private SpannableString mSpannableString;

    public AutoTextView(Context context) {
        this(context, null);
    }

    public AutoTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSpannableCache = new AutoCacheMap<>(20);
        mAutoTextViews = new ArrayList<>();
        mTextQueue = new LinkedBlockingQueue<>();
    }

    @Override
    protected void onAttachedToWindow() {
        LogUtil.d(TAG, "onAttachedToWindow");
        mAutoTextViews.add(this);
        if (!TextUtils.isEmpty(mSpannableKey)) {
            SpannableString spannableString = mSpannableCache.get(mSpannableKey);
            if (spannableString != null) {
                setSpannableString(spannableString);
            } else {
                addText(mContent);
            }
        }
        super.onAttachedToWindow();
    }

    private void setSpannableString(SpannableString spannableString) {
        if (mSpannableString == spannableString) {
            return;
        }
        mSpannableString = spannableString;
        mAutoTextViews.remove(this);
        super.setText(spannableString);
    }

    public static void addText(String text) {
        synchronized (mTextQueue) {
            String key = MD5(text);
            if (mSpannableCache.get(key) == null) {
                mTextQueue.add(key);
            }
        }
    }

    private static String MD5(String key) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            byte[] bytes = mDigest.digest();
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < bytes.length; ++i) {
                String hex = Integer.toHexString(255 & bytes[i]);
                if (hex.length() == 1) {
                    sb.append('0');
                }

                sb.append(hex);
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException var6) {
            return String.valueOf(key.hashCode());
        }
    }
}
