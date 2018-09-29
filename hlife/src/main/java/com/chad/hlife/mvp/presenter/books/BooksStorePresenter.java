package com.chad.hlife.mvp.presenter.books;

import com.chad.hlife.entity.juhe.BookCatalogInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.BooksStoreModel;
import com.chad.hlife.mvp.view.IBooksStoreView;

import io.reactivex.ObservableTransformer;

public class BooksStorePresenter extends BasePresenter<IBooksStoreView> implements IBooksStorePresenter {

    public void getBookCatalogInfo(ObservableTransformer transformer, String key) {
        BooksStoreModel.getInstance().getBookCatalogInfo(transformer, key, this);
    }

    @Override
    public void onBookCatalogInfo(BookCatalogInfo bookCatalogInfo) {
        getView().onBookCatalogInfo(bookCatalogInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
