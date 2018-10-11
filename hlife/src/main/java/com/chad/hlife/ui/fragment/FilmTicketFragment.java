package com.chad.hlife.ui.fragment;

import com.chad.hlife.R;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.ui.base.BaseRxFragment;
import com.chad.hlife.util.LogUtil;

public class FilmTicketFragment extends BaseRxFragment {

    private static final String TAG = FilmTicketFragment.class.getSimpleName();

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_film_ticket;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        ActivityHelper.startFilmTicketActivity(getActivity());
    }
}
