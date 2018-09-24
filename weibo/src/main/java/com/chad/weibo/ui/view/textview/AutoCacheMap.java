package com.chad.weibo.ui.view.textview;

import java.util.LinkedHashMap;
import java.util.Map;

public class AutoCacheMap<K, V> {

    private final LinkedHashMap<K, V> mHashMap;

    private int mSize;
    private int mMaxSize;
    private int mPutCount;
    private int mEvictionCount;
    private int mHitCount;
    private int mMissCount;

    public AutoCacheMap(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException();
        } else {
            mMaxSize = maxSize;
            mHashMap = new LinkedHashMap<>(0, 0.75F, true);
        }
    }

    public final V put(K key, V value) {
        if (key != null && value != null) {
            Object object;
            synchronized (this) {
                ++mPutCount;
                mSize += 1;
                object = mHashMap.put(key, value);
                if (object != null) {
                    mSize -= 1;
                }
            }
            this.trimToSize(mMaxSize);
            return (V) object;
        } else {
            throw new NullPointerException();
        }
    }

    public final V get(K key) {
        if (key == null) {
            throw new NullPointerException();
        } else {
            Object value;
            synchronized (this) {
                value = mHashMap.get(key);
                if (value != null) {
                    ++mHitCount;
                    return (V) value;
                }
                ++mMissCount;
            }
        }
        return null;
    }

    public final V remove(K key) {
        if (key == null) {
            throw new NullPointerException();
        } else {
            Object object;
            synchronized (this) {
                object = mHashMap.remove(key);
                if (object != null) {
                    mSize -= 1;
                }
            }
            return (V) object;
        }
    }

    private void trimToSize(int maxSize) {
        while (true) {
            Object key;
            synchronized (this) {
                label47:
                {
                    if (mSize >= 0 && (mSize == 0 || !mHashMap.isEmpty())) {
                        if (mSize > maxSize && !mHashMap.isEmpty()) {
                            Map.Entry<K, V> entry = mHashMap.entrySet().iterator().next();
                            key = entry.getKey();
                            mHashMap.remove(key);
                            mSize -= 1;
                            ++mEvictionCount;
                            break label47;
                        }
                    }
                    return;
                }
                throw new IllegalStateException();
            }
        }
    }
}
