package com.chad.hlife.mvp.presenter.juhe.books;

import com.chad.hlife.entity.juhe.BookCatalogInfo;
import com.chad.hlife.entity.juhe.BookContentInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.juhe.BooksStoreModel;
import com.chad.hlife.mvp.view.juhe.IBooksStoreView;

import io.reactivex.ObservableTransformer;

public class BooksStorePresenter extends BasePresenter<IBooksStoreView> implements IBooksStorePresenter {

    public void getBookCatalogInfo(ObservableTransformer transformer, String key) {
        BooksStoreModel.getInstance().getBookCatalogInfo(transformer, key, this);
    }

    public void getBookContentInfo(ObservableTransformer transformer, String key, int catalogId,
                                   int pn, int rn) {
        BooksStoreModel.getInstance().getBookContentInfo(transformer, key, catalogId, pn, rn, this);
    }

    @Override
    public void onBookCatalogInfo(BookCatalogInfo bookCatalogInfo) {
        getView().onBookCatalogInfo(bookCatalogInfo);
    }

    @Override
    public void onBookContentInfo(BookContentInfo bookContentInfo) {
        getView().onBookContentInfo(bookContentInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
