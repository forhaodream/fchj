package com.fchj.czglgz.chengzhangguanli_high.model;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class LoginModel {

    /**
     * code : 1
     * msg : 登录成功
     * data : {"id":7,"signname":"07","signpassword":"80","signdate":1523862547120,"username":null,"userphone":null,"age":null,"sex":null,"school":null,"grade":null,"classes":null,"address":null,"parentname":null,"headimgurl":null,"summary":null,"usertype":1}
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
         * id : 7
         * signname : 07
         * signpassword : 80
         * signdate : 1523862547120
         * username : null
         * userphone : null
         * age : null
         * sex : null
         * school : null
         * grade : null
         * classes : null
         * address : null
         * parentname : null
         * headimgurl : null
         * summary : null
         * usertype : 1
         */

        private int id;
        private String signname;
        private String signpassword;
        private long signdate;
        private Object username;
        private Object userphone;
        private Object age;
        private Object sex;
        private Object school;
        private Object grade;
        private Object classes;
        private Object address;
        private Object parentname;
        private Object headimgurl;
        private Object summary;
        private int usertype;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSignname() {
            return signname;
        }

        public void setSignname(String signname) {
            this.signname = signname;
        }

        public String getSignpassword() {
            return signpassword;
        }

        public void setSignpassword(String signpassword) {
            this.signpassword = signpassword;
        }

        public long getSigndate() {
            return signdate;
        }

        public void setSigndate(long signdate) {
            this.signdate = signdate;
        }

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
            this.username = username;
        }

        public Object getUserphone() {
            return userphone;
        }

        public void setUserphone(Object userphone) {
            this.userphone = userphone;
        }

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getSchool() {
            return school;
        }

        public void setSchool(Object school) {
            this.school = school;
        }

        public Object getGrade() {
            return grade;
        }

        public void setGrade(Object grade) {
            this.grade = grade;
        }

        public Object getClasses() {
            return classes;
        }

        public void setClasses(Object classes) {
            this.classes = classes;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getParentname() {
            return parentname;
        }

        public void setParentname(Object parentname) {
            this.parentname = parentname;
        }

        public Object getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(Object headimgurl) {
            this.headimgurl = headimgurl;
        }

        public Object getSummary() {
            return summary;
        }

        public void setSummary(Object summary) {
            this.summary = summary;
        }

        public int getUsertype() {
            return usertype;
        }

        public void setUsertype(int usertype) {
            this.usertype = usertype;
        }
    }
}
