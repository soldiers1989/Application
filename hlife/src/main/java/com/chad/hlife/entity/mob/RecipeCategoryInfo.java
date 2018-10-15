package com.chad.hlife.entity.mob;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜谱分类
 */
public class RecipeCategoryInfo implements Parcelable {

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

    protected RecipeCategoryInfo(Parcel in) {
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

    public static final Creator<RecipeCategoryInfo> CREATOR = new Creator<RecipeCategoryInfo>() {
        @Override
        public RecipeCategoryInfo createFromParcel(Parcel parcel) {
            return new RecipeCategoryInfo(parcel);
        }

        @Override
        public RecipeCategoryInfo[] newArray(int size) {
            return new RecipeCategoryInfo[size];
        }
    };

    public static class Result implements Parcelable {

        private CategoryInfo categoryInfo;
        private List<Child> childs;

        public CategoryInfo getCategoryInfo() {
            return categoryInfo;
        }

        public List<Child> getChilds() {
            return childs;
        }

        protected Result(Parcel in) {
            categoryInfo = (CategoryInfo) in.readValue(CategoryInfo.class.getClassLoader());
            childs = new ArrayList<>();
            in.readList(childs, Child.class.getClassLoader());
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeValue(categoryInfo);
            parcel.writeList(childs);
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

    public static class Child implements Parcelable {

        private CategoryInfo categoryInfo;
        private List<ChildCategoryInfo> childs;

        public CategoryInfo getCategoryInfo() {
            return categoryInfo;
        }

        public List<ChildCategoryInfo> getChilds() {
            return childs;
        }

        protected Child(Parcel in) {
            categoryInfo = (CategoryInfo) in.readValue(CategoryInfo.class.getClassLoader());
            childs = new ArrayList<>();
            in.readList(childs, ChildCategoryInfo.class.getClassLoader());
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeValue(categoryInfo);
            parcel.writeList(childs);
        }

        public static final Creator<Child> CREATOR = new Creator<Child>() {
            @Override
            public Child createFromParcel(Parcel parcel) {
                return new Child(parcel);
            }

            @Override
            public Child[] newArray(int size) {
                return new Child[size];
            }
        };
    }

    public static class ChildCategoryInfo implements Parcelable {

        private CategoryInfo categoryInfo;

        public CategoryInfo getCategoryInfo() {
            return categoryInfo;
        }

        protected ChildCategoryInfo(Parcel in) {
            categoryInfo = (CategoryInfo) in.readValue(CategoryInfo.class.getClassLoader());
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeValue(categoryInfo);
        }

        public static final Creator<ChildCategoryInfo> CREATOR = new Creator<ChildCategoryInfo>() {
            @Override
            public ChildCategoryInfo createFromParcel(Parcel parcel) {
                return new ChildCategoryInfo(parcel);
            }

            @Override
            public ChildCategoryInfo[] newArray(int size) {
                return new ChildCategoryInfo[size];
            }
        };
    }

    public static class CategoryInfo implements Parcelable {

        private String ctgId;
        private String parentId;
        private String name;

        public String getCtgId() {
            return ctgId;
        }

        public String getParentId() {
            return parentId;
        }

        public String getName() {
            return name;
        }

        protected CategoryInfo(Parcel in) {
            ctgId = in.readString();
            parentId = in.readString();
            name = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(ctgId);
            parcel.writeString(parentId);
            parcel.writeString(name);
        }

        public static final Creator<CategoryInfo> CREATOR = new Creator<CategoryInfo>() {
            @Override
            public CategoryInfo createFromParcel(Parcel parcel) {
                return new CategoryInfo(parcel);
            }

            @Override
            public CategoryInfo[] newArray(int size) {
                return new CategoryInfo[size];
            }
        };
    }
}
