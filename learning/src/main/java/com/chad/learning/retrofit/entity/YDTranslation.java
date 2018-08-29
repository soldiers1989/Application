package com.chad.learning.retrofit.entity;

import java.util.List;

public class YDTranslation {

    private String type;
    private int errorCode;
    private long elapsedTime;
    private List<List<TranslateResultBean>> translateResult;

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

    public void setTranslateResult(List<List<TranslateResultBean>> translateResult) {
        this.translateResult = translateResult;
    }

    public List<List<TranslateResultBean>> getTranslateResult() {
        return translateResult;
    }

    public static class TranslateResultBean {

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
