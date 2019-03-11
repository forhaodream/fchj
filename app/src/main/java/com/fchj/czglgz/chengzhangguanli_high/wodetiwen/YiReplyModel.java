package com.fchj.czglgz.chengzhangguanli_high.wodetiwen;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class YiReplyModel {

    /**
     * code : 1
     * msg : 获取分类列表成功
     * data : [{"id":15,"questiontitle":"孩子平常学习好，但是一考试就紧张","questioncontent":"累死了是离开的看到看得开的空对空导弹","quizTime":1524537417000,"userId":1,"isanswered":1,"answerer":16,"expaws":{"id":8,"questionid":15,"answercontent":"徐老师","answertime":1524537452000,"answerer":null,"norluser":null}}]
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
         * id : 15
         * questiontitle : 孩子平常学习好，但是一考试就紧张
         * questioncontent : 累死了是离开的看到看得开的空对空导弹
         * quizTime : 1524537417000
         * userId : 1
         * isanswered : 1
         * answerer : 16
         * expaws : {"id":8,"questionid":15,"answercontent":"徐老师","answertime":1524537452000,"answerer":null,"norluser":null}
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
             * id : 8
             * questionid : 15
             * answercontent : 徐老师
             * answertime : 1524537452000
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
}
