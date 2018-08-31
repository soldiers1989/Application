package com.chad.learning.retrofit.activity;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;
import com.chad.learning.retrofit.entity.JSTranslation;
import com.chad.learning.retrofit.entity.YDTranslation;
import com.chad.learning.retrofit.interfaces.IRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.edit_from)
    AppCompatEditText mEditFrom;
    @BindView(R.id.btn_translate_get)
    AppCompatButton mBtnTranslateGet;
    @BindView(R.id.btn_translate_post)
    AppCompatButton mBtnTranslatePost;
    @BindView(R.id.text_to)
    AppCompatTextView mTextTo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_retrofit;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_translate_get, R.id.btn_translate_post})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_translate_get:
                onTranslateByGet();
                break;
            case R.id.btn_translate_post:
                onTranslateByPost();
                break;
            default:
                break;
        }
    }

    private void onTranslateByGet() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置网络请求baseURL
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson数据解析器
                .build();

        // 创建网络请求接口实例
        IRequest iRequest = retrofit.create(IRequest.class);
        // 对Get的方式发送请求进行封装
        Call<JSTranslation> call = iRequest.get();
        // 发送网络请求（异步）
        call.enqueue(new Callback<JSTranslation>() {

            @Override
            public void onResponse(Call<JSTranslation> call, Response<JSTranslation> response) {
                JSTranslation JSTranslation = response.body();
                JSTranslation.Content content = JSTranslation.getContent();
                mTextTo.setText(JSTranslation.getStatus() + "\n"
                        + content.getFrom() + "\n"
                        + content.getTo() + "\n"
                        + content.getVendor() + "\n"
                        + content.getOut() + "\n"
                        + content.getErrNo());
            }

            @Override
            public void onFailure(Call<JSTranslation> call, Throwable t) {
                mTextTo.setText("Error Get");
            }
        });
    }

    private void onTranslateByPost() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置网络请求BaseUrl
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson数据解析器
                .build();
        // 创建网络请求接口实例
        IRequest iRequest = retrofit.create(IRequest.class);
        // 以POST的方式对发送请求进行封装
        Call<YDTranslation> call = iRequest.post(mEditFrom.getText().toString());
        // 发送网络请求（异步）
        call.enqueue(new Callback<YDTranslation>() {

            @Override
            public void onResponse(Call<YDTranslation> call, Response<YDTranslation> response) {
                YDTranslation ydTranslation = response.body();
                List<YDTranslation.TranslateResultBean> list = ydTranslation.getTranslateResult().get(0);
                mTextTo.setText(ydTranslation.getType() + "\n"
                        + ydTranslation.getErrorCode() + "\n"
                        + ydTranslation.getElapsedTime() + "\n"
                        + list.get(0).getSrc() + "\n"
                        + list.get(0).getTgt());
            }

            @Override
            public void onFailure(Call<YDTranslation> call, Throwable t) {
                mTextTo.setText("Error Post");
            }
        });
    }
}
