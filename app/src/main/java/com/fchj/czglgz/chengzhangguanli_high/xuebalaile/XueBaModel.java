package com.fchj.czglgz.chengzhangguanli_high.xuebalaile;

import java.util.List;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class XueBaModel {

    /**
     * code : 1
     * msg : 获取学霸来了数据成功
     * data : [{"id":6,"voidname":"学霸来啦第六期","previewimageurl":"image/scholar/201804201430186033.jpeg","contentvoidurl":"void/scholar/201804201430186815.mp4","type":1,"uploadtime":1524205818000,"userId":3,"viewingtimes":0,"thumbsup":0,"noruser":{"id":3,"signname":"123","signpassword":"123","signdate":1523808000000,"username":"","userphone":"","age":null,"sex":null,"school":null,"grade":"","classes":"","address":"","parentname":"","headimgurl":null,"summary":null,"usertype":1,"grownum":null}},{"id":5,"voidname":"学霸来啦第五期","previewimageurl":"image/scholar/201804201430061000.jpeg","contentvoidurl":"void/scholar/201804201430061971.mp4","type":1,"uploadtime":1524205806000,"userId":3,"viewingtimes":0,"thumbsup":0,"noruser":{"id":3,"signname":"123","signpassword":"123","signdate":1523808000000,"username":"","userphone":"","age":null,"sex":null,"school":null,"grade":"","classes":"","address":"","parentname":"","headimgurl":null,"summary":null,"usertype":1,"grownum":null}},{"id":4,"voidname":"学霸来啦第四期","previewimageurl":"image/scholar/2018042014295512.jpeg","contentvoidurl":"void/scholar/201804201429554019.mp4","type":1,"uploadtime":1524205795000,"userId":3,"viewingtimes":0,"thumbsup":0,"noruser":{"id":3,"signname":"123","signpassword":"123","signdate":1523808000000,"username":"","userphone":"","age":null,"sex":null,"school":null,"grade":"","classes":"","address":"","parentname":"","headimgurl":null,"summary":null,"usertype":1,"grownum":null}},{"id":3,"voidname":"学霸来啦第三期","previewimageurl":"image/scholar/201804201429361257.jpeg","contentvoidurl":"void/scholar/201804201429364867.mp4","type":1,"uploadtime":1524205776000,"userId":3,"viewingtimes":0,"thumbsup":0,"noruser":{"id":3,"signname":"123","signpassword":"123","signdate":1523808000000,"username":"","userphone":"","age":null,"sex":null,"school":null,"grade":"","classes":"","address":"","parentname":"","headimgurl":null,"summary":null,"usertype":1,"grownum":null}},{"id":2,"voidname":"学霸来啦第二期","previewimageurl":"image/scholar/201804201429233418.jpeg","contentvoidurl":"void/scholar/201804201429239747.mp4","type":1,"uploadtime":1524205763000,"userId":3,"viewingtimes":0,"thumbsup":0,"noruser":{"id":3,"signname":"123","signpassword":"123","signdate":1523808000000,"username":"","userphone":"","age":null,"sex":null,"school":null,"grade":"","classes":"","address":"","parentname":"","headimgurl":null,"summary":null,"usertype":1,"grownum":null}},{"id":1,"voidname":"学霸来啦第一期","previewimageurl":"image/scholar/201804201425497095.jpeg","contentvoidurl":"void/scholar/201804201425497684.mp4","type":1,"uploadtime":1524205549000,"userId":3,"viewingtimes":0,"thumbsup":0,"noruser":{"id":3,"signname":"123","signpassword":"123","signdate":1523808000000,"username":"","userphone":"","age":null,"sex":null,"school":null,"grade":"","classes":"","address":"","parentname":"","headimgurl":null,"summary":null,"usertype":1,"grownum":null}}]
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
         * id : 6
         * voidname : 学霸来啦第六期
         * previewimageurl : image/scholar/201804201430186033.jpeg
         * contentvoidurl : void/scholar/201804201430186815.mp4
         * type : 1
         * uploadtime : 1524205818000
         * userId : 3
         * viewingtimes : 0
         * thumbsup : 0
         * noruser : {"id":3,"signname":"123","signpassword":"123","signdate":1523808000000,"username":"","userphone":"","age":null,"sex":null,"school":null,"grade":"","classes":"","address":"","parentname":"","headimgurl":null,"summary":null,"usertype":1,"grownum":null}
         */

        private int id;
        private String voidname;
        private String previewimageurl;
        private String contentvoidurl;
        private int type;
        private long uploadtime;
        private int userId;
        private int viewingtimes;
        private int thumbsup;
        private NoruserBean noruser;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVoidname() {
            return voidname;
        }

        public void setVoidname(String voidname) {
            this.voidname = voidname;
        }

        public String getPreviewimageurl() {
            return previewimageurl;
        }

        public void setPreviewimageurl(String previewimageurl) {
            this.previewimageurl = previewimageurl;
        }

        public String getContentvoidurl() {
            return contentvoidurl;
        }

        public void setContentvoidurl(String contentvoidurl) {
            this.contentvoidurl = contentvoidurl;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public int getViewingtimes() {
            return viewingtimes;
        }

        public void setViewingtimes(int viewingtimes) {
            this.viewingtimes = viewingtimes;
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
             * id : 3
             * signname : 123
             * signpassword : 123
             * signdate : 1523808000000
             * username :
             * userphone :
             * age : null
             * sex : null
             * school : null
             * grade :
             * classes :
             * address :
             * parentname :
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
            private Object age;
            private Object sex;
            private Object school;
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
}
