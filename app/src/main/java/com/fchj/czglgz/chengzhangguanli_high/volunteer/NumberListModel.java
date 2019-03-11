package com.fchj.czglgz.chengzhangguanli_high.volunteer;

import java.util.List;

public class NumberListModel {

    /**
     * code : 1
     * msg : 获取专业分数列表成功
     * data : [{"adid":229735,"schoolstatus":"安徽","paryear":"2017","subject":"理科","universityname":"西安交通大学","major":"电气类","average":639,"hstscore":null,"mimscore":"637","adtbatch":"一批"},{"adid":229448,"schoolstatus":"安徽","paryear":"2017","subject":"理科","universityname":"同济大学","major":"材料科学与工程","average":637,"hstscore":null,"mimscore":"635","adtbatch":"一批"},{"adid":230805,"schoolstatus":"安徽","paryear":"2017","subject":"理科","universityname":"中国社会科学院大学","major":"经济学","average":635,"hstscore":null,"mimscore":"635","adtbatch":"一批"},{"adid":230923,"schoolstatus":"安徽","paryear":"2017","subject":"理科","universityname":"中央财经大学","major":"财政学类","average":632,"hstscore":null,"mimscore":"632","adtbatch":"一批"},{"adid":229520,"schoolstatus":"安徽","paryear":"2017","subject":"理科","universityname":"武汉大学","major":"测绘类","average":631,"hstscore":null,"mimscore":"628","adtbatch":"一批"}]
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
         * adid : 229735
         * schoolstatus : 安徽
         * paryear : 2017
         * subject : 理科
         * universityname : 西安交通大学
         * major : 电气类
         * average : 639
         * hstscore : null
         * mimscore : 637
         * adtbatch : 一批
         */

        private int adid;
        private String schoolstatus;
        private String paryear;
        private String subject;
        private String universityname;
        private String major;
        private int average;
        private Object hstscore;
        private String mimscore;
        private String adtbatch;

        public int getAdid() {
            return adid;
        }

        public void setAdid(int adid) {
            this.adid = adid;
        }

        public String getSchoolstatus() {
            return schoolstatus;
        }

        public void setSchoolstatus(String schoolstatus) {
            this.schoolstatus = schoolstatus;
        }

        public String getParyear() {
            return paryear;
        }

        public void setParyear(String paryear) {
            this.paryear = paryear;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getUniversityname() {
            return universityname;
        }

        public void setUniversityname(String universityname) {
            this.universityname = universityname;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public int getAverage() {
            return average;
        }

        public void setAverage(int average) {
            this.average = average;
        }

        public Object getHstscore() {
            return hstscore;
        }

        public void setHstscore(Object hstscore) {
            this.hstscore = hstscore;
        }

        public String getMimscore() {
            return mimscore;
        }

        public void setMimscore(String mimscore) {
            this.mimscore = mimscore;
        }

        public String getAdtbatch() {
            return adtbatch;
        }

        public void setAdtbatch(String adtbatch) {
            this.adtbatch = adtbatch;
        }
    }
}
