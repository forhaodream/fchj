package com.fchj.czglgz.chengzhangguanli_high.wodetiwen;

import java.util.List;

/**
 * Created by Administrator on 2018/4/26 0026.
 */

public class MyAskReplyModel {
    /**
     * code : 1
     * msg : 获取分类问题列表成功
     * data : [{"id":22,"questiontitle":"致炫","questioncontent":"致炫2","quizTime":1524725063000,"userId":5,"isanswered":1,"answerer":0,"expaws":{"id":12,"questionid":22,"answercontent":"2w","answertime":1524725078000,"answerer":null,"norluser":null}},{"id":21,"questiontitle":"123","questioncontent":"123","quizTime":1524712379000,"userId":5,"isanswered":1,"answerer":0,"expaws":{"id":8,"questionid":21,"answercontent":"1","answertime":1524712391000,"answerer":null,"norluser":null}},{"id":20,"questiontitle":"企鹅同意哦大骨鸡啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊","questioncontent":"电话号回复啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊","quizTime":1524708612000,"userId":5,"isanswered":1,"answerer":0,"expaws":{"id":9,"questionid":20,"answercontent":"回复测试","answertime":1524713353000,"answerer":null,"norluser":null}},{"id":19,"questiontitle":"单身狗很多国家","questioncontent":"吃的九分裤发货发酒疯解放军","quizTime":1524708495000,"userId":5,"isanswered":1,"answerer":0,"expaws":{"id":10,"questionid":19,"answercontent":"123456789","answertime":1524721140000,"answerer":null,"norluser":null}}]
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
         * id : 22
         * questiontitle : 致炫
         * questioncontent : 致炫2
         * quizTime : 1524725063000
         * userId : 5
         * isanswered : 1
         * answerer : 0
         * expaws : {"id":12,"questionid":22,"answercontent":"2w","answertime":1524725078000,"answerer":null,"norluser":null}
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
             * id : 12
             * questionid : 22
             * answercontent : 2w
             * answertime : 1524725078000
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
//     * msg : 获取分类问题列表成功
//     * data : [{"id":17,"questiontitle":"12","questioncontent":"3","quizTime":1524705004000,"userId":5,"isanswered":0,"answerer":0,"expaws":null},{"id":16,"questiontitle":"去哪","questioncontent":"不去","quizTime":1524704470000,"userId":5,"isanswered":0,"answerer":0,"expaws":null}]
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
//         * id : 17
//         * questiontitle : 12
//         * questioncontent : 3
//         * quizTime : 1524705004000
//         * userId : 5
//         * isanswered : 0
//         * answerer : 0
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
