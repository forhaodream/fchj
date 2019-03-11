package com.fchj.czglgz.chengzhangguanli_high.collegedetail;

import java.util.List;

public class CMNModel {

    /**
     * code : 1
     * msg : 获取专业分数记录成功
     * data : [{"major":"城乡规划","subject":"理科","average2017":"--","average2016":689},{"major":"电子信息类","subject":"理科","average2017":"--","average2016":687},{"major":"法学","subject":"文科","average2017":"--","average2016":641},{"major":"工商管理类","subject":"理科","average2017":"--","average2016":702},{"major":"工商管理类","subject":"文科","average2017":"--","average2016":641},{"major":"公共管理类","subject":"文科","average2017":"--","average2016":635},{"major":"国际政治","subject":"文科","average2017":"--","average2016":641},{"major":"化学类","subject":"理科","average2017":"--","average2016":691},{"major":"基础医学","subject":"理科","average2017":"--","average2016":674},{"major":"经济学类","subject":"理科","average2017":"--","average2016":691},{"major":"经济学类","subject":"文科","average2017":"--","average2016":641},{"major":"口腔医学","subject":"理科","average2017":"--","average2016":676},{"major":"理科试验班类","subject":"理科","average2017":"--","average2016":695},{"major":"历史学类","subject":"文科","average2017":"--","average2016":633},{"major":"临床医学","subject":"理科","average2017":"--","average2016":678},{"major":"人文科学试验班","subject":"文科","average2017":"--","average2016":629},{"major":"社会学","subject":"文科","average2017":"--","average2016":638},{"major":"生物科学","subject":"理科","average2017":"--","average2016":687},{"major":"数学类","subject":"理科","average2017":"--","average2016":693},{"major":"文科试验班类","subject":"文科","average2017":"--","average2016":648},{"major":"新闻传播学类","subject":"文科","average2017":"--","average2016":641},{"major":"信息管理与信息系统","subject":"文科","average2017":"--","average2016":640},{"major":"药学","subject":"理科","average2017":"--","average2016":670},{"major":"英语","subject":"理科","average2017":"--","average2016":672},{"major":"英语","subject":"文科","average2017":"--","average2016":640},{"major":"预防医学","subject":"理科","average2017":"--","average2016":672},{"major":"中国语言文学类","subject":"文科","average2017":"--","average2016":636}]
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
         * major : 城乡规划
         * subject : 理科
         * average2017 : --
         * average2016 : 689
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
