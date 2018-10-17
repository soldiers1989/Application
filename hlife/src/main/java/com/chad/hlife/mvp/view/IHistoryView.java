package com.chad.hlife.mvp.view;

import com.chad.hlife.entity.mob.HistoryInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface IHistoryView extends IBaseView {

    void onHistoryInfo(HistoryInfo historyInfo);
}
