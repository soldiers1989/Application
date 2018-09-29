package com.chad.hlife.mvp.model;

import com.chad.hlife.entity.juhe.BookCatalogInfo;
import com.chad.hlife.entity.juhe.BookContentInfo;
import com.chad.hlife.mvp.presenter.books.IBooksStorePresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class BooksStoreModel {

    private static volatile BooksStoreModel mBooksStoreModel = null;

    public static BooksStoreModel getInstance() {
        synchronized (BooksStoreModel.class) {
            if (mBooksStoreModel == null) {
                mBooksStoreModel = new BooksStoreModel();
            }
        }
        return mBooksStoreModel;
    }

    private BooksStoreModel() {
    }

    public void getBookCatalogInfo(ObservableTransformer transformer, String key,
                                   IBooksStorePresenter booksStorePresenter) {
        HLifeRetrofit.getBookCatalogInfo(key)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> booksStorePresenter.onBookCatalogInfo((BookCatalogInfo) o),
                        throwable -> booksStorePresenter.onError(throwable));
    }

    public void getBookContentInfo(ObservableTransformer transformer, String key, String catalogId,
                                   String pn, String rn, IBooksStorePresenter booksStorePresenter) {
        HLifeRetrofit.getBookContentInfo(key, catalogId, pn, rn)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> booksStorePresenter.onBookContentInfo((BookContentInfo) o),
                        throwable -> booksStorePresenter.onError(throwable));
    }
}
