package com.fchj.czglgz.chengzhangguanli_high.bean;

import java.util.List;

public class ZhuanKeBig {

    /**
     * code : 1
     * msg : 获取学科门类列表成功
     * data : ["公共管理与服务大类","公安与司法大类","医药卫生大类","教育与体育大类","文化艺术大类","新闻传播大类","旅游大类","电子信息大类","财经商贸大类"]
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
