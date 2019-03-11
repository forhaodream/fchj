package com.fchj.czglgz.chengzhangguanli_high.model;

import java.util.List;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class BannerModel {


    /**
     * code : 1
     * msg : 查询成功
     * data : [{"id":1,"ornum":1,"imgurl":"image/banner/banner1.png"},{"id":2,"ornum":2,"imgurl":"image/banner/banner2.jpg"}]
     * value : null
     */

    private int code;
    private String msg;
    private Object value;
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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
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
         * id : 1
         * ornum : 1
         * imgurl : image/banner/banner1.png
         */

        private int id;
        private int ornum;
        private String imgurl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrnum() {
            return ornum;
        }

        public void setOrnum(int ornum) {
            this.ornum = ornum;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }
}
