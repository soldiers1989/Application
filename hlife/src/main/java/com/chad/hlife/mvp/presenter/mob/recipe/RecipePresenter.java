package com.chad.hlife.mvp.presenter.mob.recipe;

import com.chad.hlife.entity.mob.RecipeCategoryInfo;
import com.chad.hlife.entity.mob.RecipeDetailInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.mob.RecipeModel;
import com.chad.hlife.mvp.view.mob.IRecipeView;

import io.reactivex.ObservableTransformer;

public class RecipePresenter extends BasePresenter<IRecipeView> implements IRecipePresenter {

    public void getRecipeCategoryInfo(ObservableTransformer transformer, String key) {
        RecipeModel.getInstance().getRecipeCategoryInfo(transformer, key, this);
    }

    public void getRecipeDetailInfoByCId(ObservableTransformer transformer, String key, String id,
                                         int page, int size) {
        RecipeModel.getInstance().getRecipeDetailInfoByCId(transformer, key, id, page, size, this);
    }

    public void getRecipeDetailInfoByName(ObservableTransformer transformer, String key, String name,
                                          int page, int size) {
        RecipeModel.getInstance().getRecipeDetailInfoByName(transformer, key, name, page, size, this);
    }

    @Override
    public void onRecipeCategoryInfo(RecipeCategoryInfo recipeCategoryInfo) {
        getView().onRecipeCategoryInfo(recipeCategoryInfo);
    }

    @Override
    public void onRecipeDetailInfo(RecipeDetailInfo recipeDetailInfo) {
        getView().onRecipeDetailInfo(recipeDetailInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
