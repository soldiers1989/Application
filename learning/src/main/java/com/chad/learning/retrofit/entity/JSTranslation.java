package com.chad.learning.retrofit.entity;

public class JSTranslation {

    private int status; // 请求成功时取1

    private Content content; // 内容信息

    public static class Content {
        private String from;    //原文内容类型
        private String to;  // 译文内容类型
        private String vendor;  // 来源平台
        private String out; // 译文内容
        private int errNo;  // 请求成功时取0

        public void setFrom(String from) {
            this.from = from;
        }

        public String getFrom() {
            return from;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getTo() {
            return to;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getVendor() {
            return vendor;
        }

        public void setOut(String out) {
            this.out = out;
        }

        public String getOut() {
            return out;
        }

        public void setErrNo(int errNo) {
            this.errNo = errNo;
        }

        public int getErrNo() {
            return errNo;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Content getContent() {
        return content;
    }
}
