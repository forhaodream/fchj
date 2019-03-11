package com.fchj.czglgz.chengzhangguanli_high.volunteer;

import java.util.List;

public class CollegeListModel {

    /**
     * code : 1
     * msg : 获取专业分数列表成功
     * data : [{"adid":731331,"schoolstatus":"山东","paryear":"2017","subject":"理科","universityname":"大连理工大学","major":"工程力学（钱令希力学创新实验班）","average":646,"hstscore":null,"mimscore":"646","adtbatch":"本科批"},{"adid":731361,"schoolstatus":"山东","paryear":"2017","subject":"理科","universityname":"大连理工大学","major":"应用化学（张大煜化学菁英班）","average":644,"hstscore":null,"mimscore":"637","adtbatch":"本科批"},{"adid":731327,"schoolstatus":"山东","paryear":"2017","subject":"理科","universityname":"大连理工大学","major":"电子信息类（电气信息类创新实验班）","average":642,"hstscore":null,"mimscore":"--","adtbatch":"本科批"},{"adid":731340,"schoolstatus":"山东","paryear":"2017","subject":"理科","universityname":"大连理工大学","major":"机械类（机械类创新实验班）","average":642,"hstscore":null,"mimscore":"642","adtbatch":"本科批"},{"adid":731332,"schoolstatus":"山东","paryear":"2017","subject":"理科","universityname":"大连理工大学","major":"工商管理类","average":640,"hstscore":null,"mimscore":"632","adtbatch":"本科批"},{"adid":731330,"schoolstatus":"山东","paryear":"2017","subject":"理科","universityname":"大连理工大学","major":"工程力学","average":639,"hstscore":null,"mimscore":"635","adtbatch":"本科批"},{"adid":731362,"schoolstatus":"山东","paryear":"2017","subject":"理科","universityname":"大连理工大学","major":"应用物理学（王大珩物理科学班）","average":639,"hstscore":null,"mimscore":"637","adtbatch":"本科批"},{"adid":731324,"schoolstatus":"山东","paryear":"2017","subject":"理科","universityname":"大连理工大学","major":"电气工程及其自动化","average":637,"hstscore":null,"mimscore":"635","adtbatch":"本科批"},{"adid":731326,"schoolstatus":"山东","paryear":"2017","subject":"理科","universityname":"大连理工大学","major":"电子信息工程（含英语强化班）","average":637,"hstscore":null,"mimscore":"635","adtbatch":"本科批"},{"adid":731366,"schoolstatus":"山东","paryear":"2017","subject":"理科","universityname":"大连理工大学","major":"自动化","average":637,"hstscore":null,"mimscore":"635","adtbatch":"本科批"}]
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
         * adid : 731331
         * schoolstatus : 山东
         * paryear : 2017
         * subject : 理科
         * universityname : 大连理工大学
         * major : 工程力学（钱令希力学创新实验班）
         * average : 646
         * hstscore : null
         * mimscore : 646
         * adtbatch : 本科批
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
