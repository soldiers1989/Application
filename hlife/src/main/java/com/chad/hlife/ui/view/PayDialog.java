package com.chad.hlife.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.util.LogUtil;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayDialog extends Dialog {

    private static final String TAG = PayDialog.class.getSimpleName();

    @BindViews({R.id.image_check_ali, R.id.image_check_wx, R.id.image_check_union})
    List<AppCompatImageView> mImageChecks;

    private OnPayClickListener mOnPayClickListener;

    private int mPayModel = -1;

    public interface OnPayClickListener {

        void onPay(int payModel);
    }

    public PayDialog(Context context) {
        super(context, R.style.PayDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_pay);
        ButterKnife.bind(this);
        initWindow();
        initData();
    }

    private void initWindow() {
        LogUtil.d(TAG, "initWindow");
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.y = 0;
        window.setAttributes(layoutParams);
    }

    private void initData() {
        LogUtil.d(TAG, "initData");
        setChecked(AppConstant.PAY_MODEL_ALI);
    }

    public void setOnPayClickListener(OnPayClickListener listener) {
        mOnPayClickListener = listener;
    }

    @OnClick({R.id.layout_ali})
    public void onAliClick() {
        LogUtil.d(TAG, "onAliClick");
        setChecked(AppConstant.PAY_MODEL_ALI);
    }

    @OnClick(R.id.layout_wx)
    public void onWxClick() {
        LogUtil.d(TAG, "onWxClick");
        setChecked(AppConstant.PAY_MODEL_WX);
    }

    @OnClick(R.id.layout_union)
    public void onUnionClick() {
        LogUtil.d(TAG, "onUnionClick");
        setChecked(AppConstant.PAY_MODEL_UNION);
    }

    @OnClick(R.id.btn_pay)
    public void onPayClick() {
        LogUtil.d(TAG, "onPayClick");
        if (mOnPayClickListener != null) {
            mOnPayClickListener.onPay(mPayModel);
        }
    }

    private void setChecked(int payModel) {
        LogUtil.d(TAG, "setChecked : payModel = " + payModel + " , mPayModel = " + mPayModel);
        if (mImageChecks == null || mImageChecks.size() == 0 || mPayModel == payModel) {
            return;
        }
        for (int i = 0; i < mImageChecks.size(); i++) {
            mImageChecks.get(i).setVisibility(View.GONE);
        }
        mImageChecks.get(payModel).setVisibility(View.VISIBLE);
        mPayModel = payModel;
    }
}
