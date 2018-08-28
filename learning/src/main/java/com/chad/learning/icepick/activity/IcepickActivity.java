package com.chad.learning.icepick.activity;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import butterknife.BindView;
import icepick.Icepick;

public class IcepickActivity extends BaseAppCompatActivity {

    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;
    @BindView(R.id.btn_add)
    AppCompatButton mBtnAdd;
    
    int mResult = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_icepick;
    }

    @Override
    public void initViews() {
        mTextContent.setText(mResult + "");
        mBtnAdd.setOnClickListener(v -> {
            mResult ++;
            mTextContent.setText(mResult + "");
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Icepick.saveInstanceState(this, outState);
        super.onSaveInstanceState(outState);
    }
}
