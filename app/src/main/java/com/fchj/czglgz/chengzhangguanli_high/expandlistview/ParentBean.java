package com.fchj.czglgz.chengzhangguanli_high.expandlistview;

import java.util.List;

public class ParentBean {
    /**
     * code : 1
     * msg : 获取高校选科专业列表成功
     * data : [{"genid":1,"genresname":"哲学类","leval":1,"lsmjrs":[{"majid":100,"genid":1,"majname":"哲学","leval":2},{"majid":101,"genid":1,"majname":"逻辑学","leval":2},{"majid":102,"genid":1,"majname":"宗教学","leval":2},{"majid":103,"genid":1,"majname":"伦理学","leval":2}]}]
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
         * genid : 1
         * genresname : 哲学类
         * leval : 1
         * lsmjrs : [{"majid":100,"genid":1,"majname":"哲学","leval":2},{"majid":101,"genid":1,"majname":"逻辑学","leval":2},{"majid":102,"genid":1,"majname":"宗教学","leval":2},{"majid":103,"genid":1,"majname":"伦理学","leval":2}]
         */

        private int genid;
        private String genresname;
        private int leval;
        private List<ChildBean> lsmjrs;

        public int getGenid() {
            return genid;
        }

        public void setGenid(int genid) {
            this.genid = genid;
        }

        public String getGenresname() {
            return genresname;
        }

        public void setGenresname(String genresname) {
            this.genresname = genresname;
        }

        public int getLeval() {
            return leval;
        }

        public void setLeval(int leval) {
            this.leval = leval;
        }

        public List<ChildBean> getLsmjrs() {
            return lsmjrs;
        }

        public void setLsmjrs(List<ChildBean> lsmjrs) {
            this.lsmjrs = lsmjrs;
        }

        public static class LsmjrsBean {
            /**
             * majid : 100
             * genid : 1
             * majname : 哲学
             * leval : 2
             */

            private int majid;
            private int genid;
            private String majname;
            private int leval;

            public int getMajid() {
                return majid;
            }

            public void setMajid(int majid) {
                this.majid = majid;
            }

            public int getGenid() {
                return genid;
            }

            public void setGenid(int genid) {
                this.genid = genid;
            }

            public String getMajname() {
                return majname;
            }

            public void setMajname(String majname) {
                this.majname = majname;
            }

            public int getLeval() {
                return leval;
            }

            public void setLeval(int leval) {
                this.leval = leval;
            }
        }
    }


//    public int genid;
//    public String genresname;
//    public List<ChildBean> lsmjrs;
//
//    public ParentBean() {
//
//    }
//
//    public ParentBean(int genid, String genresname, List<ChildBean> lsmjrs) {
//        this.genid = genid;
//        this.genresname = genresname;
//        this.lsmjrs = lsmjrs;
//    }
//
//    public int getGenid() {
//        return genid;
//    }
//
//    public void setGenid(int genid) {
//        this.genid = genid;
//    }
//
//    public String getGenresname() {
//        return genresname;
//    }
//
//    public void setGenresname(String genresname) {
//        this.genresname = genresname;
//    }
//
//    public List<ChildBean> getLsmjrs() {
//        return lsmjrs;
//    }
//
//    public void setLsmjrs(List<ChildBean> lsmjrs) {
//        this.lsmjrs = lsmjrs;
//    }
}
