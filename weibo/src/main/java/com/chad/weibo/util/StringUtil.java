package com.chad.weibo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static String getWeiBoSource(String source) {
        Pattern pattern = Pattern.compile("<a[^>]*>([^<]*)</a>");
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
