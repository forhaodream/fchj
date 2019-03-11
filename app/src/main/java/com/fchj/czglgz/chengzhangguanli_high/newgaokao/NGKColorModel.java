package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import java.util.List;

public class NGKColorModel {

    /**
     * code : 1
     * msg : 获取高校选科专业列表成功
     * data : [{"srid":1,"subjectname":"物理","subjnum":0,"subjpercent":100},{"srid":2,"subjectname":"化学","subjnum":0,"subjpercent":100},{"srid":3,"subjectname":"生物","subjnum":0,"subjpercent":100},{"srid":4,"subjectname":"历史","subjnum":0,"subjpercent":100},{"srid":5,"subjectname":"地理","subjnum":0,"subjpercent":100},{"srid":6,"subjectname":"政治","subjnum":0,"subjpercent":100},{"srid":7,"subjectname":"技术","subjnum":0,"subjpercent":100},{"srid":8,"subjectname":"不限","subjnum":17,"subjpercent":100}]
     * value : {"schoolNum":17,"totalpercent":"77.27","recruitnum":41}
     */

    private int code;
    private String msg;
    private ValueBean value;
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

    public ValueBean getValue() {
        return value;
    }

    public void setValue(ValueBean value) {
        this.value = value;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ValueBean {
        /**
         * schoolNum : 17
         * totalpercent : 77.27
         * recruitnum : 41
         */

        private int schoolNum;
        private String totalpercent;
        private int recruitnum;

        public int getSchoolNum() {
            return schoolNum;
        }

        public void setSchoolNum(int schoolNum) {
            this.schoolNum = schoolNum;
        }

        public String getTotalpercent() {
            return totalpercent;
        }

        public void setTotalpercent(String totalpercent) {
            this.totalpercent = totalpercent;
        }

        public int getRecruitnum() {
            return recruitnum;
        }

        public void setRecruitnum(int recruitnum) {
            this.recruitnum = recruitnum;
        }
    }

    public static class DataBean {
        /**
         * srid : 1
         * subjectname : 物理
         * subjnum : 0
         * subjpercent : 100.0
         */

        private int srid;
        private String subjectname;
        private int subjnum;
        private double subjpercent;

        public int getSrid() {
            return srid;
        }

        public void setSrid(int srid) {
            this.srid = srid;
        }

        public String getSubjectname() {
            return subjectname;
        }

        public void setSubjectname(String subjectname) {
            this.subjectname = subjectname;
        }

        public int getSubjnum() {
            return subjnum;
        }

        public void setSubjnum(int subjnum) {
            this.subjnum = subjnum;
        }

        public double getSubjpercent() {
            return subjpercent;
        }

        public void setSubjpercent(double subjpercent) {
            this.subjpercent = subjpercent;
        }
    }
}
