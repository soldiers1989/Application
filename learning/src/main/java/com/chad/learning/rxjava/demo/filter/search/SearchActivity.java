package com.chad.learning.rxjava.demo.filter.search;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * 联想搜索优化
 */
public class SearchActivity extends BaseAppCompatActivity {

    @BindView(R.id.edit_search)
    AppCompatEditText mEditContent;
    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_java_demo_filter_search;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {
        // 此处用了RxBinding
        RxTextView.textChanges(mEditContent) // 对控件数据变更进行监听
                .debounce(1, TimeUnit.SECONDS) // 一秒采集一次数据(发送一次事件)
                .skip(1) // 跳过初始输入框的空字符状态
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CharSequence>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        mTextContent.setText("发送给服务器的字符" + charSequence.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
