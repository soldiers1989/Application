package com.chad.learning.rxjava.network.polling.entity;

public class JSTranslation {

    private int status;

    private Content content;

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

    public static class Content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int err_no;

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

        public void setErr_no(int err_no) {
            this.err_no = err_no;
        }

        public int getErr_no() {
            return err_no;
        }
    }
}
