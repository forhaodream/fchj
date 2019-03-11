package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import java.util.List;

public class BestMajModel {

    /**
     * code : 1
     * msg : 获取成功
     * data : [{"rmId":1,"interestmajor":"工商管理","majnum":295,"majpercent":"41.90","majnumwoterm":348,"majors":{"majid":"MaJ-00396-Info","coursetype":"本科专业","coursekind":"管理学","majorgenre":"工商管理类","majorcode":"120201K","majorname":"工商管理","studyyears":"四年","relativevct":"报关员,仓储经理,电子商务师,公关经理,国内贸易经理·主管,国内贸易专员·助理,人事经理,首席运营官COO,首席执行官CEO,外贸专员·助理,危机处理专家,项目总监,销售代表,销售主管,薪酬专员,薪金发放员,业务跟单员,职业经理人,总裁助理/总经理助理,总经理,订货员,高科技、加工业和技术产品的批发销售代表,机械设备和零件销售员,零售员,零售主管,批发和制造业销售代表,上门推销员,销售工程师,船舶租赁人员,防损主管,集邮业务员,经纪人","abthsclass":"数学","teachclass":"经济学、工商管理","trainingtarget":"本专业培养具备管理、经济、法律及企业管理方面的知识和能力，能在企、事业单位及政府部门从事管理以及教学、科研方面工作的工商管理学科高级专门人才。","teachingpractice":"包括课程实习、毕业实习，一般安排10\u201412周。","skillsclaim":"本专业学生主要学习管理学、经济学和企业管理的基本理论和基本知识，受到企业管理方法与技巧方面的基本训练，具有分析和解决企业管理问题的基本能力。","isfollow":null}},{"rmId":1,"interestmajor":"计算机科学与技术","majnum":217,"majpercent":"30.82","majnumwoterm":294,"majors":{"majid":"MaJ-00242-Info","coursetype":"本科专业","coursekind":"工学","majorgenre":"计算机类","majorcode":"080901","majorname":"计算机科学与技术","studyyears":"四年","relativevct":"成本核算员,程序员,电子商务师,高校辅导员,个人综合理财顾问,就业指导老师,软件工程师,视听资料采编员,首席技术官CTO首席信息官CIO,数据库管理员,数据库架构师,数码产品开发工程师,通信技术工程师,网站分析师,硬件测试工程师,中学教师中学职业教育教师,立库管理技术人员,计算机操作员,计算机程序师,计算机和信息系统经理,计算机软件应用工程师,计算机系统工程师或设计师计算机系统软件工程师,计算机硬件工程师,计算机支持专家,软件质量鉴定和检验工程师,数据库管理者,数据录入员,网络和计算机系统管理者大学计算机科学教师","abthsclass":null,"teachclass":"计算机科学与技术","trainingtarget":"本专业培养具有良好的科学素养，系统地、较好地掌握计算机科学与技术包括计算机硬件、软件与应用的基本理论、基本知识和基本技能与方法，能在科研部门、教育单位、企业、事业、技术和行政管理部门等单位从事计算机教学、科学研究和应用的计算机科学与技术学科的高级专门科学技术人才","teachingpractice":"包括电子工艺实习、硬件部件设计及调试、计算机基础训练、课程设计、计算机工程实践、生产实习、毕业设计(论文)","skillsclaim":"本专业学生主要学习计算机科学与技术方面的基本理论和基本知识，接受从事研究与应用计算机的基本训练，具有研究和开发计算机系统的基本能力","isfollow":null}},{"rmId":1,"interestmajor":"土木工程","majnum":192,"majpercent":"27.27","majnumwoterm":235,"majors":{"majid":"MaJ-00251-Info","coursetype":"本科专业","coursekind":"工学","majorgenre":"土木类","majorcode":"081001","majorname":"土木工程","studyyears":"四年","relativevct":"CAD辅助土建制图员,监理工程师,造价工程师,钢结构设计技术人员,结构工程师,土木·土建工程师,土木工程技术员,土木工程师,土木绘图师","abthsclass":null,"teachclass":"力学、土木工程、水利工程","trainingtarget":"本专业培养掌握工程力学、流体力学、岩土力学和市政工程学科的基本理论和基本知识，具备从事土木工程的项目规划、设计、研究开发、施工及管理的能力，能在房屋建筑、地下建筑、隧道、道路、桥梁、矿井等的设计、研究、施工、教育、管理、投资、开发部门从事技术或管理工作的高级工程技术人才","teachingpractice":"包括认识实习、测量实习、工程地质实习、专业实习或生产实习、结构课程设计、毕业设计或毕业论文等，一般安排40周左右","skillsclaim":"本专业学生主要学习工程力学、流体力学、岩土力学和市政工程学科的基本理论，受到课程设计、试验仪器操作和现场实习等方面的基本训练，具有从事土木工程的规划、设计、研究、施工、管理的基本能力","isfollow":null}}]
     * value : {"allpeople":8907,"recpet":"80.27"}
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
         * allpeople : 8907
         * recpet : 80.27
         */

        private int allpeople;
        private String recpet;

        public int getAllpeople() {
            return allpeople;
        }

        public void setAllpeople(int allpeople) {
            this.allpeople = allpeople;
        }

        public String getRecpet() {
            return recpet;
        }

        public void setRecpet(String recpet) {
            this.recpet = recpet;
        }
    }

    public static class DataBean {
        /**
         * rmId : 1
         * interestmajor : 工商管理
         * majnum : 295
         * majpercent : 41.90
         * majnumwoterm : 348
         * majors : {"majid":"MaJ-00396-Info","coursetype":"本科专业","coursekind":"管理学","majorgenre":"工商管理类","majorcode":"120201K","majorname":"工商管理","studyyears":"四年","relativevct":"报关员,仓储经理,电子商务师,公关经理,国内贸易经理·主管,国内贸易专员·助理,人事经理,首席运营官COO,首席执行官CEO,外贸专员·助理,危机处理专家,项目总监,销售代表,销售主管,薪酬专员,薪金发放员,业务跟单员,职业经理人,总裁助理/总经理助理,总经理,订货员,高科技、加工业和技术产品的批发销售代表,机械设备和零件销售员,零售员,零售主管,批发和制造业销售代表,上门推销员,销售工程师,船舶租赁人员,防损主管,集邮业务员,经纪人","abthsclass":"数学","teachclass":"经济学、工商管理","trainingtarget":"本专业培养具备管理、经济、法律及企业管理方面的知识和能力，能在企、事业单位及政府部门从事管理以及教学、科研方面工作的工商管理学科高级专门人才。","teachingpractice":"包括课程实习、毕业实习，一般安排10\u201412周。","skillsclaim":"本专业学生主要学习管理学、经济学和企业管理的基本理论和基本知识，受到企业管理方法与技巧方面的基本训练，具有分析和解决企业管理问题的基本能力。","isfollow":null}
         */

        private int rmId;
        private String interestmajor;
        private int majnum;
        private String majpercent;
        private int majnumwoterm;
        private MajorsBean majors;

        public int getRmId() {
            return rmId;
        }

        public void setRmId(int rmId) {
            this.rmId = rmId;
        }

        public String getInterestmajor() {
            return interestmajor;
        }

        public void setInterestmajor(String interestmajor) {
            this.interestmajor = interestmajor;
        }

        public int getMajnum() {
            return majnum;
        }

        public void setMajnum(int majnum) {
            this.majnum = majnum;
        }

        public String getMajpercent() {
            return majpercent;
        }

        public void setMajpercent(String majpercent) {
            this.majpercent = majpercent;
        }

        public int getMajnumwoterm() {
            return majnumwoterm;
        }

        public void setMajnumwoterm(int majnumwoterm) {
            this.majnumwoterm = majnumwoterm;
        }

        public MajorsBean getMajors() {
            return majors;
        }

        public void setMajors(MajorsBean majors) {
            this.majors = majors;
        }

        public static class MajorsBean {
            /**
             * majid : MaJ-00396-Info
             * coursetype : 本科专业
             * coursekind : 管理学
             * majorgenre : 工商管理类
             * majorcode : 120201K
             * majorname : 工商管理
             * studyyears : 四年
             * relativevct : 报关员,仓储经理,电子商务师,公关经理,国内贸易经理·主管,国内贸易专员·助理,人事经理,首席运营官COO,首席执行官CEO,外贸专员·助理,危机处理专家,项目总监,销售代表,销售主管,薪酬专员,薪金发放员,业务跟单员,职业经理人,总裁助理/总经理助理,总经理,订货员,高科技、加工业和技术产品的批发销售代表,机械设备和零件销售员,零售员,零售主管,批发和制造业销售代表,上门推销员,销售工程师,船舶租赁人员,防损主管,集邮业务员,经纪人
             * abthsclass : 数学
             * teachclass : 经济学、工商管理
             * trainingtarget : 本专业培养具备管理、经济、法律及企业管理方面的知识和能力，能在企、事业单位及政府部门从事管理以及教学、科研方面工作的工商管理学科高级专门人才。
             * teachingpractice : 包括课程实习、毕业实习，一般安排10—12周。
             * skillsclaim : 本专业学生主要学习管理学、经济学和企业管理的基本理论和基本知识，受到企业管理方法与技巧方面的基本训练，具有分析和解决企业管理问题的基本能力。
             * isfollow : null
             */

            private String majid;
            private String coursetype;
            private String coursekind;
            private String majorgenre;
            private String majorcode;
            private String majorname;
            private String studyyears;
            private String relativevct;
            private String abthsclass;
            private String teachclass;
            private String trainingtarget;
            private String teachingpractice;
            private String skillsclaim;
            private Object isfollow;

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

            public String getAbthsclass() {
                return abthsclass;
            }

            public void setAbthsclass(String abthsclass) {
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

            public Object getIsfollow() {
                return isfollow;
            }

            public void setIsfollow(Object isfollow) {
                this.isfollow = isfollow;
            }
        }
    }
}
