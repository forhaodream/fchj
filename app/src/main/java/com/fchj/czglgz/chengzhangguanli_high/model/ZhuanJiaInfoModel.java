package com.fchj.czglgz.chengzhangguanli_high.model;

/**
 * Created by Administrator on 2018/4/25 0025.
 */

public class ZhuanJiaInfoModel {

    /**
     * code : 1
     * msg : 查询成功
     * data : {"id":4,"signname":"fchj001","signpassword":"81BC1463FD7206EE60B802729683C20D","signdate":1524640487000,"username":"邱洪广","userphone":"","age":1,"sex":1,"school":3,"grade":"教育学","classes":null,"address":"","parentname":null,"headimgurl":"image/userheadimg/201804251514461688.png","summary":"12年的一线教育教学经历\r\n5年的教育局教育管理经验\r\n中华人民共和国第十二届全运会全运村管理服务经历\r\n沈阳市优秀教师、沈阳市优秀班主任、辽宁省优秀心理咨询师、东北师范大学理想信息技术研究院高级生涯规划师\r\n","usertype":2,"grownum":null,"schl":{"id":3,"schoolname":"沈阳师范大学","schooltype":1,"schoolsummary":"","schoolofficial":"","schoolforareas":"210113"}}
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
         * id : 4
         * signname : fchj001
         * signpassword : 81BC1463FD7206EE60B802729683C20D
         * signdate : 1524640487000
         * username : 邱洪广
         * userphone :
         * age : 1
         * sex : 1
         * school : 3
         * grade : 教育学
         * classes : null
         * address :
         * parentname : null
         * headimgurl : image/userheadimg/201804251514461688.png
         * summary : 12年的一线教育教学经历
         5年的教育局教育管理经验
         中华人民共和国第十二届全运会全运村管理服务经历
         沈阳市优秀教师、沈阳市优秀班主任、辽宁省优秀心理咨询师、东北师范大学理想信息技术研究院高级生涯规划师

         * usertype : 2
         * grownum : null
         * schl : {"id":3,"schoolname":"沈阳师范大学","schooltype":1,"schoolsummary":"","schoolofficial":"","schoolforareas":"210113"}
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
        private Object classes;
        private String address;
        private Object parentname;
        private String headimgurl;
        private String summary;
        private int usertype;
        private Object grownum;
        private SchlBean schl;

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

        public Object getClasses() {
            return classes;
        }

        public void setClasses(Object classes) {
            this.classes = classes;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getParentname() {
            return parentname;
        }

        public void setParentname(Object parentname) {
            this.parentname = parentname;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
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

        public SchlBean getSchl() {
            return schl;
        }

        public void setSchl(SchlBean schl) {
            this.schl = schl;
        }

        public static class SchlBean {
            /**
             * id : 3
             * schoolname : 沈阳师范大学
             * schooltype : 1
             * schoolsummary :
             * schoolofficial :
             * schoolforareas : 210113
             */

            private int id;
            private String schoolname;
            private int schooltype;
            private String schoolsummary;
            private String schoolofficial;
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

            public String getSchoolofficial() {
                return schoolofficial;
            }

            public void setSchoolofficial(String schoolofficial) {
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
}
