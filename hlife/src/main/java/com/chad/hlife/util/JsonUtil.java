package com.chad.hlife.util;

import android.text.TextUtils;

import com.chad.hlife.entity.mob.RecipeDetailInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class JsonUtil {

    public static List<String> formatList(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<String>>() {
        }.getType());
    }

    public static List<RecipeDetailInfo.Method> formatRecipeMethod(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<RecipeDetailInfo.Method>>() {
        }.getType());
    }
}
