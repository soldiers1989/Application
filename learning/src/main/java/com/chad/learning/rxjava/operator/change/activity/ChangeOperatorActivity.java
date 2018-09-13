package com.chad.learning.rxjava.operator.change.activity;

import android.support.v7.widget.AppCompatTextView;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ChangeOperatorActivity extends BaseAppCompatActivity {

    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;

    private String mContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_java_operator_change;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    // map()变换操作符，对观察者发送的每一个事件都通过指定的函数处理，从而变换成另外一种事件
    // 即被观察者发送的事件转换为任意类型的事件
    @OnClick(R.id.btn_map)
    public void onMapClick() {
        mContent = "map \n";
        Observable.just(1, 2, 3)
                .map(new Function<Integer, String>() {

                    @Override
                    public String apply(Integer integer) throws Exception {
                        return Integer.toString(integer);
                    }
                })
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) throws Exception {
                        mContent += "accept = " + s + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // flatMap()变换操作符，将被观察者发送的事件序列进行拆分&单独转换，在合并成一个新的事件序列，
    // 最后在进行发送
    @OnClick(R.id.btn_flat_map)
    public void onFlatMap() {
        mContent = "flatMap \n";
        Observable.just(1, 2, 3)
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        // 通过flatMap中将被观察者生产的事件序列先进行拆分,
                        // 再将每个事件转换为一个新的发送三个String事件
                        // 最终合并，再发送给被观察者
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < 3; i ++) {
                            list.add("我是事件 = " + integer + " ， 拆分成的 = " + i);
                        }
                        return Observable.fromIterable(list);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mContent += "accept = " + s + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // concatMap()类似flatMap()，与flatMap()的区别是拆分&重新合并生成的事件序列的顺序等于
    // 被观察者旧序列生产的顺序
    @OnClick(R.id.btn_concat_map)
    public void onConcatMap() {
        mContent = "concatMap \n";
        Observable.just(1, 2, 3)
                .concatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        // 通过concatMap中将被观察者生产的事件序列先进行拆分,
                        // 再将每个事件转换为一个新的发送三个String事件
                        // 最终合并，再发送给被观察者
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < 3; i ++) {
                            list.add("我是事件 = " + integer + " ， 拆分成的 = " + i);
                        }
                        return Observable.fromIterable(list);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mContent += "accept = " + s + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // buffer()变换操作符，定期从被观察者需要发送的事件中获取一定数量的事件放到缓存中，最终发送
    @OnClick(R.id.btn_buffer)
    public void onBufferClick() {
        mContent = "buffer \n";
        Observable.just(1, 2, 3, 4, 5)
                .buffer(3, 1)  // 设置缓存区大小和步长，缓存区大小等于每次从被观察者中获取的
                                            // 事件数量，步长等于每次获取新事件的数量
                .subscribe(new Consumer<List<Integer>>() {

                    @Override
                    public void accept(List<Integer> integers) throws Exception {

                    }
                });
    }
}
