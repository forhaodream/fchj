package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

public class MBSDetailPutModel {

    /**
     * code : 1
     * msg : 申请成功
     * data : {"afsId":1,"applicant":"浩浩的小姨子","applyTarget":"美国","applySchool":"江南皮革厂","contactPhone":"3838438","attendSchool":"沙县","applyTime":1529660364140,"isHandle":null,"handleTime":null,"osetId":1}
     * value : null
     */

    private int code;
    private String msg;
    private DataBean data;
    private Object value;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static class DataBean {
        /**
         * afsId : 1
         * applicant : 浩浩的小姨子
         * applyTarget : 美国
         * applySchool : 江南皮革厂
         * contactPhone : 3838438
         * attendSchool : 沙县
         * applyTime : 1529660364140
         * isHandle : null
         * handleTime : null
         * osetId : 1
         */

        private int afsId;
        private String applicant;
        private String applyTarget;
        private String applySchool;
        private String contactPhone;
        private String attendSchool;
        private long applyTime;
        private Object isHandle;
        private Object handleTime;
        private int osetId;

        public int getAfsId() {
            return afsId;
        }

        public void setAfsId(int afsId) {
            this.afsId = afsId;
        }

        public String getApplicant() {
            return applicant;
        }

        public void setApplicant(String applicant) {
            this.applicant = applicant;
        }

        public String getApplyTarget() {
            return applyTarget;
        }

        public void setApplyTarget(String applyTarget) {
            this.applyTarget = applyTarget;
        }

        public String getApplySchool() {
            return applySchool;
        }

        public void setApplySchool(String applySchool) {
            this.applySchool = applySchool;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        public String getAttendSchool() {
            return attendSchool;
        }

        public void setAttendSchool(String attendSchool) {
            this.attendSchool = attendSchool;
        }

        public long getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(long applyTime) {
            this.applyTime = applyTime;
        }

        public Object getIsHandle() {
            return isHandle;
        }

        public void setIsHandle(Object isHandle) {
            this.isHandle = isHandle;
        }

        public Object getHandleTime() {
            return handleTime;
        }

        public void setHandleTime(Object handleTime) {
            this.handleTime = handleTime;
        }

        public int getOsetId() {
            return osetId;
        }

        public void setOsetId(int osetId) {
            this.osetId = osetId;
        }
    }
}
