package com.fchj.czglgz.chengzhangguanli_high.model;

/**
 * Created by Administrator on 2018/5/21 0021.
 */

public class XiuGaiPsdModel {

    /**
     * code : 1
     * msg : 修改成功
     * data : {"id":2,"signname":"13889885021","signpassword":"123456","signdate":1523808000000,"username":"撒的地方","userphone":"123444848","age":12,"sex":null,"school":1,"grade":"第三方","classes":"大法师地方","address":"法师的阿放放风","parentname":"撒旦","headimgurl":null,"summary":null,"usertype":1}
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
         * id : 2
         * signname : 13889885021
         * signpassword : 123456
         * signdate : 1523808000000
         * username : 撒的地方
         * userphone : 123444848
         * age : 12
         * sex : null
         * school : 1
         * grade : 第三方
         * classes : 大法师地方
         * address : 法师的阿放放风
         * parentname : 撒旦
         * headimgurl : null
         * summary : null
         * usertype : 1
         */

        private int id;
        private String signname;
        private String signpassword;
        private long signdate;
        private String username;
        private String userphone;
        private int age;
        private Object sex;
        private int school;
        private String grade;
        private String classes;
        private String address;
        private String parentname;
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

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
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
    }
}
