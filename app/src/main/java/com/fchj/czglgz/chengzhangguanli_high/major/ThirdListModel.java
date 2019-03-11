package com.fchj.czglgz.chengzhangguanli_high.major;

import java.util.List;

public class ThirdListModel {

    /**
     * code : 1
     * msg : 获取专业名称列表成功
     * data : ["草业科学"]
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
