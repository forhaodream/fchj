package com.fchj.czglgz.chengzhangguanli_high.model;

import java.util.List;

/**
 * Created by Administrator on 2018/4/20 0020.
 */

public class ShiModel {

    /**
     * code : 1
     * msg : 获取市级数据成功
     * data : [{"id":1,"cityid":"110100","city":"市辖区","provinceid":"110000"},{"id":2,"cityid":"110200","city":"县","provinceid":"110000"}]
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
         * cityid : 110100
         * city : 市辖区
         * provinceid : 110000
         */

        private int id;
        private String cityid;
        private String city;
        private String provinceid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getProvinceid() {
            return provinceid;
        }

        public void setProvinceid(String provinceid) {
            this.provinceid = provinceid;
        }
    }
}
