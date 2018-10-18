package com.chad.hlife.util;

import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;

public class PinYinUtil {

    public static String getPinYinHeader(String chinese) {
        if (TextUtils.isEmpty(chinese)) {
            return null;
        }
        String letter = null;
        char first = chinese.charAt(0);
        String[] letters = PinyinHelper.toHanyuPinyinStringArray(first);
        if (letters != null) {
            letter += letters[0].charAt(0);
        } else {
            letter += first;
        }
        return letter.toUpperCase();
    }
}
