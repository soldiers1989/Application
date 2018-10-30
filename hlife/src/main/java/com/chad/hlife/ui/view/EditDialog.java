package com.chad.hlife.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.Gravity;
import android.view.Window;

import com.chad.hlife.R;
import com.chad.hlife.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditDialog extends Dialog {

    private static final String TAG = EditDialog.class.getSimpleName();

    @BindView(R.id.edit_mobile_phone)
    AppCompatEditText mEditPhone;

    private OnSubmitClickListener mOnSubmitClickListener;

    public interface OnSubmitClickListener {

        void onPhone(String phone);
    }

    public EditDialog(Context context) {
        super(context, R.style.GeneralDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_edit);
        ButterKnife.bind(this);
        initWindow();
    }

    private void initWindow() {
        LogUtil.d(TAG, "initWindow");
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
    }

    public void setEditHint(int resId) {
        mEditPhone.setHint(resId);
    }

    public void setOnSubmitClickListener(OnSubmitClickListener listener) {
        this.mOnSubmitClickListener = listener;
    }

    @OnClick(R.id.btn_submit)
    public void onSubmitClick() {
        LogUtil.d(TAG, "onSubmitClick");
        if (mOnSubmitClickListener != null) {
            mOnSubmitClickListener.onPhone(mEditPhone.getText().toString());
        }
        dismiss();
    }

    @OnClick(R.id.btn_cancel)
    public void onCancelClick() {
        LogUtil.d(TAG, "onCancelClick");
        dismiss();
    }
}
