package com.chad.learning.rxjava.operator.filter.activity;

import android.support.v7.widget.AppCompatTextView;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * 根据指定事件位置过滤事件
 */
public class IndexActivity extends BaseAppCompatActivity {

    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;

    private String mContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_java_operator_filter_index;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    // firstElement()过滤操作符，发送第一个事件
    @OnClick(R.id.btn_first_element)
    public void onFirstElement() {
        mContent = "firstElement \n";
        Observable.just(1, 2, 3)
                .firstElement() // 获取第一个元素
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {
                        mContent += "accept = " + integer + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // lastElement()过滤操作符，发送最后一个事件
    @OnClick(R.id.btn_last_element)
    public void onLastElement() {
        mContent = "lastElement \n";
        Observable.just(1, 2, 3)
                .lastElement() // 获取最后一个元素
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {
                        mContent += "accept = " + integer + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // elementAt()过滤操作符，通过索引发送指定位置上的事件
    @OnClick(R.id.btn_element_at)
    public void onElementAt() {
        mContent = "elementAt \n";
        Observable.just(1, 2, 3)
                .elementAt(1) // 获取第一个位置上的元素
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {
                        mContent += "accept = " + integer + "\n";
                        mTextContent.setText(mContent);
                    }
                });

        Observable.just(1, 2, 3)
                .elementAt(5, 5) // 获取的位置索引大于发送事件序列长度时，设置默认参数
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {
                        mContent += "accept = " + integer + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // elementAtOrError()过滤操作符，在elementAt()基础上，当出现越界情况就抛出异常
    @OnClick(R.id.btn_element_at_or_error)
    public void onElementAtOrError() {
        mContent = "elementAtOrError \n";
        Observable.just(1, 2, 3)
                .elementAtOrError(5) // 出现越界，抛出异常
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {
                        mContent += "accept = " + integer + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }
}
