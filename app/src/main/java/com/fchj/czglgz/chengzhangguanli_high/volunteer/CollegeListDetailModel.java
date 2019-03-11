package com.fchj.czglgz.chengzhangguanli_high.volunteer;

import java.util.List;

public class CollegeListDetailModel {


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


        private String guid;
        private String universityname;
        private String universityenname;
        private String universityofficial;
        private String enrolmentwebsite;
        private String universityaddress;
        private String universityphone;
        private String universitycreatime;
        private String universitytype;
        private String universitysubjecttype;
        private String universityfordept;
        private int universitydoctoralnum;
        private int universitymasternum;
        private String universitystudentsnum;
        private double longitude;
        private double latitude;
        private String universitysummary;
        private String universityheat;
        private int universityranking;
        private String specialstudent;
        private String logofilename;
        private Object universityforareas;
        private List<LusyBean> lusy;
        private List<LuadtiBean> luadti;

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getUniversityname() {
            return universityname;
        }

        public void setUniversityname(String universityname) {
            this.universityname = universityname;
        }

        public String getUniversityenname() {
            return universityenname;
        }

        public void setUniversityenname(String universityenname) {
            this.universityenname = universityenname;
        }

        public String getUniversityofficial() {
            return universityofficial;
        }

        public void setUniversityofficial(String universityofficial) {
            this.universityofficial = universityofficial;
        }

        public String getEnrolmentwebsite() {
            return enrolmentwebsite;
        }

        public void setEnrolmentwebsite(String enrolmentwebsite) {
            this.enrolmentwebsite = enrolmentwebsite;
        }

        public String getUniversityaddress() {
            return universityaddress;
        }

        public void setUniversityaddress(String universityaddress) {
            this.universityaddress = universityaddress;
        }

        public String getUniversityphone() {
            return universityphone;
        }

        public void setUniversityphone(String universityphone) {
            this.universityphone = universityphone;
        }

        public String getUniversitycreatime() {
            return universitycreatime;
        }

        public void setUniversitycreatime(String universitycreatime) {
            this.universitycreatime = universitycreatime;
        }

        public String getUniversitytype() {
            return universitytype;
        }

        public void setUniversitytype(String universitytype) {
            this.universitytype = universitytype;
        }

        public String getUniversitysubjecttype() {
            return universitysubjecttype;
        }

        public void setUniversitysubjecttype(String universitysubjecttype) {
            this.universitysubjecttype = universitysubjecttype;
        }

        public String getUniversityfordept() {
            return universityfordept;
        }

        public void setUniversityfordept(String universityfordept) {
            this.universityfordept = universityfordept;
        }

        public int getUniversitydoctoralnum() {
            return universitydoctoralnum;
        }

        public void setUniversitydoctoralnum(int universitydoctoralnum) {
            this.universitydoctoralnum = universitydoctoralnum;
        }

        public int getUniversitymasternum() {
            return universitymasternum;
        }

        public void setUniversitymasternum(int universitymasternum) {
            this.universitymasternum = universitymasternum;
        }

        public String getUniversitystudentsnum() {
            return universitystudentsnum;
        }

        public void setUniversitystudentsnum(String universitystudentsnum) {
            this.universitystudentsnum = universitystudentsnum;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public String getUniversitysummary() {
            return universitysummary;
        }

        public void setUniversitysummary(String universitysummary) {
            this.universitysummary = universitysummary;
        }

        public String getUniversityheat() {
            return universityheat;
        }

        public void setUniversityheat(String universityheat) {
            this.universityheat = universityheat;
        }

        public int getUniversityranking() {
            return universityranking;
        }

        public void setUniversityranking(int universityranking) {
            this.universityranking = universityranking;
        }

        public String getSpecialstudent() {
            return specialstudent;
        }

        public void setSpecialstudent(String specialstudent) {
            this.specialstudent = specialstudent;
        }

        public String getLogofilename() {
            return logofilename;
        }

        public void setLogofilename(String logofilename) {
            this.logofilename = logofilename;
        }

        public Object getUniversityforareas() {
            return universityforareas;
        }

        public void setUniversityforareas(Object universityforareas) {
            this.universityforareas = universityforareas;
        }

        public List<LusyBean> getLusy() {
            return lusy;
        }

        public void setLusy(List<LusyBean> lusy) {
            this.lusy = lusy;
        }

        public List<LuadtiBean> getLuadti() {
            return luadti;
        }

        public void setLuadti(List<LuadtiBean> luadti) {
            this.luadti = luadti;
        }

        public static class LusyBean {
            /**
             * usid : 122043
             * unversityguid : 84a8bc59-3547-4203-a096-4228c81e8fc3
             * unversityname : 北京大学
             * sceneryplace : 校门
             * extensionname : jpeg
             * specificimg : http://growup-highschool.oss-cn-hangzhou.aliyuncs.com/UniversityScenery/specific/1D4B301D-A890-44CC-A9A8-EA8621CB6C4A_big.jpeg
             * breviaryimg : http://growup-highschool.oss-cn-hangzhou.aliyuncs.com/UniversityScenery/breviary/2B06C3BD-09F0-4908-8E56-7ADFDBEC4761_small.jpeg
             */

            private int usid;
            private String unversityguid;
            private String unversityname;
            private String sceneryplace;
            private String extensionname;
            private String specificimg;
            private String breviaryimg;

            public int getUsid() {
                return usid;
            }

            public void setUsid(int usid) {
                this.usid = usid;
            }

            public String getUnversityguid() {
                return unversityguid;
            }

            public void setUnversityguid(String unversityguid) {
                this.unversityguid = unversityguid;
            }

            public String getUnversityname() {
                return unversityname;
            }

            public void setUnversityname(String unversityname) {
                this.unversityname = unversityname;
            }

            public String getSceneryplace() {
                return sceneryplace;
            }

            public void setSceneryplace(String sceneryplace) {
                this.sceneryplace = sceneryplace;
            }

            public String getExtensionname() {
                return extensionname;
            }

            public void setExtensionname(String extensionname) {
                this.extensionname = extensionname;
            }

            public String getSpecificimg() {
                return specificimg;
            }

            public void setSpecificimg(String specificimg) {
                this.specificimg = specificimg;
            }

            public String getBreviaryimg() {
                return breviaryimg;
            }

            public void setBreviaryimg(String breviaryimg) {
                this.breviaryimg = breviaryimg;
            }
        }

        public static class LuadtiBean {
            /**
             * major : 基础医学
             * subject : 理科
             * average2017 : --
             * average2016 : 676
             */

            private String major;
            private String subject;
            private String average2017;
            private int average2016;

            public String getMajor() {
                return major;
            }

            public void setMajor(String major) {
                this.major = major;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public String getAverage2017() {
                return average2017;
            }

            public void setAverage2017(String average2017) {
                this.average2017 = average2017;
            }

            public int getAverage2016() {
                return average2016;
            }

            public void setAverage2016(int average2016) {
                this.average2016 = average2016;
            }
        }
    }
}