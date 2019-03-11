package com.fchj.czglgz.chengzhangguanli_high.volunteermodel;

import java.util.List;

public class CollegePicDetailModel {


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
         * usid : 155470
         * unversityguid : 867fcb9a-f1ae-41e5-a51c-cde7c958a95d
         * unversityname : 北华大学
         * sceneryplace : 比赛
         * extensionname : jpeg
         * specificimg : http://growup-highschool.oss-cn-hangzhou.aliyuncs.com/UniversityScenery/specific/0F37DBD6-8097-4449-9F78-24F9DBFDEDE8_big.jpeg
         * breviaryimg : http://growup-highschool.oss-cn-hangzhou.aliyuncs.com/UniversityScenery/breviary/57397A93-00BB-4C59-A43F-BAFE77AB22D8_small.jpeg
         */

        private int usid;
        private String unversityguid;
        private String unversityname;
        private String sceneryplace;
        private String extensionname;
        private String specificimg;
        private String breviaryimg;

        public int getUsid() {
            return usid;
        }

        public void setUsid(int usid) {
            this.usid = usid;
        }

        public String getUnversityguid() {
            return unversityguid;
        }

        public void setUnversityguid(String unversityguid) {
            this.unversityguid = unversityguid;
        }

        public String getUnversityname() {
            return unversityname;
        }

        public void setUnversityname(String unversityname) {
            this.unversityname = unversityname;
        }

        public String getSceneryplace() {
            return sceneryplace;
        }

        public void setSceneryplace(String sceneryplace) {
            this.sceneryplace = sceneryplace;
        }

        public String getExtensionname() {
            return extensionname;
        }

        public void setExtensionname(String extensionname) {
            this.extensionname = extensionname;
        }

        public String getSpecificimg() {
            return specificimg;
        }

        public void setSpecificimg(String specificimg) {
            this.specificimg = specificimg;
        }

        public String getBreviaryimg() {
            return breviaryimg;
        }

        public void setBreviaryimg(String breviaryimg) {
            this.breviaryimg = breviaryimg;
        }
    }
}
