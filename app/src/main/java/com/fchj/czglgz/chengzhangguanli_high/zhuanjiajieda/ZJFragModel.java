package com.fchj.czglgz.chengzhangguanli_high.zhuanjiajieda;

import com.fchj.czglgz.chengzhangguanli_high.model.ZhuanJiaInfoModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class ZJFragModel {

    /**
     * code : 1
     * msg : 获取专家列表成功
     * data : [{"id":16,"signname":"13889807245","signpassword":"724521","signdate":1524452627000,"username":" 刘老师","userphone":"13889885021","age":29,"sex":1,"school":1,"grade":"计算机科学与技术","classes":null,"address":"辽宁省沈阳市","parentname":null,"headimgurl":null,"summary":null,"usertype":0,"grownum":null},{"id":17,"signname":"144488748848","signpassword":"4885587","signdate":1524454186000,"username":"48848","userphone":"54155151","age":74,"sex":0,"school":null,"grade":"烤白薯","classes":null,"address":"114冯小刚的风格","parentname":null,"headimgurl":null,"summary":null,"usertype":0,"grownum":null},{"id":18,"signname":"3271221237","signpassword":"4885587","signdate":1524454220000,"username":"发发发","userphone":"54155151","age":74,"sex":0,"school":null,"grade":"烤白薯","classes":null,"address":"114冯小刚的风格","parentname":null,"headimgurl":null,"summary":null,"usertype":0,"grownum":null},{"id":19,"signname":"88544587","signpassword":"4885587","signdate":1524454229000,"username":"发发发","userphone":"54155151","age":74,"sex":0,"school":null,"grade":"烤白薯","classes":null,"address":"114冯小刚的风格","parentname":null,"headimgurl":null,"summary":null,"usertype":0,"grownum":null},{"id":20,"signname":"88544","signpassword":"4885587","signdate":1524454235000,"username":"发发发","userphone":"54155151","age":74,"sex":0,"school":null,"grade":"烤白薯","classes":null,"address":"114冯小刚的风格","parentname":null,"headimgurl":null,"summary":null,"usertype":0,"grownum":null}]
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
         * id : 16
         * signname : 13889807245
         * signpassword : 724521
         * signdate : 1524452627000
         * username :  刘老师
         * userphone : 13889885021
         * age : 29
         * sex : 1
         * school : 1
         * grade : 计算机科学与技术
         * classes : null
         * address : 辽宁省沈阳市
         * parentname : null
         * headimgurl : null
         * summary : null
         * usertype : 0
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
        private Object classes;
        private String address;
        private Object parentname;
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
//    /**
//     * code : 1
//     * msg : 查询成功
//     * data : {"id":4,"signname":"fchj001","signpassword":"81BC1463FD7206EE60B802729683C20D","signdate":1524640487000,"username":"邱洪广","userphone":"","age":1,"sex":1,"school":3,"grade":"教育学","classes":null,"address":"","parentname":null,"headimgurl":"image/userheadimg/201804251514461688.png","summary":"12年的一线教育教学经历\r\n5年的教育局教育管理经验\r\n中华人民共和国第十二届全运会全运村管理服务经历\r\n沈阳市优秀教师、沈阳市优秀班主任、辽宁省优秀心理咨询师、东北师范大学理想信息技术研究院高级生涯规划师\r\n","usertype":2,"grownum":null,"schl":{"id":3,"schoolname":"沈阳师范大学","schooltype":1,"schoolsummary":"","schoolofficial":"","schoolforareas":"210113"}}
//     * value : null
//     */
//
//    private int code;
//    private String msg;
//    private ZhuanJiaInfoModel.DataBean data;
//    private Object value;
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public ZhuanJiaInfoModel.DataBean getData() {
//        return data;
//    }
//
//    public void setData(ZhuanJiaInfoModel.DataBean data) {
//        this.data = data;
//    }
//
//    public Object getValue() {
//        return value;
//    }
//
//    public void setValue(Object value) {
//        this.value = value;
//    }
//
//    public static class DataBean {
//        /**
//         * id : 4
//         * signname : fchj001
//         * signpassword : 81BC1463FD7206EE60B802729683C20D
//         * signdate : 1524640487000
//         * username : 邱洪广
//         * userphone :
//         * age : 1
//         * sex : 1
//         * school : 3
//         * grade : 教育学
//         * classes : null
//         * address :
//         * parentname : null
//         * headimgurl : image/userheadimg/201804251514461688.png
//         * summary : 12年的一线教育教学经历
//         * 5年的教育局教育管理经验
//         * 中华人民共和国第十二届全运会全运村管理服务经历
//         * 沈阳市优秀教师、沈阳市优秀班主任、辽宁省优秀心理咨询师、东北师范大学理想信息技术研究院高级生涯规划师
//         * <p>
//         * usertype : 2
//         * grownum : null
//         * schl : {"id":3,"schoolname":"沈阳师范大学","schooltype":1,"schoolsummary":"","schoolofficial":"","schoolforareas":"210113"}
//         */
//
//        private int id;
//        private String signname;
//        private String signpassword;
//        private long signdate;
//        private String username;
//        private String userphone;
//        private int age;
//        private int sex;
//        private int school;
//        private String grade;
//        private Object classes;
//        private String address;
//        private Object parentname;
//        private String headimgurl;
//        private String summary;
//        private int usertype;
//        private Object grownum;
//        private ZhuanJiaInfoModel.DataBean.SchlBean schl;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getSignname() {
//            return signname;
//        }
//
//        public void setSignname(String signname) {
//            this.signname = signname;
//        }
//
//        public String getSignpassword() {
//            return signpassword;
//        }
//
//        public void setSignpassword(String signpassword) {
//            this.signpassword = signpassword;
//        }
//
//        public long getSigndate() {
//            return signdate;
//        }
//
//        public void setSigndate(long signdate) {
//            this.signdate = signdate;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public String getUserphone() {
//            return userphone;
//        }
//
//        public void setUserphone(String userphone) {
//            this.userphone = userphone;
//        }
//
//        public int getAge() {
//            return age;
//        }
//
//        public void setAge(int age) {
//            this.age = age;
//        }
//
//        public int getSex() {
//            return sex;
//        }
//
//        public void setSex(int sex) {
//            this.sex = sex;
//        }
//
//        public int getSchool() {
//            return school;
//        }
//
//        public void setSchool(int school) {
//            this.school = school;
//        }
//
//        public String getGrade() {
//            return grade;
//        }
//
//        public void setGrade(String grade) {
//            this.grade = grade;
//        }
//
//        public Object getClasses() {
//            return classes;
//        }
//
//        public void setClasses(Object classes) {
//            this.classes = classes;
//        }
//
//        public String getAddress() {
//            return address;
//        }
//
//        public void setAddress(String address) {
//            this.address = address;
//        }
//
//        public Object getParentname() {
//            return parentname;
//        }
//
//        public void setParentname(Object parentname) {
//            this.parentname = parentname;
//        }
//
//        public String getHeadimgurl() {
//            return headimgurl;
//        }
//
//        public void setHeadimgurl(String headimgurl) {
//            this.headimgurl = headimgurl;
//        }
//
//        public String getSummary() {
//            return summary;
//        }
//
//        public void setSummary(String summary) {
//            this.summary = summary;
//        }
//
//        public int getUsertype() {
//            return usertype;
//        }
//
//        public void setUsertype(int usertype) {
//            this.usertype = usertype;
//        }
//
//        public Object getGrownum() {
//            return grownum;
//        }
//
//        public void setGrownum(Object grownum) {
//            this.grownum = grownum;
//        }
//
//        public ZhuanJiaInfoModel.DataBean.SchlBean getSchl() {
//            return schl;
//        }
//
//        public void setSchl(ZhuanJiaInfoModel.DataBean.SchlBean schl) {
//            this.schl = schl;
//        }
//
//        public static class SchlBean {
//            /**
//             * id : 3
//             * schoolname : 沈阳师范大学
//             * schooltype : 1
//             * schoolsummary :
//             * schoolofficial :
//             * schoolforareas : 210113
//             */
//
//            private int id;
//            private String schoolname;
//            private int schooltype;
//            private String schoolsummary;
//            private String schoolofficial;
//            private String schoolforareas;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getSchoolname() {
//                return schoolname;
//            }
//
//            public void setSchoolname(String schoolname) {
//                this.schoolname = schoolname;
//            }
//
//            public int getSchooltype() {
//                return schooltype;
//            }
//
//            public void setSchooltype(int schooltype) {
//                this.schooltype = schooltype;
//            }
//
//            public String getSchoolsummary() {
//                return schoolsummary;
//            }
//
//            public void setSchoolsummary(String schoolsummary) {
//                this.schoolsummary = schoolsummary;
//            }
//
//            public String getSchoolofficial() {
//                return schoolofficial;
//            }
//
//            public void setSchoolofficial(String schoolofficial) {
//                this.schoolofficial = schoolofficial;
//            }
//
//            public String getSchoolforareas() {
//                return schoolforareas;
//            }
//
//            public void setSchoolforareas(String schoolforareas) {
//                this.schoolforareas = schoolforareas;
//            }
//        }
//    }
}
