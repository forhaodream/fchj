package com.fchj.czglgz.chengzhangguanli_high.model;

import java.util.List;

/**
 * Created by Administrator on 2018/4/20 0020.
 */

public class SchoolModel {

    /**
     * code : 1
     * msg : 获取学校成功
     * data : [{"id":1,"schoolname":"沈阳二中","schooltype":0,"schoolsummary":"","schoolofficial":null,"schoolforareas":"210103"},{"id":2,"schoolname":"沈阳四中","schooltype":0,"schoolsummary":"","schoolofficial":null,"schoolforareas":"210103"},{"id":4,"schoolname":"沈阳市实验中学","schooltype":0,"schoolsummary":"","schoolofficial":null,"schoolforareas":"210103"},{"id":5,"schoolname":"沈阳市第十五中学","schooltype":0,"schoolsummary":"发上月的是的任何 但是有的 大少爷的阿萨德有地位啊实打实的","schoolofficial":null,"schoolforareas":"210104"}]
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
         * schoolname : 沈阳二中
         * schooltype : 0
         * schoolsummary :
         * schoolofficial : null
         * schoolforareas : 210103
         */

        private int id;
        private String schoolname;
        private int schooltype;
        private String schoolsummary;
        private Object schoolofficial;
        private String schoolforareas;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSchoolname() {
            return schoolname;
        }

        public void setSchoolname(String schoolname) {
            this.schoolname = schoolname;
        }

        public int getSchooltype() {
            return schooltype;
        }

        public void setSchooltype(int schooltype) {
            this.schooltype = schooltype;
        }

        public String getSchoolsummary() {
            return schoolsummary;
        }

        public void setSchoolsummary(String schoolsummary) {
            this.schoolsummary = schoolsummary;
        }

        public Object getSchoolofficial() {
            return schoolofficial;
        }

        public void setSchoolofficial(Object schoolofficial) {
            this.schoolofficial = schoolofficial;
        }

        public String getSchoolforareas() {
            return schoolforareas;
        }

        public void setSchoolforareas(String schoolforareas) {
            this.schoolforareas = schoolforareas;
        }
    }
}
