package com.fchj.czglgz.chengzhangguanli_high.model;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class SchoolNewsDetailModel {

    /**
     * code : 1
     * msg : 查询动态详情成功
     * data : {"id":7,"newsname":"东北大学校园动态","newspreviewimageurl":"image/campusnews/201804230854161158.jpg","newscontenttexturl":"asjhdhhwrhhhchhchjrf,ruddhuuhweed,wueduiduifcr,erwufdhfc","newscontenttext":"asjhdhhwrhhhchhchjrf,ruddhuuhweed,wueduiduifcr,erwufdhfc","uploadtime":null,"newstype":1,"userId":2,"noruser":{"id":2,"signname":"13889885021","signpassword":"724521","signdate":1523947959000,"username":"喜笑笑","userphone":"13574557542","age":27,"sex":0,"school":2,"grade":"地方的","classes":"豆腐乳","address":"撒点热解毒的人吧","parentname":"习大大","headimgurl":"image/userheadimg/201804170919389803.png","summary":null,"usertype":1,"grownum":null}}
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
         * newsname : 东北大学校园动态
         * newspreviewimageurl : image/campusnews/201804230854161158.jpg
         * newscontenttexturl : asjhdhhwrhhhchhchjrf,ruddhuuhweed,wueduiduifcr,erwufdhfc
         * newscontenttext : asjhdhhwrhhhchhchjrf,ruddhuuhweed,wueduiduifcr,erwufdhfc
         * uploadtime : null
         * newstype : 1
         * userId : 2
         * noruser : {"id":2,"signname":"13889885021","signpassword":"724521","signdate":1523947959000,"username":"喜笑笑","userphone":"13574557542","age":27,"sex":0,"school":2,"grade":"地方的","classes":"豆腐乳","address":"撒点热解毒的人吧","parentname":"习大大","headimgurl":"image/userheadimg/201804170919389803.png","summary":null,"usertype":1,"grownum":null}
         */

        private int id;
        private String newsname;
        private String newspreviewimageurl;
        private String newscontenttexturl;
        private String newscontenttext;
        private Object uploadtime;
        private int newstype;
        private int userId;
        private NoruserBean noruser;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNewsname() {
            return newsname;
        }

        public void setNewsname(String newsname) {
            this.newsname = newsname;
        }

        public String getNewspreviewimageurl() {
            return newspreviewimageurl;
        }

        public void setNewspreviewimageurl(String newspreviewimageurl) {
            this.newspreviewimageurl = newspreviewimageurl;
        }

        public String getNewscontenttexturl() {
            return newscontenttexturl;
        }

        public void setNewscontenttexturl(String newscontenttexturl) {
            this.newscontenttexturl = newscontenttexturl;
        }

        public String getNewscontenttext() {
            return newscontenttext;
        }

        public void setNewscontenttext(String newscontenttext) {
            this.newscontenttext = newscontenttext;
        }

        public Object getUploadtime() {
            return uploadtime;
        }

        public void setUploadtime(Object uploadtime) {
            this.uploadtime = uploadtime;
        }

        public int getNewstype() {
            return newstype;
        }

        public void setNewstype(int newstype) {
            this.newstype = newstype;
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
