package com.chad.hlife.util;

import android.text.TextUtils;

import java.util.regex.Pattern;

public class CheckUtil {
    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "[\u4e00-\u9fa5_a-zA-Z0-9]{5,16}";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "[a-zA-Z0-9]{10,16}";

    public static boolean isUserName(String userName) {
        if (TextUtils.isEmpty(userName)) {
            return false;
        }
        return Pattern.matches(REGEX_USERNAME, userName);
    }

    public static boolean isPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        return Pattern.matches(REGEX_PASSWORD, password);
    }

}
