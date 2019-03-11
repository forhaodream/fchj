package com.fchj.czglgz.chengzhangguanli_high.mysc;

public class GroupBean {
    private String title; //部门名称


    /**
     * @param title 部门名称
     */
    GroupBean(String title) {
        this.title = title;
    }

    //获取部门名称
    public String getTitle() {
        return title;
    }

    //设置部门名称
    public void setTitle(String title) {
        this.title = title;
    }


}
