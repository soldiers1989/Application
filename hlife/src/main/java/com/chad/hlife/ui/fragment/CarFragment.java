package com.chad.hlife.ui.fragment;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.config.MobConfig;
import com.chad.hlife.entity.mob.CarBrandInfo;
import com.chad.hlife.entity.mob.CarDetailInfo;
import com.chad.hlife.entity.mob.CarTypeInfo;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.car.CarPresenter;
import com.chad.hlife.mvp.view.ICarView;
import com.chad.hlife.ui.adapter.CarBrandAdapter;
import com.chad.hlife.ui.base.BaseMvpFragment;
import com.chad.hlife.ui.view.LetterIndexView;
import com.chad.hlife.ui.view.loading.DoubleCircleLoadingView;
import com.chad.hlife.util.LogUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class CarFragment extends BaseMvpFragment<ICarView, CarPresenter> implements ICarView {

    private static final String TAG = CarFragment.class.getSimpleName();

    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.view_letter_index)
    LetterIndexView mLetterIndexView;
    @BindView(R.id.text_letter)
    AppCompatTextView mTextLetter;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;
    @BindView(R.id.view_loading)
    DoubleCircleLoadingView mLoadingView;

    private OnScrollListener mOnScrollListener;
    private CarBrandAdapter mCarBrandAdapter;
    private Disposable mDisposable = null;

    private Map<String, Integer> mLetterIndexMap;

    @Override
    protected CarPresenter onGetPresenter() {
        return new CarPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_car;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "initViews");
        initColor();
        initRecyclerView();
        initLetterIndexView();
    }

    private void initColor() {
        LogUtil.d(TAG, "initColor");
        mLoadingView.setColor(getResources().getColor(AppConstant.COLOR_STATUS_BAR_BLUE));
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mOnScrollListener = new OnScrollListener();
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        mCarBrandAdapter = new CarBrandAdapter(getContext());
        mCarBrandAdapter.setOnItemClickListener(position ->
                ActivityHelper.startCarActivity(getActivity(),
                        mCarBrandAdapter.getData().get(position).getName(),
                        mCarBrandAdapter.getData().get(position).getName())
        );
        mRecyclerView.setAdapter(mCarBrandAdapter);
    }

    private void initLetterIndexView() {
        LogUtil.d(TAG, "initLetterIndexView");
        mLetterIndexView.setOnLetterChangedListener(new LetterIndexView.OnLetterChangedListener() {
            @Override
            public void onTouchDown() {
                LogUtil.d(TAG, "onTouchDown");
                if (mTextLetter.getVisibility() != View.VISIBLE) {
                    mTextLetter.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTouchUp() {
                LogUtil.d(TAG, "onTouchUp");
                if (mTextLetter.getVisibility() == View.VISIBLE) {
                    mTextLetter.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLetterChanged(String letter) {
                LogUtil.d(TAG, "onLetterChanged : letter = " + letter);
                if (mLetterIndexMap.containsKey(letter)) {
                    mTextLetter.setText(letter);
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                    int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                    int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    int currentIndex = mLetterIndexMap.get(letter);
                    if (currentIndex <= firstVisibleItem) {
                        mRecyclerView.scrollToPosition(currentIndex);
                    } else if (currentIndex <= lastVisibleItem) {
                        int top = mRecyclerView.getChildAt(currentIndex - firstVisibleItem).getTop();
                        mRecyclerView.scrollBy(0, top);
                    } else {
                        mRecyclerView.scrollToPosition(currentIndex);
                    }
                }
            }
        });
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        presenter.getCarBrandInfo(bindToLifecycle(), MobConfig.APP_KEY);
    }

    @Override
    public void onCarBrandInfo(CarBrandInfo carBrandInfo) {
        LogUtil.d(TAG, "onCarBrandInfo : carBrandInfo = " + carBrandInfo);
        if (carBrandInfo == null) {
            return;
        }
        if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
            mLoading.setVisibility(View.GONE);
        }
        mCarBrandAdapter.setData(carBrandInfo.getResult());
        if (mLetterIndexMap == null) {
            mLetterIndexMap = new HashMap<>();
        }
        for (int i = 0; i < carBrandInfo.getResult().size(); i++) {
            if (i == 0) {
                mLetterIndexMap.put(carBrandInfo.getResult().get(i).getFirstLetter(), i);
            } else {
                String lastLetter = carBrandInfo.getResult().get(i - 1).getFirstLetter();
                String currentLetter = carBrandInfo.getResult().get(i).getFirstLetter();
                if (!lastLetter.equals(currentLetter)) {
                    mLetterIndexMap.put(carBrandInfo.getResult().get(i).getFirstLetter(), i);
                }
            }
        }
    }

    @Override
    public void onCarTypeInfo(CarTypeInfo carTypeInfo) {

    }

    @Override
    public void onCarDetailInfo(CarDetailInfo carDetailInfo) {

    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }

    private class OnScrollListener extends RecyclerView.OnScrollListener {

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            String letter = mCarBrandAdapter.getData()
                    .get(((LinearLayoutManager) mRecyclerView.getLayoutManager())
                            .findFirstVisibleItemPosition())
                    .getFirstLetter();
            mLetterIndexView.setLetterIndex(letter);
        }
    }
}
