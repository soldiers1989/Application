package com.chad.zhihu.ui.fragment;

import android.support.v7.widget.AppCompatCheckBox;

import com.chad.zhihu.R;
import com.chad.zhihu.app.AppSettings;
import com.chad.zhihu.ui.base.BaseRxFragment;
import com.chad.zhihu.util.LogUtil;

import butterknife.BindView;

public class SettingsFragment extends BaseRxFragment {

    private static final String TAG = SettingsFragment.class.getSimpleName();

    @BindView(R.id.check_box_picture_mode)
    AppCompatCheckBox mCheckBoxPictureMode;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        initCheckBox();
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");

    }

    private void initCheckBox() {
        LogUtil.d(TAG, "initCheckBox");
        mCheckBoxPictureMode.setChecked(AppSettings.getInstance().isShowPicture());
        mCheckBoxPictureMode.setOnCheckedChangeListener((buttonView, isChecked) ->
                AppSettings.getInstance().setPictureMode(isChecked));
    }
}
