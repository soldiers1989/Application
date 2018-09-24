package com.chad.zhihu.ui.fragment;

import android.support.v7.widget.AppCompatCheckBox;
import android.widget.CompoundButton;

import com.chad.zhihu.R;
import com.chad.zhihu.app.AppSettings;
import com.chad.zhihu.ui.base.BaseRxFragment;
import com.chad.zhihu.util.LogUtil;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

public class SettingsFragment extends BaseRxFragment
        implements CompoundButton.OnCheckedChangeListener {

    private static final String TAG = SettingsFragment.class.getSimpleName();

    @BindView(R.id.check_box_graph_browsing)
    AppCompatCheckBox mCheckBoxGraphBrowsing;
    @BindView(R.id.check_box_built_in_browser)
    AppCompatCheckBox mCheckBoxBuiltInBrowser;

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
        mCheckBoxGraphBrowsing.setChecked(AppSettings.getInstance().isGraphBrowsing());
        mCheckBoxBuiltInBrowser.setChecked(AppSettings.getInstance().isBuiltInBrowser());
    }

    @OnCheckedChanged({R.id.check_box_graph_browsing, R.id.check_box_built_in_browser})
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.check_box_graph_browsing:
                AppSettings.getInstance().setGraphBrowsing(isChecked);
                break;
            case R.id.check_box_built_in_browser:
                AppSettings.getInstance().setBuiltInBrowser(isChecked);
                break;
            default:
                break;
        }
    }
}
