package com.chad.hlife.util;

import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;

public class PinYinUtil {

    public static String getPinYinFirstLetter(String chinese) {
        if (TextUtils.isEmpty(chinese)) {
            return null;
        }
        char firstLetter;
        char firstChinese = chinese.charAt(0);
        String[] letters = PinyinHelper.toHanyuPinyinStringArray(firstChinese);
        if (letters != null) {
            firstLetter = letters[0].charAt(0);
        } else {
            firstLetter = firstChinese;
        }
        if (firstChinese == '长') {
            firstLetter = 'c';
        }
        return String.valueOf(firstLetter).toUpperCase();
    }
}
