package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import java.util.List;

public class SubListModel {

    private int code;
    private String msg;
    private int value;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * mkid : 1
         * kindname : 工学
         * kindsnum : 2886
         * kindpercent : 21.59
         */

        private int mkid;
        private String kindname;
        private int kindsnum;
        private String kindpercent;

        public int getMkid() {
            return mkid;
        }

        public void setMkid(int mkid) {
            this.mkid = mkid;
        }

        public String getKindname() {
            return kindname;
        }

        public void setKindname(String kindname) {
            this.kindname = kindname;
        }

        public int getKindsnum() {
            return kindsnum;
        }

        public void setKindsnum(int kindsnum) {
            this.kindsnum = kindsnum;
        }

        public String getKindpercent() {
            return kindpercent;
        }

        public void setKindpercent(String kindpercent) {
            this.kindpercent = kindpercent;
        }
    }
}
