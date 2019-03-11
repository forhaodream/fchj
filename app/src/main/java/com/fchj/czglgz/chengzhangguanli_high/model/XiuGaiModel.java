package com.fchj.czglgz.chengzhangguanli_high.model;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class XiuGaiModel {

    /**
     * code : 1
     * msg : 修改成功
     * data : {"id":1,"signname":"dfsdfr","signpassword":"sdaas","signdate":1523808000000,"username":"1","userphone":"1","age":1,"sex":0,"school":1,"grade":"1","classes":"1","address":"1","parentname":"1","headimgurl":null,"summary":null,"usertype":1,"grownum":null}
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
         * signname : dfsdfr
         * signpassword : sdaas
         * signdate : 1523808000000
         * username : 1
         * userphone : 1
         * age : 1
         * sex : 0
         * school : 1
         * grade : 1
         * classes : 1
         * address : 1
         * parentname : 1
         * headimgurl : null
         * summary : null
         * usertype : 1
         * grownum : null
         */

        private int id;
        private String signname;
        private String signpassword;
        private long signdate;
        private String username;
        private String userphone;
        private int age;
        private int sex;
        private int school;
        private String grade;
        private String classes;
        private String address;
        private String parentname;
        private Object headimgurl;
        private Object summary;
        private int usertype;
        private Object grownum;

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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserphone() {
            return userphone;
        }

        public void setUserphone(String userphone) {
            this.userphone = userphone;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getSchool() {
            return school;
        }

        public void setSchool(int school) {
            this.school = school;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getClasses() {
            return classes;
        }

        public void setClasses(String classes) {
            this.classes = classes;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getParentname() {
            return parentname;
        }

        public void setParentname(String parentname) {
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
    }
}
