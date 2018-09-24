package com.chad.weibo.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.weibo.R;
import com.chad.weibo.constant.AppConstant;
import com.chad.weibo.entity.TimeLine;
import com.chad.weibo.helper.WeiBoAuthHelper;
import com.chad.weibo.mvp.presenter.user.UserPresenter;
import com.chad.weibo.mvp.view.IUserView;
import com.chad.weibo.ui.adapter.TimeLineAdapter;
import com.chad.weibo.ui.base.BaseMvpFragment;
import com.chad.weibo.util.LogUtil;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class UserStatusFragment extends BaseMvpFragment<IUserView, UserPresenter> implements IUserView {

    private static final String TAG = UserStatusFragment.class.getSimpleName();

    @BindView(R.id.view_recycler_user)
    RecyclerView mHomeRecyclerView;

    private TimeLineAdapter mTimeLineAdapter = null;

    private int mPage = 1;
    private int mFeature = AppConstant.TIME_LINE_FEATURE_ALL;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_status;
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        initRecyclerView();
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        mTimeLineAdapter = new TimeLineAdapter(getActivity());
        mHomeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeRecyclerView.setAdapter(mTimeLineAdapter);
        mHomeRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mHomeRecyclerView
                        .getLayoutManager();
                if (linearLayoutManager.findFirstVisibleItemPosition() > linearLayoutManager.getChildCount() - 5) {
//                    getMoreUserTimeLine(++mPage, mFeature);
                }
            }
        });
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        mTimeLineAdapter.setData(list);
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
//        getUserTimeLine(mPage, mFeature);
    }

    private void getUserTimeLine(int page, int feature) {
        LogUtil.d(TAG, "getUserTimeLine");
        Oauth2AccessToken accessToken = WeiBoAuthHelper.getInstance().getOauth2AccessToken();
        if (accessToken != null) {
            String access_token = accessToken.getToken();
            long uid = Long.parseLong(accessToken.getUid());
            presenter.getUserTimeLine(bindToLifecycle(), access_token, uid, AppConstant.TIME_LINE_COUNT,
                    page, feature);
        }
    }

    private void getMoreUserTimeLine(int page, int feature) {
        LogUtil.d(TAG, "getMoreUserTimeLine");
        Oauth2AccessToken accessToken = WeiBoAuthHelper.getInstance().getOauth2AccessToken();
        if (accessToken != null) {
            String access_token = accessToken.getToken();
            long uid = Long.parseLong(accessToken.getUid());
            presenter.getMoreUserTimeLine(bindToLifecycle(), access_token, uid, AppConstant.TIME_LINE_COUNT,
                    page, feature);
        }
    }

    @Override
    protected UserPresenter getPresenter() {
        return new UserPresenter();
    }

    @Override
    public void onUserTimeLine(TimeLine timeLine) {
        LogUtil.d(TAG, "onUserTimeLine : timeLine = " + (timeLine == null ? null : "Not Null"));
        if (timeLine == null) {
            return;
        }
//        mTimeLineAdapter.setData(timeLine.getStatuses());
        mPage = 1;
    }

    @Override
    public void onMoreUserTimeLine(TimeLine timeLine) {
        LogUtil.d(TAG, "onMoreUserTimeLine : timeLine = " + (timeLine == null ? null : "Not Null"));
        if (timeLine == null) {
            return;
        }
//        mTimeLineAdapter.addData(timeLine.getStatuses());
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError : message = " + ((Throwable) object).getMessage());
    }
}
