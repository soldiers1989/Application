package com.chad.learning.retrofit.entity;

import java.util.List;

public class YDTranslation {

    private String type;
    private int errorCode;
    private long elapsedTime;
    private List<List<TranslationResultBean>> lists;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setLists(List<List<TranslationResultBean>> lists) {
        this.lists = lists;
    }

    public List<List<TranslationResultBean>> getLists() {
        return lists;
    }

    public static class TranslationResultBean {

        private String src;
        private String tgt;

        public void setSrc(String src) {
            this.src = src;
        }

        public String getSrc() {
            return src;
        }

        public void setTgt(String tgt) {
            this.tgt = tgt;
        }

        public String getTgt() {
            return tgt;
        }
    }
}
