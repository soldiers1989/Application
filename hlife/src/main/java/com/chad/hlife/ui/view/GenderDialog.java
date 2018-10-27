package com.chad.hlife.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.Gravity;
import android.view.Window;
import android.widget.CompoundButton;

import com.chad.hlife.R;
import com.chad.hlife.util.LogUtil;

import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class GenderDialog extends Dialog implements CompoundButton.OnCheckedChangeListener {

    private static final String TAG = GenderDialog.class.getSimpleName();

    private OnSubmitClickListener mOnSubmitClickListener;

    private String mGender;

    public interface OnSubmitClickListener {

        void onGender(String gender);
    }

    public GenderDialog(Context context) {
        super(context, R.style.GeneralDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_gender);
        ButterKnife.bind(this);
        initWindow();
        mGender = getContext().getString(R.string.man);
    }

    private void initWindow() {
        LogUtil.d(TAG, "initWindow");
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
    }

    public void setOnSubmitClickListener(OnSubmitClickListener listener) {
        this.mOnSubmitClickListener = listener;
    }

    @OnClick(R.id.btn_submit)
    public void onSubmitClick() {
        LogUtil.d(TAG, "onSubmitClick");
        if (mOnSubmitClickListener != null) {
            mOnSubmitClickListener.onGender(mGender);
        }
        dismiss();
    }

    @OnClick(R.id.btn_cancel)
    public void onCancelClick() {
        LogUtil.d(TAG, "onCancelClick");
        dismiss();
    }

    @OnCheckedChanged({R.id.btn_man, R.id.btn_woman})
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        LogUtil.d(TAG, "onCheckedChanged : buttonView = " + buttonView.getText().toString()
                + " , isChecked = " + isChecked);
        if (isChecked) {
            mGender = buttonView.getText().toString();
        }
    }
}
