package com.fchj.czglgz.chengzhangguanli_high.major;

import java.util.List;

public class SearchListModel {

    /**
     * code : 1
     * msg : 获取专业名称列表成功
     * data : ["国土测绘与规划","国土资源调查与管理","土地资源管理","土木工程","土木工程检测技术","土耳其语","地籍测绘与土地管理","岩土工程技术","水土保持与荒漠化防治","水土保持技术"]
     * value : null
     */

    private int code;
    private String msg;
    private Object value;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
