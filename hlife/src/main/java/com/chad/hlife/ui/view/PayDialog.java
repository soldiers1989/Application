package com.chad.hlife.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.chad.hlife.R;
import com.chad.hlife.util.LogUtil;

public class PayDialog extends Dialog {

    private static final String TAG = PayDialog.class.getSimpleName();

    public PayDialog(Context context) {
        super(context, R.style.PayDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_pay);
        initWindow();
    }

    private void initWindow() {
        LogUtil.d(TAG, "initWindow");
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = 0;
        window.setAttributes(layoutParams);
    }
}
