package com.fchj.czglgz.chengzhangguanli_high.model;

public class WxLoginModel {

    /**
     * code : 1
     * msg : 注册登录成功
     * data : {"id":30,"openid":"987654321","unionid":"987654321","fixingId":null,"signname":null,"signpassword":null,"signdate":1527064561889,"username":null,"userphone":null,"age":null,"sex":null,"school":null,"grade":null,"classes":null,"address":null,"parentname":null,"headimgurl":null,"summary":null,"usertype":1,"grownum":null,"schl":null}
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
         * id : 30
         * openid : 987654321
         * unionid : 987654321
         * fixingId : null
         * signname : null
         * signpassword : null
         * signdate : 1527064561889
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
         * grownum : null
         * schl : null
         */

        private int id;
        private String openid;
        private String unionid;
        private Object fixingId;
        private Object signname;
        private Object signpassword;
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
        private Object grownum;
        private Object schl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getUnionid() {
            return unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid;
        }

        public Object getFixingId() {
            return fixingId;
        }

        public void setFixingId(Object fixingId) {
            this.fixingId = fixingId;
        }

        public Object getSignname() {
            return signname;
        }

        public void setSignname(Object signname) {
            this.signname = signname;
        }

        public Object getSignpassword() {
            return signpassword;
        }

        public void setSignpassword(Object signpassword) {
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

        public Object getGrownum() {
            return grownum;
        }

        public void setGrownum(Object grownum) {
            this.grownum = grownum;
        }

        public Object getSchl() {
            return schl;
        }

        public void setSchl(Object schl) {
            this.schl = schl;
        }
    }
}
