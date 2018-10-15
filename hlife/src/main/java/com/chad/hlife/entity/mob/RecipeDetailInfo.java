package com.chad.hlife.entity.mob;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜谱详情
 */
public class RecipeDetailInfo implements Parcelable {

    private String msg;
    private String retCode;
    private Result result;

    public String getMsg() {
        return msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public Result getResult() {
        return result;
    }

    protected RecipeDetailInfo(Parcel in) {
        msg = in.readString();
        retCode = in.readString();
        result = (Result) in.readValue(Result.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(msg);
        parcel.writeString(retCode);
        parcel.writeValue(result);
    }

    public static final Creator<RecipeDetailInfo> CREATOR = new Creator<RecipeDetailInfo>() {
        @Override
        public RecipeDetailInfo createFromParcel(Parcel parcel) {
            return new RecipeDetailInfo(parcel);
        }

        @Override
        public RecipeDetailInfo[] newArray(int size) {
            return new RecipeDetailInfo[size];
        }
    };

    public static class Result implements Parcelable {

        private int curPage;
        private int total;
        private List<Recipes> list;

        public int getCurPage() {
            return curPage;
        }

        public int getTotal() {
            return total;
        }

        public List<Recipes> getList() {
            return list;
        }

        protected Result(Parcel in) {
            curPage = in.readInt();
            total = in.readInt();
            list = new ArrayList<>();
            in.readList(list, Recipes.class.getClassLoader());
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(curPage);
            parcel.writeInt(total);
            parcel.writeList(list);
        }

        public static final Creator<Result> CREATOR = new Creator<Result>() {
            @Override
            public Result createFromParcel(Parcel parcel) {
                return new Result(parcel);
            }

            @Override
            public Result[] newArray(int size) {
                return new Result[size];
            }
        };
    }

    public static class Recipes implements Parcelable {

        private List<String> ctgIds;
        private String menuId;
        private String ctgTitles;
        private String name;
        private String thumbnail;
//        private Recipe recipe;

        public List<String> getCtgIds() {
            return ctgIds;
        }

        public String getMenuId() {
            return menuId;
        }

        public String getCtgTitles() {
            return ctgTitles;
        }

        public String getName() {
            return name;
        }

        public String getThumbnail() {
            return thumbnail;
        }

//        public Recipe getRecipe() {
//            return recipe;
//        }

        protected Recipes(Parcel in) {
            ctgIds = new ArrayList<>();
            in.readList(ctgIds, String.class.getClassLoader());
            menuId = in.readString();
            ctgTitles = in.readString();
            name = in.readString();
            thumbnail = in.readString();
//            recipe = (Recipe) in.readValue(Recipe.class.getClassLoader());
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeList(ctgIds);
            parcel.writeString(menuId);
            parcel.writeString(ctgTitles);
            parcel.writeString(name);
            parcel.writeString(thumbnail);
//            parcel.writeValue(recipe);
        }

        public static final Creator<Recipes> CREATOR = new Creator<Recipes>() {
            @Override
            public Recipes createFromParcel(Parcel parcel) {
                return new Recipes(parcel);
            }

            @Override
            public Recipes[] newArray(int size) {
                return new Recipes[size];
            }
        };
    }

    public static class Recipe implements Parcelable {

        private String title;
        private String sumary;
        private String img;
//        private List<String> ingredients;
//        private Method method;

        public String getTitle() {
            return title;
        }

        public String getSumary() {
            return sumary;
        }

        public String getImg() {
            return img;
        }

//        public List<String> getIngredients() {
//            return ingredients;
//        }

//        public Method getMethod() {
//            return method;
//        }

        protected Recipe(Parcel in) {
            title = in.readString();
            sumary = in.readString();
            img = in.readString();
//            ingredients = new ArrayList<>();
//            in.readList(ingredients, String.class.getClassLoader());
//            method = (Method) in.readValue(Method.class.getClassLoader());
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(title);
            parcel.writeString(sumary);
            parcel.writeString(img);
//            parcel.writeList(ingredients);
//            parcel.writeValue(method);
        }

        public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
            @Override
            public Recipe createFromParcel(Parcel parcel) {
                return new Recipe(parcel);
            }

            @Override
            public Recipe[] newArray(int size) {
                return new Recipe[size];
            }
        };
    }

    public static class Method implements Parcelable {

        private List<String> methods;

        public List<String> getMethods() {
            return methods;
        }

        protected Method(Parcel in) {
            methods = new ArrayList<>();
            in.readList(methods, String.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(methods);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Method> CREATOR = new Creator<Method>() {
            @Override
            public Method createFromParcel(Parcel in) {
                return new Method(in);
            }

            @Override
            public Method[] newArray(int size) {
                return new Method[size];
            }
        };
    }
}
