package com.chad.learning.eventbus;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;
import com.jakewharton.rxbinding2.view.RxView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

public class EventBusOtherActivity extends BaseAppCompatActivity {

    @BindView(R.id.btn_start)
    AppCompatButton mBtnStart;
    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_bus_other;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {
        initButton();
    }

    private void initButton() {
        RxView.clicks(mBtnStart).subscribe(o -> {
            // 发送消息
            EventBus.getDefault().post(new EventMessage("我是来自EventBusOtherActivity"));
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
