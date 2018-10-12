package com.chad.hlife.mvp.view.juhe;

import com.chad.hlife.entity.juhe.BookCatalogInfo;
import com.chad.hlife.entity.juhe.BookContentInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface IBooksStoreView extends IBaseView {

    void onBookCatalogInfo(BookCatalogInfo bookCatalogInfo);

    void onBookContentInfo(BookContentInfo bookContentInfo);
}
