package com.fchj.czglgz.chengzhangguanli_high.major;

import java.util.List;

public class AboutCollegeModel {


    /**
     * code : 1
     * msg : 获取院校成功
     * data : [{"umid":526,"universityname":"三明学院","major":"学前教育","batch":"提前","coursetype":"本科专业","unty":null},{"umid":527,"universityname":"三明学院","major":"学前教育[面向三明]","batch":"提前","coursetype":"本科专业","unty":null},{"umid":1173,"universityname":"上海外国语大学贤达经济人文学院","major":"学前教育","batch":"二批","coursetype":"本科专业","unty":null},{"umid":1174,"universityname":"上海外国语大学贤达经济人文学院","major":"学前教育","batch":"本科批","coursetype":"本科专业","unty":null},{"umid":1576,"universityname":"上海师范大学","major":"学前教育","batch":"一批","coursetype":"本科专业","unty":null},{"umid":1577,"universityname":"上海师范大学","major":"学前教育","batch":"提前","coursetype":"本科专业","unty":null},{"umid":1578,"universityname":"上海师范大学","major":"学前教育","batch":"本科批","coursetype":"本科专业","unty":null},{"umid":1579,"universityname":"上海师范大学","major":"学前教育[师范]","batch":"一批","coursetype":"本科专业","unty":null},{"umid":1753,"universityname":"上海师范大学天华学院","major":"学前教育","batch":"二批","coursetype":"本科专业","unty":null},{"umid":1754,"universityname":"上海师范大学天华学院","major":"学前教育[师范]","batch":"二批","coursetype":"本科专业","unty":null}]
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
         * umid : 526
         * universityname : 三明学院
         * major : 学前教育
         * batch : 提前
         * coursetype : 本科专业
         * unty : null
         */

        private int umid;
        private String universityname;
        private String major;
        private String batch;
        private String coursetype;
        private Object unty;

        public int getUmid() {
            return umid;
        }

        public void setUmid(int umid) {
            this.umid = umid;
        }

        public String getUniversityname() {
            return universityname;
        }

        public void setUniversityname(String universityname) {
            this.universityname = universityname;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getBatch() {
            return batch;
        }

        public void setBatch(String batch) {
            this.batch = batch;
        }

        public String getCoursetype() {
            return coursetype;
        }

        public void setCoursetype(String coursetype) {
            this.coursetype = coursetype;
        }

        public Object getUnty() {
            return unty;
        }

        public void setUnty(Object unty) {
            this.unty = unty;
        }
    }
}
