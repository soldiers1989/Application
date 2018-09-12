package com.chad.learning.rxjava.demo.filter.shake;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * 功能防抖
 */
public class ShakeActivity extends BaseAppCompatActivity {
    
    @BindView(R.id.btn_shake)
    AppCompatButton mBtnShake;
    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_java_demo_filter_shake;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {
        RxView.clicks(mBtnShake) // 实现按钮的点击事件
                .throttleFirst(2, TimeUnit.SECONDS) // 发送两秒内的第一次事件
                .subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(Object o) throws Exception {
                        mTextContent.setText("发送了网络请求一次，无论点击多少次按钮，我只会发一次");
                    }
                });
    }
}
