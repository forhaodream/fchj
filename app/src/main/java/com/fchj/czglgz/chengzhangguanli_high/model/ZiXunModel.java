package com.fchj.czglgz.chengzhangguanli_high.model;

import java.util.List;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class ZiXunModel {

    /**
     * code : 1
     * msg : 资讯获取成功
     * data : [{"id":1,"infotype":1,"title":"黄磊教学","previewimageurl":"","isinc":1,"contenttexturl":"www.baidu.com","contenttext":"大三的人护肤v反倒会比较短时间的哈赛基地和u维护的人不包邮出一堆粉丝电影风格让不法商家就撒都会夜读是否阿三肚饿","contentvoidurl":"void/information/201804171700503547.mp4","uploadtime":null,"userId":2,"noruser":{"id":2,"signname":"13889885021","signpassword":"724521","signdate":1523947959000,"username":"喜笑笑","userphone":"13574557542","age":27,"sex":0,"school":2,"grade":"地方的","classes":"豆腐乳","address":"撒点热解毒的人吧","parentname":"习大大","headimgurl":"image/userheadimg/201804170919389803.png","summary":null,"usertype":1}},{"id":2,"infotype":2,"title":"黄磊的看法","previewimageurl":"image/information/","isinc":1,"contenttexturl":"www.baidu.com","contenttext":"但是风景和速度和夫人符合，护肤和富庶的合法稳定，有点厚衣服和乳房虎视眈眈粗放，速度和夫人","contentvoidurl":"void/information/20180417171618770.mp4","uploadtime":1523956578000,"userId":2,"noruser":{"id":2,"signname":"13889885021","signpassword":"724521","signdate":1523947959000,"username":"喜笑笑","userphone":"13574557542","age":27,"sex":0,"school":2,"grade":"地方的","classes":"豆腐乳","address":"撒点热解毒的人吧","parentname":"习大大","headimgurl":"image/userheadimg/201804170919389803.png","summary":null,"usertype":1}},{"id":3,"infotype":2,"title":"何炅教学","previewimageurl":"image/information/","isinc":0,"contenttexturl":"www.baidu.com","contenttext":"电话分机粉红粉红粉红粉红的风很大很大很大很好","contentvoidurl":null,"uploadtime":1523958268000,"userId":2,"noruser":{"id":2,"signname":"13889885021","signpassword":"724521","signdate":1523947959000,"username":"喜笑笑","userphone":"13574557542","age":27,"sex":0,"school":2,"grade":"地方的","classes":"豆腐乳","address":"撒点热解毒的人吧","parentname":"习大大","headimgurl":"image/userheadimg/201804170919389803.png","summary":null,"usertype":1}}]
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
         * id : 1
         * infotype : 1
         * title : 黄磊教学
         * previewimageurl :
         * isinc : 1
         * contenttexturl : www.baidu.com
         * contenttext : 大三的人护肤v反倒会比较短时间的哈赛基地和u维护的人不包邮出一堆粉丝电影风格让不法商家就撒都会夜读是否阿三肚饿
         * contentvoidurl : void/information/201804171700503547.mp4
         * uploadtime : null
         * userId : 2
         * noruser : {"id":2,"signname":"13889885021","signpassword":"724521","signdate":1523947959000,"username":"喜笑笑","userphone":"13574557542","age":27,"sex":0,"school":2,"grade":"地方的","classes":"豆腐乳","address":"撒点热解毒的人吧","parentname":"习大大","headimgurl":"image/userheadimg/201804170919389803.png","summary":null,"usertype":1}
         */

        private int id;
        private int infotype;
        private String title;
        private String previewimageurl;
        private int isinc;
        private String contenttexturl;
        private String contenttext;
        private String contentvoidurl;
        private Object uploadtime;
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

        public String getContentvoidurl() {
            return contentvoidurl;
        }

        public void setContentvoidurl(String contentvoidurl) {
            this.contentvoidurl = contentvoidurl;
        }

        public Object getUploadtime() {
            return uploadtime;
        }

        public void setUploadtime(Object uploadtime) {
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
        }
    }
}
