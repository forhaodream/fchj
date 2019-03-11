package com.fchj.czglgz.chengzhangguanli_high.model;

import java.util.List;

/**
 * Created by Administrator on 2018/4/20 0020.
 */

public class ShengModel {

    /**
     * code : 1
     * msg : 获取省级数据成功
     * data : [{"id":1,"provinceid":"110000","province":"北京市"},{"id":2,"provinceid":"120000","province":"天津市"},{"id":3,"provinceid":"130000","province":"河北省"},{"id":4,"provinceid":"140000","province":"山西省"},{"id":5,"provinceid":"150000","province":"内蒙古自治区"},{"id":6,"provinceid":"210000","province":"辽宁省"},{"id":7,"provinceid":"220000","province":"吉林省"},{"id":8,"provinceid":"230000","province":"黑龙江省"},{"id":9,"provinceid":"310000","province":"上海市"},{"id":10,"provinceid":"320000","province":"江苏省"},{"id":11,"provinceid":"330000","province":"浙江省"},{"id":12,"provinceid":"340000","province":"安徽省"},{"id":13,"provinceid":"350000","province":"福建省"},{"id":14,"provinceid":"360000","province":"江西省"},{"id":15,"provinceid":"370000","province":"山东省"},{"id":16,"provinceid":"410000","province":"河南省"},{"id":17,"provinceid":"420000","province":"湖北省"},{"id":18,"provinceid":"430000","province":"湖南省"},{"id":19,"provinceid":"440000","province":"广东省"},{"id":20,"provinceid":"450000","province":"广西壮族自治区"},{"id":21,"provinceid":"460000","province":"海南省"},{"id":22,"provinceid":"500000","province":"重庆市"},{"id":23,"provinceid":"510000","province":"四川省"},{"id":24,"provinceid":"520000","province":"贵州省"},{"id":25,"provinceid":"530000","province":"云南省"},{"id":26,"provinceid":"540000","province":"西藏自治区"},{"id":27,"provinceid":"610000","province":"陕西省"},{"id":28,"provinceid":"620000","province":"甘肃省"},{"id":29,"provinceid":"630000","province":"青海省"},{"id":30,"provinceid":"640000","province":"宁夏回族自治区"},{"id":31,"provinceid":"650000","province":"新疆维吾尔自治区"},{"id":32,"provinceid":"710000","province":"台湾省"},{"id":33,"provinceid":"810000","province":"香港特别行政区"},{"id":34,"provinceid":"820000","province":"澳门特别行政区"}]
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
         * provinceid : 110000
         * province : 北京市
         */

        private int id;
        private String provinceid;
        private String province;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProvinceid() {
            return provinceid;
        }

        public void setProvinceid(String provinceid) {
            this.provinceid = provinceid;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }
    }
}