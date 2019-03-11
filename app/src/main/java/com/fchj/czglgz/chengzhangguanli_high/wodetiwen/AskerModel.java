package com.fchj.czglgz.chengzhangguanli_high.wodetiwen;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class AskerModel {

    /**
     * code : 1
     * msg : 提问成功
     * data : {"id":17,"questiontitle":"aaaaaaaaaa","questioncontent":"aaaaaaaaaa","quizTime":1524554717095,"userId":2,"isanswered":0,"answerer":16,"expaws":null}
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
         * id : 17
         * questiontitle : aaaaaaaaaa
         * questioncontent : aaaaaaaaaa
         * quizTime : 1524554717095
         * userId : 2
         * isanswered : 0
         * answerer : 16
         * expaws : null
         */

        private int id;
        private String questiontitle;
        private String questioncontent;
        private long quizTime;
        private int userId;
        private int isanswered;
        private int answerer;
        private Object expaws;

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

        public Object getExpaws() {
            return expaws;
        }

        public void setExpaws(Object expaws) {
            this.expaws = expaws;
        }
    }
}
