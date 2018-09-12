package com.chad.learning.rxbinding.activity;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.widget.Toast;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * RxBinding
 */
public class RxBindingActivity extends BaseAppCompatActivity {

    @BindView(R.id.edit_name)
    AppCompatEditText mEditName;
    @BindView(R.id.edit_password)
    AppCompatEditText mEditPassword;
    @BindView(R.id.btn_login)
    AppCompatButton mBtnLogin;
    @BindView(R.id.btn_send_click_event)
    AppCompatButton mBtnSendClickEvent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_binding;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {
       initEdit();
       initButton();
    }

    /**
     * 通过对两个EditView的监听实现登录
     */
    private void initEdit() {
        // combineLatest()操作符，可以结合两个Observable的数据源进行输出
        Observable.combineLatest(RxTextView.textChanges(mEditName).map(new Function<CharSequence, String>() {

            @Override
            public String apply(CharSequence charSequence) throws Exception {
                return String.valueOf(charSequence);
            }
        }), RxTextView.textChanges(mEditPassword).map(new Function<CharSequence, String>() {

            @Override
            public String apply(CharSequence charSequence) throws Exception {
                return String.valueOf(charSequence);
            }
        }), new BiFunction<String, String, Boolean>() {

            @Override
            public Boolean apply(String s, String s2) throws Exception {
                boolean isName = false;
                boolean isPassword = false;
                if (!TextUtils.isEmpty(s) && s.equals("RxBinding")) {
                    isName = true;
                }
                if (!TextUtils.isEmpty(s2) && s2.equals("12345")) {
                    isPassword = true;
                }
                return isName && isPassword;
            }
        }).subscribe(new Consumer<Boolean>() {

            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    mBtnLogin.setEnabled(true);
                    RxView.clicks(mBtnLogin)
                            .subscribe(new Consumer<Object>() {

                                @Override
                                public void accept(Object o) throws Exception {
                                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }

    /**
     * 点击一个按钮，可在多处收到通知
     * 1.RxView.clicks(mBtnSendClickEvent).share()首先需要使用share这个操作符
     * 2.通过CompositeDisposable订阅多个Disposable
     */
    private void initButton() {
        Observable observable = RxView.clicks(mBtnSendClickEvent).share();

        CompositeDisposable compositeDisposable = new CompositeDisposable();

        Disposable disposable1 = observable.subscribe(new Consumer() {

            @Override
            public void accept(Object o) throws Exception {
                Toast.makeText(getApplicationContext(), "点击事件，分发一次", Toast.LENGTH_SHORT).show();
            }
        });

        Disposable disposable2 = observable.subscribe(new Consumer() {

            @Override
            public void accept(Object o) throws Exception {
                Toast.makeText(getApplicationContext(), "点击事件，分发两次", Toast.LENGTH_SHORT).show();
            }
        });

        compositeDisposable.add(disposable1);
        compositeDisposable.add(disposable2);
    }
}
