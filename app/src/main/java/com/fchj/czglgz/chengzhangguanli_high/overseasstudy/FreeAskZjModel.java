package com.fchj.czglgz.chengzhangguanli_high.overseasstudy;

import java.util.List;

public class FreeAskZjModel {

    /**
     * code : 1
     * msg : 获取专家列表成功
     * data : [{"eccId":7,"osetId":5,"country":"美国","os":{"osetId":5,"signname":"xiemin","signpassword":"xiemin","osename":"解敏","osephone":"","headimgurl":"http://47.94.165.33:8080/HighShoolManager/image/osexpert/201806250947544399.png","summary":"    2008年开始从事美国留学申请，工作十载，为许许多多不同经历背景及年龄层次的同学设计并成功申请到符合个人特点及未来前景规划的理想院校，有着丰富的美国院校考察经历，帮助为没有出国学习经历的家长和同学们更好的理解国外的学习和生活方式，更准确的选择定位适合的学校","lsecc":null}},{"eccId":17,"osetId":10,"country":"美国","os":{"osetId":10,"signname":"sunyu","signpassword":"sunyu","osename":"孙禹","osephone":"","headimgurl":"http://47.94.165.33:8080/HighShoolManager/image/osexpert/201806250952529137.png","summary":"   毕业于英国布里斯托商学院硕士\r\n专精于北美留学规划、院校申请、职业发展规划\r\n曾辅导众多学生申请到哥伦比亚大学、康奈尔大学、芝加哥大学、杜克大学等世界顶级名校\r\n","lsecc":null}}]
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
         * eccId : 7
         * osetId : 5
         * country : 美国
         * os : {"osetId":5,"signname":"xiemin","signpassword":"xiemin","osename":"解敏","osephone":"","headimgurl":"http://47.94.165.33:8080/HighShoolManager/image/osexpert/201806250947544399.png","summary":"    2008年开始从事美国留学申请，工作十载，为许许多多不同经历背景及年龄层次的同学设计并成功申请到符合个人特点及未来前景规划的理想院校，有着丰富的美国院校考察经历，帮助为没有出国学习经历的家长和同学们更好的理解国外的学习和生活方式，更准确的选择定位适合的学校","lsecc":null}
         */

        private int eccId;
        private int osetId;
        private String country;
        private OsBean os;

        public int getEccId() {
            return eccId;
        }

        public void setEccId(int eccId) {
            this.eccId = eccId;
        }

        public int getOsetId() {
            return osetId;
        }

        public void setOsetId(int osetId) {
            this.osetId = osetId;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public OsBean getOs() {
            return os;
        }

        public void setOs(OsBean os) {
            this.os = os;
        }

        public static class OsBean {
            /**
             * osetId : 5
             * signname : xiemin
             * signpassword : xiemin
             * osename : 解敏
             * osephone :
             * headimgurl : http://47.94.165.33:8080/HighShoolManager/image/osexpert/201806250947544399.png
             * summary :     2008年开始从事美国留学申请，工作十载，为许许多多不同经历背景及年龄层次的同学设计并成功申请到符合个人特点及未来前景规划的理想院校，有着丰富的美国院校考察经历，帮助为没有出国学习经历的家长和同学们更好的理解国外的学习和生活方式，更准确的选择定位适合的学校
             * lsecc : null
             */

            private int osetId;
            private String signname;
            private String signpassword;
            private String osename;
            private String osephone;
            private String headimgurl;
            private String summary;
            private Object lsecc;

            public int getOsetId() {
                return osetId;
            }

            public void setOsetId(int osetId) {
                this.osetId = osetId;
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

            public String getOsename() {
                return osename;
            }

            public void setOsename(String osename) {
                this.osename = osename;
            }

            public String getOsephone() {
                return osephone;
            }

            public void setOsephone(String osephone) {
                this.osephone = osephone;
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

            public Object getLsecc() {
                return lsecc;
            }

            public void setLsecc(Object lsecc) {
                this.lsecc = lsecc;
            }
        }
    }
}
