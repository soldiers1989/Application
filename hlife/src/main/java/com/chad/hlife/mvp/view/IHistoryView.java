package com.chad.hlife.mvp.view;

import com.chad.hlife.entity.juhe.HistoryDetailInfo;
import com.chad.hlife.entity.juhe.HistoryInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface IHistoryView extends IBaseView {

    void onHistoryInfo(HistoryInfo historyInfo);

    void onHistoryDetailInfo(HistoryDetailInfo historyDetailInfo);
}
