package com.fchj.czglgz.chengzhangguanli_high.mysc;

import java.util.List;

public class ScMajorModel {

    /**
     * code : 1
     * msg : 获取关注列表成功
     * data : [{"mfid":10,"userId":106,"majid":"MaJ-00151-Info","majors":{"majid":"MaJ-00151-Info","coursetype":"本科专业","coursekind":"历史学","majorgenre":"历史学类","majorcode":"060106T","majorname":"外国语言与外国历史","studyyears":"四年","relativevct":"历史学家","abthsclass":null,"teachclass":"暂无","trainingtarget":"本专业的目标是培养素质高、学识宽阔、基础扎实、适应力强的国际文化交流人才，并为相关学科输送高质量的研究人才","teachingpractice":"主要实践教学环节：包括教学实习、毕业设计等","skillsclaim":"本专业要求学生能够比较熟练地运用主修外语进行交流，具备较好的听、说、读、写、译等综合能力。通过世界历史的学习，了解人类文明的一般发展历程和世界历史研究的基本方法、学术史和最新动态，同时对主修国家和地区的历史、文化、政治、社会、军事、经济的概貌与特点有比较深入的认识，有较强的独立研究或实际工作能力","isfollow":null}}]
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
         * mfid : 10
         * userId : 106
         * majid : MaJ-00151-Info
         * majors : {"majid":"MaJ-00151-Info","coursetype":"本科专业","coursekind":"历史学","majorgenre":"历史学类","majorcode":"060106T","majorname":"外国语言与外国历史","studyyears":"四年","relativevct":"历史学家","abthsclass":null,"teachclass":"暂无","trainingtarget":"本专业的目标是培养素质高、学识宽阔、基础扎实、适应力强的国际文化交流人才，并为相关学科输送高质量的研究人才","teachingpractice":"主要实践教学环节：包括教学实习、毕业设计等","skillsclaim":"本专业要求学生能够比较熟练地运用主修外语进行交流，具备较好的听、说、读、写、译等综合能力。通过世界历史的学习，了解人类文明的一般发展历程和世界历史研究的基本方法、学术史和最新动态，同时对主修国家和地区的历史、文化、政治、社会、军事、经济的概貌与特点有比较深入的认识，有较强的独立研究或实际工作能力","isfollow":null}
         */

        private int mfid;
        private int userId;
        private String majid;
        private MajorsBean majors;

        public int getMfid() {
            return mfid;
        }

        public void setMfid(int mfid) {
            this.mfid = mfid;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getMajid() {
            return majid;
        }

        public void setMajid(String majid) {
            this.majid = majid;
        }

        public MajorsBean getMajors() {
            return majors;
        }

        public void setMajors(MajorsBean majors) {
            this.majors = majors;
        }

        public static class MajorsBean {
            /**
             * majid : MaJ-00151-Info
             * coursetype : 本科专业
             * coursekind : 历史学
             * majorgenre : 历史学类
             * majorcode : 060106T
             * majorname : 外国语言与外国历史
             * studyyears : 四年
             * relativevct : 历史学家
             * abthsclass : null
             * teachclass : 暂无
             * trainingtarget : 本专业的目标是培养素质高、学识宽阔、基础扎实、适应力强的国际文化交流人才，并为相关学科输送高质量的研究人才
             * teachingpractice : 主要实践教学环节：包括教学实习、毕业设计等
             * skillsclaim : 本专业要求学生能够比较熟练地运用主修外语进行交流，具备较好的听、说、读、写、译等综合能力。通过世界历史的学习，了解人类文明的一般发展历程和世界历史研究的基本方法、学术史和最新动态，同时对主修国家和地区的历史、文化、政治、社会、军事、经济的概貌与特点有比较深入的认识，有较强的独立研究或实际工作能力
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
            private Object abthsclass;
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

            public Object getIsfollow() {
                return isfollow;
            }

            public void setIsfollow(Object isfollow) {
                this.isfollow = isfollow;
            }
        }
    }
}
