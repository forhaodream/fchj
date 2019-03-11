package com.fchj.czglgz.chengzhangguanli_high.vocation;

public class VocaScModel {

    /**
     * code : 1
     * msg : 关注成功
     * data : {"vfid":5,"userId":122,"vctid":"VOT-256-INFO","voc":null}
     * value : null
     */

    private int code;
    private String msg;
    private DataBean data;
    private Object value;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static class DataBean {
        /**
         * vfid : 5
         * userId : 122
         * vctid : VOT-256-INFO
         * voc : null
         */

        private int vfid;
        private int userId;
        private String vctid;
        private Object voc;

        public int getVfid() {
            return vfid;
        }

        public void setVfid(int vfid) {
            this.vfid = vfid;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getVctid() {
            return vctid;
        }

        public void setVctid(String vctid) {
            this.vctid = vctid;
        }

        public Object getVoc() {
            return voc;
        }

        public void setVoc(Object voc) {
            this.voc = voc;
        }
    }
}
