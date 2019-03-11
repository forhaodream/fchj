package com.fchj.czglgz.chengzhangguanli_high.model;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public class VersionModel {

    /**
     * code : 1
     * msg : 查询成功
     * data : {"id":1,"serviceurl":"47.94.165.33","servicefileurl":"/home/HighShoolManager/","androidversion":"1.1.18.5.2","androidversionurl":"/HighShoolManager/file/growupmanagerhighschool.apk","iosversion":"","iosversionurl":""}
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
         * id : 1
         * serviceurl : 47.94.165.33
         * servicefileurl : /home/HighShoolManager/
         * androidversion : 1.1.18.5.2
         * androidversionurl : /HighShoolManager/file/growupmanagerhighschool.apk
         * iosversion :
         * iosversionurl :
         */

        private int id;
        private String serviceurl;
        private String servicefileurl;
        private String androidversion;
        private String androidversionurl;
        private String iosversion;
        private String iosversionurl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getServiceurl() {
            return serviceurl;
        }

        public void setServiceurl(String serviceurl) {
            this.serviceurl = serviceurl;
        }

        public String getServicefileurl() {
            return servicefileurl;
        }

        public void setServicefileurl(String servicefileurl) {
            this.servicefileurl = servicefileurl;
        }

        public String getAndroidversion() {
            return androidversion;
        }

        public void setAndroidversion(String androidversion) {
            this.androidversion = androidversion;
        }

        public String getAndroidversionurl() {
            return androidversionurl;
        }

        public void setAndroidversionurl(String androidversionurl) {
            this.androidversionurl = androidversionurl;
        }

        public String getIosversion() {
            return iosversion;
        }

        public void setIosversion(String iosversion) {
            this.iosversion = iosversion;
        }

        public String getIosversionurl() {
            return iosversionurl;
        }

        public void setIosversionurl(String iosversionurl) {
            this.iosversionurl = iosversionurl;
        }
    }
}
