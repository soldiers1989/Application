package com.chad.weibo.ui.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.weibo.R;
import com.chad.weibo.entity.User;
import com.chad.weibo.eventbus.EventMessage;
import com.chad.weibo.eventbus.EventType;
import com.chad.weibo.ui.adapter.UserSheetAdapter;
import com.chad.weibo.ui.base.BaseRxFragment;
import com.chad.weibo.util.DateUtil;
import com.chad.weibo.util.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class UserSheetFragment extends BaseRxFragment {

    private static final String TAG = UserSheetFragment.class.getSimpleName();

    @BindView(R.id.view_recycler_account)
    RecyclerView mAccountRecyclerView;
    @BindView(R.id.view_recycler_personal)
    RecyclerView mPersonalRecyclerView;

    private UserSheetAdapter mUserSheetAccountAdapter;
    private UserSheetAdapter mUserSheetPersonalAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_sheet;
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        initRecyclerView();
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        mUserSheetAccountAdapter = new UserSheetAdapter(getContext(), UserSheetAdapter.TYPE_LIST_ACCOUNT);
        mAccountRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAccountRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mAccountRecyclerView.setAdapter(mUserSheetAccountAdapter);

        mUserSheetPersonalAdapter = new UserSheetAdapter(getContext(), UserSheetAdapter.TYPE_LIST_PERSONAL);
        mPersonalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPersonalRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mPersonalRecyclerView.setAdapter(mUserSheetPersonalAdapter);
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUser(EventMessage eventMessage) {
        LogUtil.d(TAG, "onUser : eventMessage = " + (eventMessage == null ? null : "Not Null"));
        if (eventMessage == null) {
            return;
        }
        switch (eventMessage.getType()) {
            case EventType.TYPE_USER:
                handleUser((User) eventMessage.getObject());
                break;
            default:
                break;
        }
    }

    private void handleUser(User user) {
        LogUtil.d(TAG, "handleUser : user = " + (user == null ? null : "Not Null"));
        if (user == null) {
            return;
        }
        String[] accountValues = new String[]{getString(R.string.rank) + " " + user.getUrank(),
                DateUtil.formatDate(user.getCreated_at())};
        mUserSheetAccountAdapter.setValues(accountValues);
        String[] personalValues = new String[]{user.getGender().equals("m") ?
                getString(R.string.male): getString(R.string.female),
                user.getLocation()};
        mUserSheetPersonalAdapter.setValues(personalValues);
    }

    @Override
    public void onDestroyView() {
        LogUtil.d(TAG, "onDestroyView");
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
