package com.chad.zhihu.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.zhihu.R;
import com.chad.zhihu.mvp.zhihu.presenter.mine.MinePresenter;
import com.chad.zhihu.mvp.zhihu.view.IMineView;
import com.chad.zhihu.ui.adapter.MineAdapter;
import com.chad.zhihu.ui.base.BaseRxFragment;
import com.chad.zhihu.util.ArrayUtil;
import com.chad.zhihu.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;

public class MineFragment extends BaseRxFragment<IMineView, MinePresenter> implements IMineView {

    private static final String TAG = MineFragment.class.getSimpleName();

    @BindView(R.id.mine_recycler)
    RecyclerView mMineRecycler;

    private LinearLayoutManager mLinearLayoutManager = null;
    private MineAdapter mMineAdapter = null;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected MinePresenter getPresenter() {
        return new MinePresenter();
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        initMineRecycler();
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        String[] titles = ArrayUtil.findStringArrayById(getActivity(), R.array.mine);
        Observable.fromArray(titles)
                .collect((Callable<List<String>>) () -> new ArrayList<>(),
                        (titleList, title) -> titleList.add(title))
                .subscribe(titleList -> mMineAdapter.setData(titleList));
    }

    private void initMineRecycler() {
        LogUtil.d(TAG, "initMineRecycler");
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mMineAdapter = new MineAdapter(getActivity());

        mMineRecycler.setLayoutManager(mLinearLayoutManager);
        mMineRecycler.setAdapter(mMineAdapter);
    }

    @Override
    public void onError(String msg) {
        LogUtil.d(TAG, "onError : msg = " + msg);
    }
}
