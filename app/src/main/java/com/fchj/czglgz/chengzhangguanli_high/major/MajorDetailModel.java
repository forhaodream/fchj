package com.fchj.czglgz.chengzhangguanli_high.major;

public class MajorDetailModel {
    /**
     * code : 1
     * msg : 获取专业成功
     * data : {"majid":"MaJ-00149-Info","coursetype":"本科专业","coursekind":"历史学","majorgenre":"历史学类","majorcode":"060104","majorname":"文物与博物馆学","studyyears":"四年","relativevct":"博物馆管理员,考古学家","abthsclass":null,"teachclass":"历史学、艺术学","trainingtarget":"本专业培养具备文物学、博物馆学的系统知识，能在政府文物管理和研究机构、各类博物馆和陈列展览单位、考古部门、文物与艺术品经营单位、海关、新闻出版、教育等单位从事文物与博物馆管理、研究工作的博物馆学高级专门人才","teachingpractice":"主要实践教学环节：包括社会调查、业务实习等，一般安排1\u20142个月","skillsclaim":"本专业学生主要学习文物学、博物馆学的基本理论和基础知识，受到历史、艺术、文化和科技等综合知识的基本训练，具有文物、鉴赏、研究和文博事业管理的基本能力","isfollow":0}
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
         * majid : MaJ-00149-Info
         * coursetype : 本科专业
         * coursekind : 历史学
         * majorgenre : 历史学类
         * majorcode : 060104
         * majorname : 文物与博物馆学
         * studyyears : 四年
         * relativevct : 博物馆管理员,考古学家
         * abthsclass : null
         * teachclass : 历史学、艺术学
         * trainingtarget : 本专业培养具备文物学、博物馆学的系统知识，能在政府文物管理和研究机构、各类博物馆和陈列展览单位、考古部门、文物与艺术品经营单位、海关、新闻出版、教育等单位从事文物与博物馆管理、研究工作的博物馆学高级专门人才
         * teachingpractice : 主要实践教学环节：包括社会调查、业务实习等，一般安排1—2个月
         * skillsclaim : 本专业学生主要学习文物学、博物馆学的基本理论和基础知识，受到历史、艺术、文化和科技等综合知识的基本训练，具有文物、鉴赏、研究和文博事业管理的基本能力
         * isfollow : 0
         */

        private String majid;
        private String coursetype;
        private String coursekind;
        private String majorgenre;
        private String majorcode;
        private String majorname;
        private String studyyears;
        private String relativevct;
        private Object abthsclass;
        private String teachclass;
        private String trainingtarget;
        private String teachingpractice;
        private String skillsclaim;
        private int isfollow;

        public String getMajid() {
            return majid;
        }

        public void setMajid(String majid) {
            this.majid = majid;
        }

        public String getCoursetype() {
            return coursetype;
        }

        public void setCoursetype(String coursetype) {
            this.coursetype = coursetype;
        }

        public String getCoursekind() {
            return coursekind;
        }

        public void setCoursekind(String coursekind) {
            this.coursekind = coursekind;
        }

        public String getMajorgenre() {
            return majorgenre;
        }

        public void setMajorgenre(String majorgenre) {
            this.majorgenre = majorgenre;
        }

        public String getMajorcode() {
            return majorcode;
        }

        public void setMajorcode(String majorcode) {
            this.majorcode = majorcode;
        }

        public String getMajorname() {
            return majorname;
        }

        public void setMajorname(String majorname) {
            this.majorname = majorname;
        }

        public String getStudyyears() {
            return studyyears;
        }

        public void setStudyyears(String studyyears) {
            this.studyyears = studyyears;
        }

        public String getRelativevct() {
            return relativevct;
        }

        public void setRelativevct(String relativevct) {
            this.relativevct = relativevct;
        }

        public Object getAbthsclass() {
            return abthsclass;
        }

        public void setAbthsclass(Object abthsclass) {
            this.abthsclass = abthsclass;
        }

        public String getTeachclass() {
            return teachclass;
        }

        public void setTeachclass(String teachclass) {
            this.teachclass = teachclass;
        }

        public String getTrainingtarget() {
            return trainingtarget;
        }

        public void setTrainingtarget(String trainingtarget) {
            this.trainingtarget = trainingtarget;
        }

        public String getTeachingpractice() {
            return teachingpractice;
        }

        public void setTeachingpractice(String teachingpractice) {
            this.teachingpractice = teachingpractice;
        }

        public String getSkillsclaim() {
            return skillsclaim;
        }

        public void setSkillsclaim(String skillsclaim) {
            this.skillsclaim = skillsclaim;
        }

        public int getIsfollow() {
            return isfollow;
        }

        public void setIsfollow(int isfollow) {
            this.isfollow = isfollow;
        }
    }

//    /**
//     * code : 1
//     * msg : 获取专业成功
//     * data : [{"majid":"MaJ-00009-Info","coursetype":"本科专业","coursekind":"经济学","majorgenre":"经济学类","majorcode":"020105T","majorname":"商务经济学","studyyears":"四年","relativevct":"保险保全员,保险公估人,保险核保师,保险精算师,保险调停人,保险业务员,车险定损员,成本估价人员,基金经理,金融监管理财规划师,利率管理人员,市场调研分析人员,外汇贷款审查员,外汇交易员,外汇信贷管理员,信用管理师,银行存款部经理,银行国库业务人员银行货币发行员,银行清算员,银行外汇管理员,银行信贷部经理,银行信贷员,银行信托经理·业务人员,银行信托经理·业务人员银行信用卡经理·业务人员,银行信用卡经理·业务人员,证券发行员,证券交易部门经理,证券交易员,证券经纪人,证券销售员,航线主管","abthsclass":null,"teachclass":"经济学","trainingtarget":"本专业培养具备经济、贸易、文化、传播、法律、管理等方面的基础知识，掌握当代国际文化贸易的现状和发展趋势，熟悉通行的国际文化贸易规则、惯例以及中国对外文化贸易的政策法规，了解文化产业的发展特点和运行机制，能在广播电视机构或其他文化机构、涉外经济贸易部门、企事业单位以及政府机构从事对外文化贸易、管理、研究、发行和策划等方面工作的专门人才","teachingpractice":"略","skillsclaim":"本专业的本科课程为学生提供结合经济和商务方面的学习和实践课程，学生有机会更好的理解现实社会的商务现象，能够根据具体的情况做出切合实际的商务决定","isfollow":0}]
//     * value : null
//     */
//
//    private int code;
//    private String msg;
//    private Object value;
//    private List<DataBean> data;
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public Object getValue() {
//        return value;
//    }
//
//    public void setValue(Object value) {
//        this.value = value;
//    }
//
//    public List<DataBean> getData() {
//        return data;
//    }
//
//    public void setData(List<DataBean> data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        /**
//         * majid : MaJ-00009-Info
//         * coursetype : 本科专业
//         * coursekind : 经济学
//         * majorgenre : 经济学类
//         * majorcode : 020105T
//         * majorname : 商务经济学
//         * studyyears : 四年
//         * relativevct : 保险保全员,保险公估人,保险核保师,保险精算师,保险调停人,保险业务员,车险定损员,成本估价人员,基金经理,金融监管理财规划师,利率管理人员,市场调研分析人员,外汇贷款审查员,外汇交易员,外汇信贷管理员,信用管理师,银行存款部经理,银行国库业务人员银行货币发行员,银行清算员,银行外汇管理员,银行信贷部经理,银行信贷员,银行信托经理·业务人员,银行信托经理·业务人员银行信用卡经理·业务人员,银行信用卡经理·业务人员,证券发行员,证券交易部门经理,证券交易员,证券经纪人,证券销售员,航线主管
//         * abthsclass : null
//         * teachclass : 经济学
//         * trainingtarget : 本专业培养具备经济、贸易、文化、传播、法律、管理等方面的基础知识，掌握当代国际文化贸易的现状和发展趋势，熟悉通行的国际文化贸易规则、惯例以及中国对外文化贸易的政策法规，了解文化产业的发展特点和运行机制，能在广播电视机构或其他文化机构、涉外经济贸易部门、企事业单位以及政府机构从事对外文化贸易、管理、研究、发行和策划等方面工作的专门人才
//         * teachingpractice : 略
//         * skillsclaim : 本专业的本科课程为学生提供结合经济和商务方面的学习和实践课程，学生有机会更好的理解现实社会的商务现象，能够根据具体的情况做出切合实际的商务决定
//         * isfollow : 0
//         */
//
//        private String majid;
//        private String coursetype;
//        private String coursekind;
//        private String majorgenre;
//        private String majorcode;
//        private String majorname;
//        private String studyyears;
//        private String relativevct;
//        private String abthsclass;
//        private String teachclass;
//        private String trainingtarget;
//        private String teachingpractice;
//        private String skillsclaim;
//        private int isfollow;
//
//        public String getMajid() {
//            return majid;
//        }
//
//        public void setMajid(String majid) {
//            this.majid = majid;
//        }
//
//        public String getCoursetype() {
//            return coursetype;
//        }
//
//        public void setCoursetype(String coursetype) {
//            this.coursetype = coursetype;
//        }
//
//        public String getCoursekind() {
//            return coursekind;
//        }
//
//        public void setCoursekind(String coursekind) {
//            this.coursekind = coursekind;
//        }
//
//        public String getMajorgenre() {
//            return majorgenre;
//        }
//
//        public void setMajorgenre(String majorgenre) {
//            this.majorgenre = majorgenre;
//        }
//
//        public String getMajorcode() {
//            return majorcode;
//        }
//
//        public void setMajorcode(String majorcode) {
//            this.majorcode = majorcode;
//        }
//
//        public String getMajorname() {
//            return majorname;
//        }
//
//        public void setMajorname(String majorname) {
//            this.majorname = majorname;
//        }
//
//        public String getStudyyears() {
//            return studyyears;
//        }
//
//        public void setStudyyears(String studyyears) {
//            this.studyyears = studyyears;
//        }
//
//        public String getRelativevct() {
//            return relativevct;
//        }
//
//        public void setRelativevct(String relativevct) {
//            this.relativevct = relativevct;
//        }
//
//        public String getAbthsclass() {
//            return abthsclass;
//        }
//
//        public void setAbthsclass(String abthsclass) {
//            this.abthsclass = abthsclass;
//        }
//
//        public String getTeachclass() {
//            return teachclass;
//        }
//
//        public void setTeachclass(String teachclass) {
//            this.teachclass = teachclass;
//        }
//
//        public String getTrainingtarget() {
//            return trainingtarget;
//        }
//
//        public void setTrainingtarget(String trainingtarget) {
//            this.trainingtarget = trainingtarget;
//        }
//
//        public String getTeachingpractice() {
//            return teachingpractice;
//        }
//
//        public void setTeachingpractice(String teachingpractice) {
//            this.teachingpractice = teachingpractice;
//        }
//
//        public String getSkillsclaim() {
//            return skillsclaim;
//        }
//
//        public void setSkillsclaim(String skillsclaim) {
//            this.skillsclaim = skillsclaim;
//        }
//
//        public int getIsfollow() {
//            return isfollow;
//        }
//
//        public void setIsfollow(int isfollow) {
//            this.isfollow = isfollow;
//        }
//    }

}
