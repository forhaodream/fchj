package com.fchj.czglgz.chengzhangguanli_high.activity;

import java.util.List;

public class JsonT {

    /**
     * genid : 1
     * genresname : 公共管理类
     * leval : 1
     * lsmjrs : [{"majid":100,"genid":1,"majname":"行政管理","leval":2},{"majid":101,"genid":1,"majname":"行政管理","leval":2},{"majid":102,"genid":1,"majname":"行政管理","leval":2},{"majid":103,"genid":1,"majname":"行政管理","leval":2},{"majid":104,"genid":1,"majname":"行政管理","leval":2},{"majid":105,"genid":1,"majname":"行政管理","leval":2},{"majid":106,"genid":1,"majname":"行政管理","leval":2},{"majid":107,"genid":1,"majname":"交通管理","leval":2},{"majid":108,"genid":1,"majname":"交通管理","leval":2},{"majid":109,"genid":1,"majname":"公共事业管理","leval":2},{"majid":110,"genid":1,"majname":"公共事业管理","leval":2},{"majid":111,"genid":1,"majname":"公共事业管理","leval":2},{"majid":112,"genid":1,"majname":"公共事业管理","leval":2},{"majid":113,"genid":1,"majname":"公共事业管理","leval":2},{"majid":114,"genid":1,"majname":"公共事业管理","leval":2},{"majid":115,"genid":1,"majname":"公共事业管理","leval":2},{"majid":116,"genid":1,"majname":"公共事业管理","leval":2},{"majid":117,"genid":1,"majname":"公共事业管理","leval":2},{"majid":118,"genid":1,"majname":"公共关系学","leval":2},{"majid":119,"genid":1,"majname":"劳动与社会保障","leval":2},{"majid":120,"genid":1,"majname":"劳动与社会保障","leval":2},{"majid":121,"genid":1,"majname":"交通管理","leval":2},{"majid":122,"genid":1,"majname":"土地资源管理","leval":2},{"majid":123,"genid":1,"majname":"城市管理","leval":2},{"majid":124,"genid":1,"majname":"城市管理","leval":2},{"majid":125,"genid":1,"majname":"海事管理","leval":2},{"majid":126,"genid":1,"majname":"海关管理","leval":2}]
     */

    private int genid;
    private String genresname;
    private int leval;
    private List<LsmjrsBean> lsmjrs;

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

    public List<LsmjrsBean> getLsmjrs() {
        return lsmjrs;
    }

    public void setLsmjrs(List<LsmjrsBean> lsmjrs) {
        this.lsmjrs = lsmjrs;
    }

    public static class LsmjrsBean {
        /**
         * majid : 100
         * genid : 1
         * majname : 行政管理
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
