package com.fchj.czglgz.chengzhangguanli_high.wodetiwen;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class WeiReplyModel {

    /**
     * code : 1
     * msg : 获取分类列表成功
     * data : [{"id":16,"questiontitle":"孩子发烧老不好","questioncontent":"揍一顿就好了","quizTime":1524539376000,"userId":2,"isanswered":0,"answerer":16,"expaws":null},{"id":14,"questiontitle":"孩子平常学习好，但是一考试就紧张","questioncontent":"累死了是离开的看到看得开的空对空导弹","quizTime":1524537379000,"userId":1,"isanswered":0,"answerer":16,"expaws":null}]
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
         * id : 16
         * questiontitle : 孩子发烧老不好
         * questioncontent : 揍一顿就好了
         * quizTime : 1524539376000
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
