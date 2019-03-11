package com.fchj.czglgz.chengzhangguanli_high.jiazhangketang;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class JZDetailModel  {


    /**
     * code : 1
     * msg : 获取家长课堂成功
     * data : {"id":2,"classname":"孩子沉迷手机没节制？学会这几招，一治一个准！早看早受益","classpreviewimageurl":"/HighShoolManager/image/parentclass/201804271245003065.jpg","isinc":0,"classcontenttexturl":"https://mp.weixin.qq.com/s/G1wBnxxWCIEe94dd5TPvKQ","classcontenttext":"据了解患者平日里最大的爱好就是打手机游戏，甚至痴迷到废寝忘食的地步。平时放假时，她一天能玩7、8个小时，从早上一睁眼就玩一整天，经常吃饭、睡觉都顾不上，晚上也常常玩到凌晨。","classcontentvoidurl":null,"uploadtime":1524804300000,"classtype":1,"viewingtimes":0,"userId":1,"thumbsup":0,"noruser":{"id":1,"signname":"13889885021","signpassword":"BB10D6D675CBFBF3BD39B0971A9DD640","signdate":1524556577000,"username":"方成鸿基","userphone":"","age":20,"sex":0,"school":90000,"grade":"","classes":"","address":"","parentname":"方成鸿基","headimgurl":"/HighShoolManager/image/userheadimg/201804281509242072.jpeg","summary":null,"usertype":10,"grownum":null,"schl":null}}
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
         * classname : 孩子沉迷手机没节制？学会这几招，一治一个准！早看早受益
         * classpreviewimageurl : /HighShoolManager/image/parentclass/201804271245003065.jpg
         * isinc : 0
         * classcontenttexturl : https://mp.weixin.qq.com/s/G1wBnxxWCIEe94dd5TPvKQ
         * classcontenttext : 据了解患者平日里最大的爱好就是打手机游戏，甚至痴迷到废寝忘食的地步。平时放假时，她一天能玩7、8个小时，从早上一睁眼就玩一整天，经常吃饭、睡觉都顾不上，晚上也常常玩到凌晨。
         * classcontentvoidurl : null
         * uploadtime : 1524804300000
         * classtype : 1
         * viewingtimes : 0
         * userId : 1
         * thumbsup : 0
         * noruser : {"id":1,"signname":"13889885021","signpassword":"BB10D6D675CBFBF3BD39B0971A9DD640","signdate":1524556577000,"username":"方成鸿基","userphone":"","age":20,"sex":0,"school":90000,"grade":"","classes":"","address":"","parentname":"方成鸿基","headimgurl":"/HighShoolManager/image/userheadimg/201804281509242072.jpeg","summary":null,"usertype":10,"grownum":null,"schl":null}
         */

        private int id;
        private String classname;
        private String classpreviewimageurl;
        private int isinc;
        private String classcontenttexturl;
        private String classcontenttext;
        private Object classcontentvoidurl;
        private long uploadtime;
        private int classtype;
        private int viewingtimes;
        private int userId;
        private int thumbsup;
        private NoruserBean noruser;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getClassname() {
            return classname;
        }

        public void setClassname(String classname) {
            this.classname = classname;
        }

        public String getClasspreviewimageurl() {
            return classpreviewimageurl;
        }

        public void setClasspreviewimageurl(String classpreviewimageurl) {
            this.classpreviewimageurl = classpreviewimageurl;
        }

        public int getIsinc() {
            return isinc;
        }

        public void setIsinc(int isinc) {
            this.isinc = isinc;
        }

        public String getClasscontenttexturl() {
            return classcontenttexturl;
        }

        public void setClasscontenttexturl(String classcontenttexturl) {
            this.classcontenttexturl = classcontenttexturl;
        }

        public String getClasscontenttext() {
            return classcontenttext;
        }

        public void setClasscontenttext(String classcontenttext) {
            this.classcontenttext = classcontenttext;
        }

        public Object getClasscontentvoidurl() {
            return classcontentvoidurl;
        }

        public void setClasscontentvoidurl(Object classcontentvoidurl) {
            this.classcontentvoidurl = classcontentvoidurl;
        }

        public long getUploadtime() {
            return uploadtime;
        }

        public void setUploadtime(long uploadtime) {
            this.uploadtime = uploadtime;
        }

        public int getClasstype() {
            return classtype;
        }

        public void setClasstype(int classtype) {
            this.classtype = classtype;
        }

        public int getViewingtimes() {
            return viewingtimes;
        }

        public void setViewingtimes(int viewingtimes) {
            this.viewingtimes = viewingtimes;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getThumbsup() {
            return thumbsup;
        }

        public void setThumbsup(int thumbsup) {
            this.thumbsup = thumbsup;
        }

        public NoruserBean getNoruser() {
            return noruser;
        }

        public void setNoruser(NoruserBean noruser) {
            this.noruser = noruser;
        }

        public static class NoruserBean {
            /**
             * id : 1
             * signname : 13889885021
             * signpassword : BB10D6D675CBFBF3BD39B0971A9DD640
             * signdate : 1524556577000
             * username : 方成鸿基
             * userphone :
             * age : 20
             * sex : 0
             * school : 90000
             * grade :
             * classes :
             * address :
             * parentname : 方成鸿基
             * headimgurl : /HighShoolManager/image/userheadimg/201804281509242072.jpeg
             * summary : null
             * usertype : 10
             * grownum : null
             * schl : null
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
            private String headimgurl;
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

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
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
}
