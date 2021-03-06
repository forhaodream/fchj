package com.fchj.czglgz.chengzhangguanli_high.wodetiwen;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class QuestionDetailModel {
    /**
     * code : 1
     * msg : 获取问题成功
     * data : {"id":20,"questiontitle":"企鹅同意哦大骨鸡啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊","questioncontent":"电话号回复啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊","quizTime":1524708612000,"userId":5,"isanswered":1,"answerer":0,"expaws":{"id":9,"questionid":20,"answercontent":"回复测试","answertime":1524713353000,"answerer":null,"norluser":null}}
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
         * id : 20
         * questiontitle : 企鹅同意哦大骨鸡啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊
         * questioncontent : 电话号回复啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊
         * quizTime : 1524708612000
         * userId : 5
         * isanswered : 1
         * answerer : 0
         * expaws : {"id":9,"questionid":20,"answercontent":"回复测试","answertime":1524713353000,"answerer":null,"norluser":null}
         */

        private int id;
        private String questiontitle;
        private String questioncontent;
        private long quizTime;
        private int userId;
        private int isanswered;
        private int answerer;
        private ExpawsBean expaws;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQuestiontitle() {
            return questiontitle;
        }

        public void setQuestiontitle(String questiontitle) {
            this.questiontitle = questiontitle;
        }

        public String getQuestioncontent() {
            return questioncontent;
        }

        public void setQuestioncontent(String questioncontent) {
            this.questioncontent = questioncontent;
        }

        public long getQuizTime() {
            return quizTime;
        }

        public void setQuizTime(long quizTime) {
            this.quizTime = quizTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getIsanswered() {
            return isanswered;
        }

        public void setIsanswered(int isanswered) {
            this.isanswered = isanswered;
        }

        public int getAnswerer() {
            return answerer;
        }

        public void setAnswerer(int answerer) {
            this.answerer = answerer;
        }

        public ExpawsBean getExpaws() {
            return expaws;
        }

        public void setExpaws(ExpawsBean expaws) {
            this.expaws = expaws;
        }

        public static class ExpawsBean {
            /**
             * id : 9
             * questionid : 20
             * answercontent : 回复测试
             * answertime : 1524713353000
             * answerer : null
             * norluser : null
             */

            private int id;
            private int questionid;
            private String answercontent;
            private long answertime;
            private Object answerer;
            private Object norluser;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getQuestionid() {
                return questionid;
            }

            public void setQuestionid(int questionid) {
                this.questionid = questionid;
            }

            public String getAnswercontent() {
                return answercontent;
            }

            public void setAnswercontent(String answercontent) {
                this.answercontent = answercontent;
            }

            public long getAnswertime() {
                return answertime;
            }

            public void setAnswertime(long answertime) {
                this.answertime = answertime;
            }

            public Object getAnswerer() {
                return answerer;
            }

            public void setAnswerer(Object answerer) {
                this.answerer = answerer;
            }

            public Object getNorluser() {
                return norluser;
            }

            public void setNorluser(Object norluser) {
                this.norluser = norluser;
            }
        }
    }

//    /**
//     * code : 1
//     * msg : 获取问题成功
//     * data : {"id":16,"questiontitle":"孩子发烧老不好","questioncontent":"揍一顿就好了","quizTime":1524539376000,"userId":2,"isanswered":0,"answerer":16,"expaws":null}
//     * value : null
//     */
//
//    private int code;
//    private String msg;
//    private DataBean data;
//    private Object value;
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
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
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
//    public static class DataBean {
//        /**
//         * id : 16
//         * questiontitle : 孩子发烧老不好
//         * questioncontent : 揍一顿就好了
//         * quizTime : 1524539376000
//         * userId : 2
//         * isanswered : 0
//         * answerer : 16
//         * expaws : null
//         */
//
//        private int id;
//        private String questiontitle;
//        private String questioncontent;
//        private long quizTime;
//        private int userId;
//        private int isanswered;
//        private int answerer;
//        private Object expaws;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getQuestiontitle() {
//            return questiontitle;
//        }
//
//        public void setQuestiontitle(String questiontitle) {
//            this.questiontitle = questiontitle;
//        }
//
//        public String getQuestioncontent() {
//            return questioncontent;
//        }
//
//        public void setQuestioncontent(String questioncontent) {
//            this.questioncontent = questioncontent;
//        }
//
//        public long getQuizTime() {
//            return quizTime;
//        }
//
//        public void setQuizTime(long quizTime) {
//            this.quizTime = quizTime;
//        }
//
//        public int getUserId() {
//            return userId;
//        }
//
//        public void setUserId(int userId) {
//            this.userId = userId;
//        }
//
//        public int getIsanswered() {
//            return isanswered;
//        }
//
//        public void setIsanswered(int isanswered) {
//            this.isanswered = isanswered;
//        }
//
//        public int getAnswerer() {
//            return answerer;
//        }
//
//        public void setAnswerer(int answerer) {
//            this.answerer = answerer;
//        }
//
//        public Object getExpaws() {
//            return expaws;
//        }
//
//        public void setExpaws(Object expaws) {
//            this.expaws = expaws;
//        }
//    }

}
