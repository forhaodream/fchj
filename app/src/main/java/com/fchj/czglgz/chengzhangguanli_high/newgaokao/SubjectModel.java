package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import java.io.Serializable;

public class SubjectModel implements Serializable {
    public int subId;
    public String subName;

    public SubjectModel(int subId, String subName) {
        this.subId = subId;
        this.subName = subName;
    }

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
}
