package com.chad.zhihu.util;

import com.chad.zhihu.entity.zhihu.DetailInfo;

import java.util.List;

public class HtmlUtil {

    public static final String MIME_TYPE = "text/html; charset=utf-8";
    public static final String ENCODED = "utf-8";

    // JavaScript标签格式化形式
    private static final String FORMAT_TAG_JS = "<script src=\"%s\"></script>";
    // CSS标签格式化形式
    private static final String FORMAT_TAG_CSS = "<link rel=\"stylesheet\" type=\"text/css\" href=\"%s\"/>";
    // CSS样式,隐藏header
    private static final String STYLE_HEADER_HIDE = "<style>div.headline{display:none;}</style>";

    /**
     * 获取Html字符串格式
     * @param detailInfo
     * @return
     */
    public static String getHtml(DetailInfo detailInfo) {
        if (detailInfo == null) {
            return null;
        }
        final String js = createJavaScript(detailInfo.getJs());
        final String css = createCss(detailInfo.getCss());
        return createHtml(detailInfo.getBody(), js, css);
    }

    /**
     * 创建js标签
     * @param javaScripts
     * @return
     */
    private static String createJavaScript(List<String> javaScripts) {
        if (javaScripts == null) {
            return null;
        }
        final StringBuffer buffer = new StringBuffer();
        for (String js : javaScripts) {
            buffer.append(String.format(FORMAT_TAG_JS, js));
        }
        return buffer.toString();
    }

    /**
     * 创建css标签
     * @param css
     * @return
     */
    private static String createCss(List<String> css) {
        if (css == null) {
            return null;
        }
        final StringBuffer buffer = new StringBuffer();
        for (String c : css) {
            buffer.append(String.format(FORMAT_TAG_CSS, c));
        }
        return buffer.toString();
    }

    /**
     * 拼接成Html
     * @param body
     * @param js
     * @param css
     * @return
     */
    private static String createHtml(String body, String js, String css) {
        return css.concat(STYLE_HEADER_HIDE).concat(body).concat(js);
    }
}
