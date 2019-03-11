package com.fchj.czglgz.chengzhangguanli_high.travel;

import java.util.List;

public class TravelModel {

    /**
     * code : 1
     * msg : 获取游学信息成功
     * data : [{"tsId":3,"title":"臻享 普吉 7 晚 8 日游","tsimageurl":"/HighShoolManager/image/information/201807040850432058.jpg","tsconwayurl":"https://mp.weixin.qq.com/s/JNtpGaW9qEeHT4YGZ1EPhQ","summary":"Grand West Sands Resort 五钻酒店 大型水上乐园畅玩","payfor":5300,"sdTime":1530665443000},{"tsId":2,"title":"伴我童行 心想狮城-新加坡亲子6日","tsimageurl":"/HighShoolManager/image/information/201807040848336393.jpg","tsconwayurl":"https://mp.weixin.qq.com/s/cp250wGxYdp-sY9cSSqi3Q","summary":"新加坡是一个多种族的国家，历史几经变迁，不同殖民地时期的文化融合贯通，在建筑风格上展现了历史的痕迹。学生们通过视觉效果初识新加坡。鱼尾狮公园（Merlion Park）、滨海艺术中心(Esplanade - Theatres  on the Bay）","payfor":7580,"sdTime":1530665313000},{"tsId":1,"title":"{桂林成长记} \u2014\u2014 青少年夏令营，桂林双飞5日","tsimageurl":"/HighShoolManager/image/information/201807040847018232.jpg","tsconwayurl":"https://mp.weixin.qq.com/s/PcZwoH1Xeip6yQslUuIm5A","summary":"营员需要与小伙伴团结友爱，互相帮助，在轻松的寻宝氛围中进行沟通技能的学习，共同完成考验。PS：在团队前进的路途中，不仅要充分挖掘、发挥个人的力量才能创造个人成绩，更要发挥集体的力量，获得集体的成功。","payfor":4080,"sdTime":1530665221000}]
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
         * tsId : 3
         * title : 臻享 普吉 7 晚 8 日游
         * tsimageurl : /HighShoolManager/image/information/201807040850432058.jpg
         * tsconwayurl : https://mp.weixin.qq.com/s/JNtpGaW9qEeHT4YGZ1EPhQ
         * summary : Grand West Sands Resort 五钻酒店 大型水上乐园畅玩
         * payfor : 5300
         * sdTime : 1530665443000
         */

        private int tsId;
        private String title;
        private String tsimageurl;
        private String tsconwayurl;
        private String summary;
        private int payfor;
        private long sdTime;

        public int getTsId() {
            return tsId;
        }

        public void setTsId(int tsId) {
            this.tsId = tsId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTsimageurl() {
            return tsimageurl;
        }

        public void setTsimageurl(String tsimageurl) {
            this.tsimageurl = tsimageurl;
        }

        public String getTsconwayurl() {
            return tsconwayurl;
        }

        public void setTsconwayurl(String tsconwayurl) {
            this.tsconwayurl = tsconwayurl;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getPayfor() {
            return payfor;
        }

        public void setPayfor(int payfor) {
            this.payfor = payfor;
        }

        public long getSdTime() {
            return sdTime;
        }

        public void setSdTime(long sdTime) {
            this.sdTime = sdTime;
        }
    }
}
