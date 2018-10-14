package com.chad.hlife.util;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class StatusBarUtil {

    private static final String TAG_VIEW_FAKE_STATUS_BAR = "fakeStatusBarView";
    private static final String TAG_VIEW_STATUS_BAR = "statusBarView";

    /**
     * 设置状态栏为全屏透明
     *
     * @param activity
     */
    public static void setFullScreenStatusBar(Activity activity) {
        if (activity == null || Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        // 获取Window
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // SDK版本大于等于4.4且小于5.0
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // SDK版本大于等于5.0
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 设置状态栏颜色
     *
     * @param activity
     * @param color
     */
    public static void setStatusBarColor(Activity activity, int color) {
        if (activity == null || Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        // 状态栏要先设置透明，4.4的伪造状态栏才能够生效
        setFullScreenStatusBar(activity);
        // 获取Window
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // SDK大于等于4.4且小于5.0
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            // 伪造状态栏
            View fakeStatusBarView;
            // 如果已经有了伪造状态栏，就要先移除
            fakeStatusBarView = decorView.findViewWithTag(TAG_VIEW_FAKE_STATUS_BAR);
            if (fakeStatusBarView != null) {
                decorView.removeView(fakeStatusBarView);
            }
            // 创建伪造状态栏
            fakeStatusBarView = new View(activity);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
            layoutParams.gravity = Gravity.TOP;
            fakeStatusBarView.setLayoutParams(layoutParams);
            fakeStatusBarView.setBackgroundColor(color);
            fakeStatusBarView.setTag(TAG_VIEW_FAKE_STATUS_BAR);
            // 添加伪造状态栏
            decorView.addView(fakeStatusBarView);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // SDK版本大于等于5.0
            window.setStatusBarColor(color);
        }
    }

    /**
     * 重置状态栏，将状态栏颜色恢复为默认的黑色
     *
     * @param activity
     */
    public static void resetStatusBarColor(Activity activity) {
        if (activity == null) {
            return;
        }
        setStatusBarColor(activity, Color.BLACK);
    }

    /**
     * 不占用状态栏的位置
     *
     * @param activity
     */
    public static void lockStatusBar(Activity activity) {
        if (activity == null) {
            return;
        }
        // 获取Window
        Window window = activity.getWindow();
        ViewGroup contentView = window.findViewById(Window.ID_ANDROID_CONTENT);
        View statusBarView = null;
        if (contentView != null && contentView.getChildCount() > 0) {
            statusBarView = contentView.getChildAt(0);
        }
        if (statusBarView != null && !TAG_VIEW_STATUS_BAR.equals(statusBarView.getTag())) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) statusBarView
                    .getLayoutParams();
            layoutParams.topMargin += getStatusBarHeight(activity);
            statusBarView.setLayoutParams(layoutParams);
            statusBarView.setTag(TAG_VIEW_STATUS_BAR);
        }
    }

    /**
     * 占用状态栏的位置
     *
     * @param activity
     */
    public static void unlockStatusBar(Activity activity) {
        if (activity == null) {
            return;
        }
        Window window = activity.getWindow();
        ViewGroup contentView = window.findViewById(Window.ID_ANDROID_CONTENT);
        View statusBarView = null;
        if (contentView != null && contentView.getChildCount() > 0) {
            statusBarView = contentView.getChildAt(0);
        }
        if (statusBarView != null && TAG_VIEW_STATUS_BAR.equals(statusBarView.getTag())) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) statusBarView
                    .getLayoutParams();
            layoutParams.topMargin -= getStatusBarHeight(activity);
            statusBarView.setLayoutParams(layoutParams);
            statusBarView.setTag(null);
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param activity
     * @return
     */
    private static int getStatusBarHeight(Activity activity) {
        if (activity == null) {
            return -1;
        }
        Resources resources = activity.getResources();
        int statusBarHeightId = resources.getIdentifier("status_bar_height",
                "dimen", "android");
        return resources.getDimensionPixelSize(statusBarHeightId);
    }
}