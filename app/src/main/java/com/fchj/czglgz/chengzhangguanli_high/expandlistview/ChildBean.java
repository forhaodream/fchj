package com.fchj.czglgz.chengzhangguanli_high.expandlistview;

public class ChildBean {
    /**
     * "majid":100,
     * "genid":1,
     * "majname":"行政管理",
     * "leval":2
     */
    public int majid;
    public int genid;
    public String majname;
    public int leval;

    public ChildBean() {

    }

    public ChildBean(int majid, int genid, String majname, int leval) {
        this.majid = majid;
        this.genid = genid;
        this.majname = majname;
        this.leval = leval;
    }

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
