package com.fchj.czglgz.chengzhangguanli_high.volunteer;

import java.util.List;

public class AllCollegeModel {

    /**
     * code : 1
     * msg : 获取高校列表成功
     * data : [{"urid":1,"ranking":1,"untyname":"北京大学","score":"100","starrank":"8星级","unty":{"guid":"84a8bc59-3547-4203-a096-4228c81e8fc3","universityname":"北京大学","universityenname":"Peking University","universityofficial":"[图片]http://www.pku.edu.cn/","enrolmentwebsite":"[图片]http://www.gotopku.cn/","universityaddress":"北京市海淀区颐和园路5号","universityphone":"010-62755622；010-62751407","universitycreatime":"1898年","universitytype":"公立","universitysubjecttype":"综合","universityfordept":"教育部","universitydoctoralnum":263,"universitymasternum":312,"universitystudentsnum":43327,"longitude":116,"latitude":40,"universitysummary":"北京大学创办于1898年，初名京师大学堂，是中国第一所国立综合性大学，也是当时中国最高教育行政机关。辛亥革命后，于1912年改为现名。　　作为新文化运动的中心和\u201c五四\u201d运动的策源地，作为中国最早传播马克思主义和民主科学思想的发祥地，作为中国共产党最早的活动基地，北京大学为民族的振兴和解放、国家的建设和发展、社会的文明和进步做出了不可替代的贡献，在中国走向现代化的进程中起到了重要的先锋作用。爱国、进步、民主、科学的传统精神和勤奋、严谨、求实、创新的学风在这里生生不息、代代相传。　　1917年，著名教育家蔡元培","universityheat":"22.1 万","universityranking":5,"specialstudent":"艺术生;","logofilename":"84a8bc59-3547-4203-a096-4228c81e8fc3.jpeg","universityforareas":null,"lusy":null,"luadti":null}}]
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
         * urid : 1
         * ranking : 1
         * untyname : 北京大学
         * score : 100
         * starrank : 8星级
         * unty : {"guid":"84a8bc59-3547-4203-a096-4228c81e8fc3","universityname":"北京大学","universityenname":"Peking University","universityofficial":"[图片]http://www.pku.edu.cn/","enrolmentwebsite":"[图片]http://www.gotopku.cn/","universityaddress":"北京市海淀区颐和园路5号","universityphone":"010-62755622；010-62751407","universitycreatime":"1898年","universitytype":"公立","universitysubjecttype":"综合","universityfordept":"教育部","universitydoctoralnum":263,"universitymasternum":312,"universitystudentsnum":43327,"longitude":116,"latitude":40,"universitysummary":"北京大学创办于1898年，初名京师大学堂，是中国第一所国立综合性大学，也是当时中国最高教育行政机关。辛亥革命后，于1912年改为现名。　　作为新文化运动的中心和\u201c五四\u201d运动的策源地，作为中国最早传播马克思主义和民主科学思想的发祥地，作为中国共产党最早的活动基地，北京大学为民族的振兴和解放、国家的建设和发展、社会的文明和进步做出了不可替代的贡献，在中国走向现代化的进程中起到了重要的先锋作用。爱国、进步、民主、科学的传统精神和勤奋、严谨、求实、创新的学风在这里生生不息、代代相传。　　1917年，著名教育家蔡元培","universityheat":"22.1 万","universityranking":5,"specialstudent":"艺术生;","logofilename":"84a8bc59-3547-4203-a096-4228c81e8fc3.jpeg","universityforareas":null,"lusy":null,"luadti":null}
         */

        private int urid;
        private int ranking;
        private String untyname;
        private String score;
        private String starrank;
        private UntyBean unty;

        public int getUrid() {
            return urid;
        }

        public void setUrid(int urid) {
            this.urid = urid;
        }

        public int getRanking() {
            return ranking;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
        }

        public String getUntyname() {
            return untyname;
        }

        public void setUntyname(String untyname) {
            this.untyname = untyname;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getStarrank() {
            return starrank;
        }

        public void setStarrank(String starrank) {
            this.starrank = starrank;
        }

        public UntyBean getUnty() {
            return unty;
        }

        public void setUnty(UntyBean unty) {
            this.unty = unty;
        }

        public static class UntyBean {
            /**
             * guid : 84a8bc59-3547-4203-a096-4228c81e8fc3
             * universityname : 北京大学
             * universityenname : Peking University
             * universityofficial : [图片]http://www.pku.edu.cn/
             * enrolmentwebsite : [图片]http://www.gotopku.cn/
             * universityaddress : 北京市海淀区颐和园路5号
             * universityphone : 010-62755622；010-62751407
             * universitycreatime : 1898年
             * universitytype : 公立
             * universitysubjecttype : 综合
             * universityfordept : 教育部
             * universitydoctoralnum : 263
             * universitymasternum : 312
             * universitystudentsnum : 43327
             * longitude : 116
             * latitude : 40
             * universitysummary : 北京大学创办于1898年，初名京师大学堂，是中国第一所国立综合性大学，也是当时中国最高教育行政机关。辛亥革命后，于1912年改为现名。　　作为新文化运动的中心和“五四”运动的策源地，作为中国最早传播马克思主义和民主科学思想的发祥地，作为中国共产党最早的活动基地，北京大学为民族的振兴和解放、国家的建设和发展、社会的文明和进步做出了不可替代的贡献，在中国走向现代化的进程中起到了重要的先锋作用。爱国、进步、民主、科学的传统精神和勤奋、严谨、求实、创新的学风在这里生生不息、代代相传。　　1917年，著名教育家蔡元培
             * universityheat : 22.1 万
             * universityranking : 5
             * specialstudent : 艺术生;
             * logofilename : 84a8bc59-3547-4203-a096-4228c81e8fc3.jpeg
             * universityforareas : null
             * lusy : null
             * luadti : null
             */

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
            private int universitystudentsnum;
            private int longitude;
            private int latitude;
            private String universitysummary;
            private String universityheat;
            private int universityranking;
            private String specialstudent;
            private String logofilename;
            private Object universityforareas;
            private Object lusy;
            private Object luadti;

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

            public int getUniversitystudentsnum() {
                return universitystudentsnum;
            }

            public void setUniversitystudentsnum(int universitystudentsnum) {
                this.universitystudentsnum = universitystudentsnum;
            }

            public int getLongitude() {
                return longitude;
            }

            public void setLongitude(int longitude) {
                this.longitude = longitude;
            }

            public int getLatitude() {
                return latitude;
            }

            public void setLatitude(int latitude) {
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

            public Object getLusy() {
                return lusy;
            }

            public void setLusy(Object lusy) {
                this.lusy = lusy;
            }

            public Object getLuadti() {
                return luadti;
            }

            public void setLuadti(Object luadti) {
                this.luadti = luadti;
            }
        }
    }
}
