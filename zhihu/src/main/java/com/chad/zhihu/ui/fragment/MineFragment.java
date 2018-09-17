package com.chad.zhihu.ui.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.zhihu.R;
import com.chad.zhihu.mvp.base.IBaseView;
import com.chad.zhihu.ui.adapter.MineAdapter;
import com.chad.zhihu.ui.base.BaseRxFragment;
import com.chad.zhihu.util.ArrayUtil;
import com.chad.zhihu.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import butterknife.BindView;
import io.reactivex.Observable;

public class MineFragment extends BaseRxFragment implements IBaseView {

    private static final String TAG = MineFragment.class.getSimpleName();

    @BindView(R.id.mine_recycler)
    RecyclerView mMineRecycler;

    private LinearLayoutManager mLinearLayoutManager = null;
    private DividerItemDecoration mDividerItemDecoration = null;
    private MineAdapter mMineAdapter = null;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
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
        mDividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mMineAdapter = new MineAdapter(getActivity());

        mMineRecycler.setLayoutManager(mLinearLayoutManager);
        mMineRecycler.addItemDecoration(mDividerItemDecoration);
        mMineRecycler.setAdapter(mMineAdapter);
    }

    @Override
    public void onError(String msg) {
        LogUtil.d(TAG, "onError : msg = " + msg);
    }
}
