package com.chad.hlife.mvp.view.mob;

import com.chad.hlife.entity.mob.RecipeCategoryInfo;
import com.chad.hlife.entity.mob.RecipeDetailInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface IRecipeView extends IBaseView {

    void onRecipeCategoryInfo(RecipeCategoryInfo recipeCategoryInfo);

    void onRecipeDetailInfo(RecipeDetailInfo recipeDetailInfo);
}
