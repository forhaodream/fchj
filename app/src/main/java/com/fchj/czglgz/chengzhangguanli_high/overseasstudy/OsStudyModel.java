package com.fchj.czglgz.chengzhangguanli_high.overseasstudy;

import java.util.List;

public class OsStudyModel {

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

        private String intalshId;
        private String schoolName;
        private String schoolEnname;
        private int worldRanking;
        private Object nationalRanking;
        private String ownedCountry;
        private String ownedCity;
        private String ownedAreas;
        private String buildTime;
        private String lectureLanguage;
        private String schoolNature;
        private String schoolType;
        private int teacherNum;
        private int classNum;
        private int intalStudentNum;
        private String tsProportion;
        private String stayExpense;
        private String tuition;
        private String schoolSummary;
        private Object dominantMajor;
        private double scoreIELTS;
        private double scoreTOEFL;
        private double scoreAverage;
        private String schoolNetwork;
        private String address;
        private String telephone;
        private String email;
        private String applyClaim;
        private String schoollogo;
        private List<LausyBean> lausy;

        public String getIntalshId() {
            return intalshId;
        }

        public void setIntalshId(String intalshId) {
            this.intalshId = intalshId;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public String getSchoolEnname() {
            return schoolEnname;
        }

        public void setSchoolEnname(String schoolEnname) {
            this.schoolEnname = schoolEnname;
        }

        public int getWorldRanking() {
            return worldRanking;
        }

        public void setWorldRanking(int worldRanking) {
            this.worldRanking = worldRanking;
        }

        public Object getNationalRanking() {
            return nationalRanking;
        }

        public void setNationalRanking(Object nationalRanking) {
            this.nationalRanking = nationalRanking;
        }

        public String getOwnedCountry() {
            return ownedCountry;
        }

        public void setOwnedCountry(String ownedCountry) {
            this.ownedCountry = ownedCountry;
        }

        public String getOwnedCity() {
            return ownedCity;
        }

        public void setOwnedCity(String ownedCity) {
            this.ownedCity = ownedCity;
        }

        public String getOwnedAreas() {
            return ownedAreas;
        }

        public void setOwnedAreas(String ownedAreas) {
            this.ownedAreas = ownedAreas;
        }

        public String getBuildTime() {
            return buildTime;
        }

        public void setBuildTime(String buildTime) {
            this.buildTime = buildTime;
        }

        public String getLectureLanguage() {
            return lectureLanguage;
        }

        public void setLectureLanguage(String lectureLanguage) {
            this.lectureLanguage = lectureLanguage;
        }

        public String getSchoolNature() {
            return schoolNature;
        }

        public void setSchoolNature(String schoolNature) {
            this.schoolNature = schoolNature;
        }

        public String getSchoolType() {
            return schoolType;
        }

        public void setSchoolType(String schoolType) {
            this.schoolType = schoolType;
        }

        public int getTeacherNum() {
            return teacherNum;
        }

        public void setTeacherNum(int teacherNum) {
            this.teacherNum = teacherNum;
        }

        public int getClassNum() {
            return classNum;
        }

        public void setClassNum(int classNum) {
            this.classNum = classNum;
        }

        public int getIntalStudentNum() {
            return intalStudentNum;
        }

        public void setIntalStudentNum(int intalStudentNum) {
            this.intalStudentNum = intalStudentNum;
        }

        public String getTsProportion() {
            return tsProportion;
        }

        public void setTsProportion(String tsProportion) {
            this.tsProportion = tsProportion;
        }

        public String getStayExpense() {
            return stayExpense;
        }

        public void setStayExpense(String stayExpense) {
            this.stayExpense = stayExpense;
        }

        public String getTuition() {
            return tuition;
        }

        public void setTuition(String tuition) {
            this.tuition = tuition;
        }

        public String getSchoolSummary() {
            return schoolSummary;
        }

        public void setSchoolSummary(String schoolSummary) {
            this.schoolSummary = schoolSummary;
        }

        public Object getDominantMajor() {
            return dominantMajor;
        }

        public void setDominantMajor(Object dominantMajor) {
            this.dominantMajor = dominantMajor;
        }

        public double getScoreIELTS() {
            return scoreIELTS;
        }

        public void setScoreIELTS(double scoreIELTS) {
            this.scoreIELTS = scoreIELTS;
        }

        public double getScoreTOEFL() {
            return scoreTOEFL;
        }

        public void setScoreTOEFL(double scoreTOEFL) {
            this.scoreTOEFL = scoreTOEFL;
        }

        public double getScoreAverage() {
            return scoreAverage;
        }

        public void setScoreAverage(double scoreAverage) {
            this.scoreAverage = scoreAverage;
        }

        public String getSchoolNetwork() {
            return schoolNetwork;
        }

        public void setSchoolNetwork(String schoolNetwork) {
            this.schoolNetwork = schoolNetwork;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getApplyClaim() {
            return applyClaim;
        }

        public void setApplyClaim(String applyClaim) {
            this.applyClaim = applyClaim;
        }

        public String getSchoollogo() {
            return schoollogo;
        }

        public void setSchoollogo(String schoollogo) {
            this.schoollogo = schoollogo;
        }

        public List<LausyBean> getLausy() {
            return lausy;
        }

        public void setLausy(List<LausyBean> lausy) {
            this.lausy = lausy;
        }

        public static class LausyBean {
            /**
             * ausid : 2801
             * intalshId : OSS_00701_SC
             * ausimg : http://growup-highschool.oss-cn-hangzhou.aliyuncs.com/AbroadUniversity/OSS_00701_SC_SPG_A.jpg
             */

            private int ausid;
            private String intalshId;
            private String ausimg;

            public int getAusid() {
                return ausid;
            }

            public void setAusid(int ausid) {
                this.ausid = ausid;
            }

            public String getIntalshId() {
                return intalshId;
            }

            public void setIntalshId(String intalshId) {
                this.intalshId = intalshId;
            }

            public String getAusimg() {
                return ausimg;
            }

            public void setAusimg(String ausimg) {
                this.ausimg = ausimg;
            }
        }
    }
}
