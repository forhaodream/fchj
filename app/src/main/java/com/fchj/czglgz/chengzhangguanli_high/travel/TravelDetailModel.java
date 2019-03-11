package com.fchj.czglgz.chengzhangguanli_high.travel;

import java.util.List;

public class TravelDetailModel {

    /**
     * code : 1
     * msg : 查询成功
     * data : [{"id":1,"ornum":1,"imgurl":"/HighShoolManager/image/banner/20180427121539318.png","outurl":null},{"id":2,"ornum":2,"imgurl":"/HighShoolManager/image/banner/201805081501072915.jpg","outurl":null},{"id":3,"ornum":3,"imgurl":"/HighShoolManager/image/banner/201806121006496655.jpg","outurl":"http://47.94.165.33:8080/HSGUM/AppDownLoad.jsp"}]
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
         * imgurl : /HighShoolManager/image/banner/20180427121539318.png
         * outurl : null
         */

        private int id;
        private int ornum;
        private String imgurl;
        private Object outurl;

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

        public Object getOuturl() {
            return outurl;
        }

        public void setOuturl(Object outurl) {
            this.outurl = outurl;
        }
    }
}
