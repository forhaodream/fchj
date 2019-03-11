package com.fchj.czglgz.chengzhangguanli_high.util;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class Url {
    // 测试接口
    //  public static final String url = "http://192.168.0.108:8080/HSGUM/";
    // 真实接口
    public static final String url = "http://47.94.165.33:8080/HSGUM/";
    public static final String fileUrl = "http://47.94.165.33:8080";
    // 注册
    public static final String zhuCeUrl = url + "JsonService/Normaluser/insertNormaluser.json";
    // 用户注册 验证码
    public static final String zcYAMUrl = url + "JsonService/Normaluser/sendRegisterNormaluserMsgCode.json";
    // 忘记密码
    public static final String forgetUrl = url + "JsonService/Normaluser/getBackpassword.json";
    // 修改密码
    public static final String xiugaiPsdUrl = url + "JsonService/Normaluser/updateNormaluserpassword.json";
    // 发送验证码
    public static final String sendYZMUrl = url + "";
    // 登录
    public static final String loginUrl = url + "JsonService/Normaluser/normaluserLogin.json";
    // url
    public static final String bannerUrl = url + "JsonService/Banner/getBannerList.json";
    // 资讯
    public static final String zixunUrl = url + "JsonService/Information/getAllInformationTop.json";
    // 全部资讯
    public static final String zxAllUrl = url + "JsonService/Information/getAllInformation.json";
    // 资讯 分页加载
    public static final String pageZXUrl = url + "JsonService/Information/getInformationByInfoType.json";
    // 资讯展示
    public static final String zxDetailUrl = url + "JsonService/Information/getInformationById.json";
    // 家长课堂
    public static final String JZUrl = url + "JsonService/Parentclass/getParentclassListByType.json";
    public static final String JZDetailUrl = url + "JsonService/Parentclass/getParentclassById.json";
    // 修改个人信息
    public static final String xiugaiUrl = url + "JsonService/Normaluser/updateNormaluserinfo.json";
    // 修改头像
    public static final String headUrl = url + "JsonService/Normaluser/updateNormalheadimgByString.json";
    //  修改头像
    public static final String headPicUrl = url + "JsonService/Normaluser/updateNormalheadimg.json";

    // 展示个人信息
    public static final String showInfoUrl = url + "JsonService/Normaluser/getNormaluserById.json";
    //省
    public static final String shengUrl = url + "JsonService/Province/getAllProvince.json";
    // 市
    public static final String shiUrl = url + "JsonService/Cities/getCityByProvince.json";
    // 区
    public static final String quUrl = url + "JsonService/Areas/getAreasByCity.json";
    //学校
    public static final String schoolUrl = url + " JsonService/School/getAllSchoolByTypeAndAreas.json";

    // 学霸来了
    // 列表
    public static final String xuebaListUrl = url + "JsonService/Scholar/getScholarByType.json";
    public static final String xuebaDetailUrl = url + "JsonService/Scholar/getScholarById.json";
    // 专家解答
    public static final String zjListUrl = url + "JsonService/Normaluser/getNormaluserListByType.json";
    // 校园动态
    public static final String schoolNewsUrl = url + "JsonService/CampusNews/getCampusNewsListByType.json";
    // 校园动态详细
    public static final String schoolDetialUrl = url + "JsonService/CampusNews/getCampusNewsById.json";
    // 我的提问
    // 已回复
    public static final String yiReplyUrl = url + "JsonService/ParentQuestion/getAnsExpawsListByAner.json";
    // 未回复
    public static final String weiReplyUrl = url + "JsonService/ParentQuestion/getAnsExpawsListByAner.json";
    //问题详情
    public static final String questionDetailUrl = url + "JsonService/ParentQuestion/getQuestionById.json";
    // 向专家提出问题
    public static final String askerUrl = url + "JsonService/ParentQuestion/AskExpawsQuestion.json";
    // 我的提问
    public static final String myAskWeiUrl = url + "JsonService/ParentQuestion/getAnsExpawsListByuId.json";
    public static final String myAskYiUrl = url + "JsonService/ParentQuestion/getAnsExpawsListByuId.json";
    // 专家回答提问
    public static final String zjReplyUrl = url + "JsonService/ParentQuestion/AnsExpawsQuestion.json";
    // 家长课堂
    public static final String jzNoVideoUrl = url + "JsonService/Parentclass/getParentclassById.json";
    public static final String jzVideoUrl = url + "JsonService/Parentclass/getParentclassById.json";
    // 升级
    public static final String updateUrl = url + "JsonService/SystemInfo/getSysteminfo.json";
    // 根据学校名查询录取分数列表
    public static final String collegeDetailUrl = url + "JsonService/AdmitInfo/getAdmitByUname.json";
    //根据专业名及分数区间查询录取分数列表
    public static final String majorListUrl = url + "JsonService/AdmitInfo/getAdmitByScoreOrMajor.json";
    // 根据专业名及分数区间查询录取分数列表
    public static final String numberListUrl = url + "JsonService/AdmitInfo/getAdmitByScoreOrMajor.json";
    // 获取学校详细信息
    public static final String collegeListDetailUrl = url + "JsonService/University/getUniversityByGuId.json";

    // 获取全部学校
    public static final String allCollegeUrl = url + "JsonService/UniversityRanking/getAllUniversity.json";
    // 查询学校排名列表
    public static final String allCollegeByNameUrl = url + "JsonService/University/getUniversityByUname.json";
    // 获取专业列表
    public static final String getMajorUrl = url + "JsonService/AdmitInfo/getMajorListByMajor.json";
    // 获取学校列表
    public static final String getCollegeUrl = url + "JsonService/University/getAllUniversityByUname.json";

    public static final String getCollegeByKindsUrl = url + "JsonService/University/getAllUniversityByAreasSubjectType.json";

    // 获取学校风景列表
    public static final String getSceneryUrl = url + "JsonService/University/getUniversitySceneryByGuId.json";
    public static final String getSceneryAUrl = url + "JsonService/University/getUniversitySceneryByGuIdForA.json";

    // 根据学校名称获取专业分数
    public static final String getMajorByNumUrl = url + "JsonService/University/getUniversityAdmitByUnames.json";
    // 微信登录
    public static final String wxLoginUrl = url + "JsonService/Normaluser/WeChatNormaluserLogin.json";
    // 根据类型获取职业列表
    public static final String getVocationUrl = url + "JsonService/Vocation/getVocationListByType.json";
    // 根据ID获取职业详细信息
    public static final String getVocationDetailUrl = url + "JsonService/Vocation/getVocationById.json";
    // 修改职业关注状态
    public static final String addScUrl = url + "JsonService/VocationFollow/updVocFollowState.json";
    // 修改专业关注状态
    public static final String addMajScUrl = url + "JsonService/MajorFollow/updMajFollowState.json";
    // 根据uid获取关注列表
    public static final String scListUrl = url + "JsonService/VocationFollow/getVocFollowListByuId.json";
    // 根据uid获取专业关注列表
    public static final String zyScListUrl = url + "JsonService/MajorFollow/getMajFollowListByuId.json";
    //  根据学历获取专业列表
    public static final String getZyListUrl = url + "JsonService/Majorinfo/getMajorInfoArchitect.json";

    //
    // 根据专业类型获取专业名称列表
    public static final String getThirdList = url + "JsonService/Majorinfo/getMajnameListByMajGenre.json";
    // 根据专业名称获取详细
    public static final String getZyDetailUrl = url + "JsonService/Majorinfo/getMajorInfoByMajName.json";
    public static final String getZyDetailNewUrl = url + "JsonService/Majorinfo/getMajorByjNameAType.json";
    // 根据专业查询开设院校
    public static final String getAboutCollegeUrl = url + "JsonService/Majorinfo/getAllUniversityByMajName.json";
    // 根据id查询国外院校
    public static final String getUniInfoByIdUrl = url + "JsonService/AbroadUniversity/getAbroadUniversityById.json";
    // 根据学校名称查询国外学校列表
    public static final String getOsUniByNameUrl = url + "JsonService/AbroadUniversity/getAbroadUniversityByName.json";
    // 海外留学列表
    public static final String getOsCollegeUrl = url + "JsonService/AbroadUniversity/getAllAbroadUniversityByTerms.json";
    // 提交申请
    public static final String applyForStudyUrl = url + "JsonService/ApplyForStudy/AddApplyForStudy.json";
    // 获取外国专家信息
    public static final String getOsZjUrl = url + "JsonService/ExpertCoCountry/getExpertCoCountryByCountry.json";
    // 专业搜索
    public static final String searchMajorUrl = url + "JsonService/Majorinfo/getMajnameListByMajNames.json";
    // 游学列表
    public static final String travelstudyUrl = url + "JsonService/TravelStudy/getTravelStudyList.json";
    //游学展示
    public static final String travelDetailUrl = url + " JsonService/TravelStudy/getTravelStudyByID.json";
    //根据选择科目获取专业分布数据
    public static final String majBySubUrl = url + "JsonService/UnviersityRecruitAdmitMajor/getMajnameListByMajNames.json";
    //
    public static final String mbsDetailUrl = url + "JsonService/UnviersityRecruitAdmitMajor/getMajorCourseListByKind.json";
    // 新高考模块--获取专业关注列表
    public static final String myMajScListUrl = url + "JsonService/MajorAdmitInterest/getMajorAdmitInterestListByuserId.json";
    // 添加选课关注专业
    public static final String addMajorBySubScUrl = url + "JsonService/MajorAdmitInterest/putMajorAdmitInterest.json";
    // 生成最佳选科方案
    public static final String getBestMajUrl = url + "JsonService/UnviersityRecruitAdmitMajor/getMajorPercentByInterest.json";
    // 根据专业对应科目比例
    public static final String majFromSubUrl = url + "JsonService/UnviersityRecruitAdmitMajor/getSubjectRatioForMajor.json";
    // 根据ID批量删除关注
    public static final String deleteMajListUrl = url + "JsonService/MajorAdmitInterest/reMoveMajorAdmitInterestBybatch.json";
}
