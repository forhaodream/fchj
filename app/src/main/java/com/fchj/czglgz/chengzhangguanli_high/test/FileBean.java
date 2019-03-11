package com.fchj.czglgz.chengzhangguanli_high.test;


import com.fchj.czglgz.chengzhangguanli_high.treelist.TreeNodeId;
import com.fchj.czglgz.chengzhangguanli_high.treelist.TreeNodeLabel;
import com.fchj.czglgz.chengzhangguanli_high.treelist.TreeNodePid;

public class FileBean {
    @TreeNodeId
    private int _id;
    @TreeNodePid
    private int parentId;
    @TreeNodeLabel
    private String name;
    //    private String idStr;
//    private String pidStr;
    private long length;
    private String desc;

    public FileBean(int id, int pid, String name) {
        super();
        this._id = id;
        this.parentId = pid;
        this.name = name;
    }

//    public FileBean(int _id, int parentId, String name) {
//        super();
//        this._id = _id;
//        this.parentId = parentId;
//        this.name = name;
//    }

}