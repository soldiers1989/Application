package com.chad.learning.eventbus;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        // 注册EventBus
        EventBus.getDefault().register(this);
        initButton();
    }

    private void initButton() {
        RxView.clicks(mBtnStart).subscribe(o -> {
            EventBus.getDefault().post(new EventMessage("我是来自EventBusOtherActivity"));
        });
    }

    // 通过注解指定在主线程中处理，不能做耗时操作
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEventMessage(EventMessage eventMessage) throws Exception {
        RxTextView.text(mTextContent).accept(eventMessage.getMessage());
    }

    @Override
    protected void onDestroy() {
        // 解除EventBus注册
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
