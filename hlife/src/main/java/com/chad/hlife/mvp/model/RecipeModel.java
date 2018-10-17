package com.chad.hlife.mvp.model;

import com.chad.hlife.entity.mob.RecipeCategoryInfo;
import com.chad.hlife.entity.mob.RecipeDetailInfo;
import com.chad.hlife.mvp.presenter.recipe.IRecipePresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class RecipeModel {

    private static volatile RecipeModel mRecipeModel = null;

    public static RecipeModel getInstance() {
        synchronized (RecipeModel.class) {
            if (mRecipeModel == null) {
                mRecipeModel = new RecipeModel();
            }
        }
        return mRecipeModel;
    }

    private RecipeModel() {
    }

    public void getRecipeCategoryInfo(ObservableTransformer transformer, String key, IRecipePresenter recipePresenter) {
        HLifeRetrofit.getRecipeCategoryInfo(key)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> recipePresenter.onRecipeCategoryInfo((RecipeCategoryInfo) o),
                        throwable -> recipePresenter.onError(throwable));
    }

    public void getRecipeDetailInfoByCId(ObservableTransformer transformer, String key, String id, int page, int size,
                                         IRecipePresenter recipePresenter) {
        HLifeRetrofit.getRecipeDetailInfoByCId(key, id, page, size)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> recipePresenter.onRecipeDetailInfo((RecipeDetailInfo) o),
                        throwable -> recipePresenter.onError(throwable));
    }

    public void getRecipeDetailInfoByName(ObservableTransformer transformer, String key, String name, int page, int size,
                                          IRecipePresenter recipePresenter) {
        HLifeRetrofit.getRecipeDetailInfoByName(key, name, page, size)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> recipePresenter.onRecipeDetailInfo((RecipeDetailInfo) o),
                        throwable -> recipePresenter.onError(throwable));
    }
}
