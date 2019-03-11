package com.fchj.czglgz.chengzhangguanli_high.model;

import java.util.List;

/**
 * Created by Administrator on 2018/4/20 0020.
 */

public class QuModel {

    /**
     * code : 1
     * msg : 获取县区级数据成功
     * data : [{"id":1,"areaid":"110101","area":"东城区","cityid":"110100"},{"id":2,"areaid":"110102","area":"西城区","cityid":"110100"},{"id":3,"areaid":"110103","area":"崇文区","cityid":"110100"},{"id":4,"areaid":"110104","area":"宣武区","cityid":"110100"},{"id":5,"areaid":"110105","area":"朝阳区","cityid":"110100"},{"id":6,"areaid":"110106","area":"丰台区","cityid":"110100"},{"id":7,"areaid":"110107","area":"石景山区","cityid":"110100"},{"id":8,"areaid":"110108","area":"海淀区","cityid":"110100"},{"id":9,"areaid":"110109","area":"门头沟区","cityid":"110100"},{"id":10,"areaid":"110111","area":"房山区","cityid":"110100"},{"id":11,"areaid":"110112","area":"通州区","cityid":"110100"},{"id":12,"areaid":"110113","area":"顺义区","cityid":"110100"},{"id":13,"areaid":"110114","area":"昌平区","cityid":"110100"},{"id":14,"areaid":"110115","area":"大兴区","cityid":"110100"},{"id":15,"areaid":"110116","area":"怀柔区","cityid":"110100"},{"id":16,"areaid":"110117","area":"平谷区","cityid":"110100"}]
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
         * id : 1
         * areaid : 110101
         * area : 东城区
         * cityid : 110100
         */

        private int id;
        private String areaid;
        private String area;
        private String cityid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAreaid() {
            return areaid;
        }

        public void setAreaid(String areaid) {
            this.areaid = areaid;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }
    }
}
