package com.fchj.czglgz.chengzhangguanli_high.zixun;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class ZiXunDetailModel {

    /**
     * code : 1
     * msg : 获取资讯成功
     * data : {"id":13,"infotype":3,"title":"高考志愿填报五大陷阱","previewimageurl":"image/information/201804181338593160.png","isinc":0,"contenttexturl":"https://mp.weixin.qq.com/s/L0irtwli5qq6ji636kEgJQ","contenttext":"高考志愿填报，是一项堪称\u201c二次高考\u201d的复杂工程。作为高三家长和学生，面对高考升学的问题，该从哪里去获得升学经验？需要掌握哪些数据？从哪里可以获得升学知识？","contentvoidurl":null,"uploadtime":1524029939000,"userId":2,"noruser":{"id":2,"signname":"13889885021","signpassword":"724521","signdate":1523947959000,"username":"喜笑笑","userphone":"13574557542","age":27,"sex":0,"school":2,"grade":"地方的","classes":"豆腐乳","address":"撒点热解毒的人吧","parentname":"习大大","headimgurl":"image/userheadimg/201804170919389803.png","summary":null,"usertype":1,"grownum":null}}
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
         * id : 13
         * infotype : 3
         * title : 高考志愿填报五大陷阱
         * previewimageurl : image/information/201804181338593160.png
         * isinc : 0
         * contenttexturl : https://mp.weixin.qq.com/s/L0irtwli5qq6ji636kEgJQ
         * contenttext : 高考志愿填报，是一项堪称“二次高考”的复杂工程。作为高三家长和学生，面对高考升学的问题，该从哪里去获得升学经验？需要掌握哪些数据？从哪里可以获得升学知识？
         * contentvoidurl : null
         * uploadtime : 1524029939000
         * userId : 2
         * noruser : {"id":2,"signname":"13889885021","signpassword":"724521","signdate":1523947959000,"username":"喜笑笑","userphone":"13574557542","age":27,"sex":0,"school":2,"grade":"地方的","classes":"豆腐乳","address":"撒点热解毒的人吧","parentname":"习大大","headimgurl":"image/userheadimg/201804170919389803.png","summary":null,"usertype":1,"grownum":null}
         */

        private int id;
        private int infotype;
        private String title;
        private String previewimageurl;
        private int isinc;
        private String contenttexturl;
        private String contenttext;
        private Object contentvoidurl;
        private long uploadtime;
        private int userId;
        private NoruserBean noruser;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getInfotype() {
            return infotype;
        }

        public void setInfotype(int infotype) {
            this.infotype = infotype;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPreviewimageurl() {
            return previewimageurl;
        }

        public void setPreviewimageurl(String previewimageurl) {
            this.previewimageurl = previewimageurl;
        }

        public int getIsinc() {
            return isinc;
        }

        public void setIsinc(int isinc) {
            this.isinc = isinc;
        }

        public String getContenttexturl() {
            return contenttexturl;
        }

        public void setContenttexturl(String contenttexturl) {
            this.contenttexturl = contenttexturl;
        }

        public String getContenttext() {
            return contenttext;
        }

        public void setContenttext(String contenttext) {
            this.contenttext = contenttext;
        }

        public Object getContentvoidurl() {
            return contentvoidurl;
        }

        public void setContentvoidurl(Object contentvoidurl) {
            this.contentvoidurl = contentvoidurl;
        }

        public long getUploadtime() {
            return uploadtime;
        }

        public void setUploadtime(long uploadtime) {
            this.uploadtime = uploadtime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public NoruserBean getNoruser() {
            return noruser;
        }

        public void setNoruser(NoruserBean noruser) {
            this.noruser = noruser;
        }

        public static class NoruserBean {
            /**
             * id : 2
             * signname : 13889885021
             * signpassword : 724521
             * signdate : 1523947959000
             * username : 喜笑笑
             * userphone : 13574557542
             * age : 27
             * sex : 0
             * school : 2
             * grade : 地方的
             * classes : 豆腐乳
             * address : 撒点热解毒的人吧
             * parentname : 习大大
             * headimgurl : image/userheadimg/201804170919389803.png
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
            private String headimgurl;
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
        }
    }
}
