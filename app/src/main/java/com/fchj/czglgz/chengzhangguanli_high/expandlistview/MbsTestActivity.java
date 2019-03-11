package com.fchj.czglgz.chengzhangguanli_high.expandlistview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MbsTestActivity extends Activity {
    private List<ParentBean> mParentBeans;
    private List<ChildBean> mChildBeans;
    private ExpandableListView mExpandableListView;
    private Map<String, List<ChildBean>> datasets = new HashMap<>();
    private String[] PList;
    private MyExpandAdapter mMyExpandAdapter;
    TextView parentText;
    private int lastClick = -1;//上一次点击的group的position

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_test);
        mExpandableListView = (ExpandableListView) findViewById(R.id.expand);
        initData();
        mMyExpandAdapter = new MyExpandAdapter();
        mExpandableListView.setAdapter(mMyExpandAdapter);
    }

    /**
     * Gson gson = new Gson();
     * JsonReader reader = new JsonReader(new StringReader(body));
     * mSubListModel = gson.fromJson(reader, SubListModel.class);
     */
    private void initData() {
        mChildBeans = new ArrayList<>();
        mParentBeans = new ArrayList<>();
        ChildBean mChildBean;
        String jsonstr = "[{\"genid\":1,\"genresname\":\"公共管理类\",\"leval\":1,\"lsmjrs\":[{\"majid\":100,\"genid\":1,\"majname\":\"行政管理\",\"leval\":2},{\"majid\":101,\"genid\":1,\"majname\":\"行政管理\",\"leval\":2},{\"majid\":102,\"genid\":1,\"majname\":\"行政管理\",\"leval\":2},{\"majid\":103,\"genid\":1,\"majname\":\"行政管理\",\"leval\":2},{\"majid\":104,\"genid\":1,\"majname\":\"行政管理\",\"leval\":2},{\"majid\":105,\"genid\":1,\"majname\":\"行政管理\",\"leval\":2},{\"majid\":106,\"genid\":1,\"majname\":\"行政管理\",\"leval\":2},{\"majid\":107,\"genid\":1,\"majname\":\"交通管理\",\"leval\":2},{\"majid\":108,\"genid\":1,\"majname\":\"交通管理\",\"leval\":2},{\"majid\":109,\"genid\":1,\"majname\":\"公共事业管理\",\"leval\":2},{\"majid\":110,\"genid\":1,\"majname\":\"公共事业管理\",\"leval\":2},{\"majid\":111,\"genid\":1,\"majname\":\"公共事业管理\",\"leval\":2},{\"majid\":112,\"genid\":1,\"majname\":\"公共事业管理\",\"leval\":2},{\"majid\":113,\"genid\":1,\"majname\":\"公共事业管理\",\"leval\":2},{\"majid\":114,\"genid\":1,\"majname\":\"公共事业管理\",\"leval\":2},{\"majid\":115,\"genid\":1,\"majname\":\"公共事业管理\",\"leval\":2},{\"majid\":116,\"genid\":1,\"majname\":\"公共事业管理\",\"leval\":2},{\"majid\":117,\"genid\":1,\"majname\":\"公共事业管理\",\"leval\":2},{\"majid\":118,\"genid\":1,\"majname\":\"公共关系学\",\"leval\":2},{\"majid\":119,\"genid\":1,\"majname\":\"劳动与社会保障\",\"leval\":2},{\"majid\":120,\"genid\":1,\"majname\":\"劳动与社会保障\",\"leval\":2},{\"majid\":121,\"genid\":1,\"majname\":\"交通管理\",\"leval\":2},{\"majid\":122,\"genid\":1,\"majname\":\"土地资源管理\",\"leval\":2},{\"majid\":123,\"genid\":1,\"majname\":\"城市管理\",\"leval\":2},{\"majid\":124,\"genid\":1,\"majname\":\"城市管理\",\"leval\":2},{\"majid\":125,\"genid\":1,\"majname\":\"海事管理\",\"leval\":2},{\"majid\":126,\"genid\":1,\"majname\":\"海关管理\",\"leval\":2}]},{\"genid\":2,\"genresname\":\"农业经济管理类\",\"leval\":1,\"lsmjrs\":[{\"majid\":200,\"genid\":2,\"majname\":\"农村区域发展\",\"leval\":2},{\"majid\":201,\"genid\":2,\"majname\":\"农村区域发展\",\"leval\":2},{\"majid\":202,\"genid\":2,\"majname\":\"农林经济管理\",\"leval\":2}]},{\"genid\":3,\"genresname\":\"图书情报与档案管理类\",\"leval\":1,\"lsmjrs\":[{\"majid\":300,\"genid\":3,\"majname\":\"图书馆学\",\"leval\":2},{\"majid\":301,\"genid\":3,\"majname\":\"档案学\",\"leval\":2},{\"majid\":302,\"genid\":3,\"majname\":\"信息资源管理\",\"leval\":2}]},{\"genid\":4,\"genresname\":\"工业工程类\",\"leval\":1,\"lsmjrs\":[{\"majid\":400,\"genid\":4,\"majname\":\"质量管理工程\",\"leval\":2},{\"majid\":401,\"genid\":4,\"majname\":\"质量管理工程\",\"leval\":2},{\"majid\":402,\"genid\":4,\"majname\":\"工业工程\",\"leval\":2},{\"majid\":403,\"genid\":4,\"majname\":\"工业工程\",\"leval\":2},{\"majid\":404,\"genid\":4,\"majname\":\"工业工程\",\"leval\":2},{\"majid\":405,\"genid\":4,\"majname\":\"标准化工程\",\"leval\":2}]},{\"genid\":5,\"genresname\":\"工商管理类\",\"leval\":1,\"lsmjrs\":[{\"majid\":500,\"genid\":5,\"majname\":\"市场营销教育\",\"leval\":2},{\"majid\":501,\"genid\":5,\"majname\":\"财务会计教育\",\"leval\":2},{\"majid\":502,\"genid\":5,\"majname\":\"财务管理\",\"leval\":2},{\"majid\":503,\"genid\":5,\"majname\":\"财务管理\",\"leval\":2},{\"majid\":504,\"genid\":5,\"majname\":\"财务管理\",\"leval\":2},{\"majid\":505,\"genid\":5,\"majname\":\"财务管理\",\"leval\":2},{\"majid\":506,\"genid\":5,\"majname\":\"财务管理\",\"leval\":2},{\"majid\":507,\"genid\":5,\"majname\":\"财务管理\",\"leval\":2},{\"majid\":508,\"genid\":5,\"majname\":\"财务管理\",\"leval\":2},{\"majid\":509,\"genid\":5,\"majname\":\"财务管理\",\"leval\":2},{\"majid\":510,\"genid\":5,\"majname\":\"财务管理\",\"leval\":2},{\"majid\":511,\"genid\":5,\"majname\":\"财务管理\",\"leval\":2},{\"majid\":512,\"genid\":5,\"majname\":\"财务管理\",\"leval\":2},{\"majid\":513,\"genid\":5,\"majname\":\"财务管理\",\"leval\":2},{\"majid\":514,\"genid\":5,\"majname\":\"资产评估\",\"leval\":2},{\"majid\":515,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":516,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":517,\"genid\":5,\"majname\":\"人力资源管理\",\"leval\":2},{\"majid\":518,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":519,\"genid\":5,\"majname\":\"人力资源管理\",\"leval\":2},{\"majid\":520,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":521,\"genid\":5,\"majname\":\"人力资源管理\",\"leval\":2},{\"majid\":522,\"genid\":5,\"majname\":\"体育经济与管理\",\"leval\":2},{\"majid\":523,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":524,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":525,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":526,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":527,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":528,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":529,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":530,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":531,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":532,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":533,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":534,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":535,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":536,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":537,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":538,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":539,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":540,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":541,\"genid\":5,\"majname\":\"劳动关系\",\"leval\":2},{\"majid\":542,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":543,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":544,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":545,\"genid\":5,\"majname\":\"工商管理\",\"leval\":2},{\"majid\":546,\"genid\":5,\"majname\":\"国际商务\",\"leval\":2},{\"majid\":547,\"genid\":5,\"majname\":\"工商管理\",\"leval\":2},{\"majid\":548,\"genid\":5,\"majname\":\"国际商务\",\"leval\":2},{\"majid\":549,\"genid\":5,\"majname\":\"国际商务\",\"leval\":2},{\"majid\":550,\"genid\":5,\"majname\":\"国际商务\",\"leval\":2},{\"majid\":551,\"genid\":5,\"majname\":\"国际商务\",\"leval\":2},{\"majid\":552,\"genid\":5,\"majname\":\"国际商务\",\"leval\":2},{\"majid\":553,\"genid\":5,\"majname\":\"审计学\",\"leval\":2},{\"majid\":554,\"genid\":5,\"majname\":\"审计学\",\"leval\":2},{\"majid\":555,\"genid\":5,\"majname\":\"审计学\",\"leval\":2},{\"majid\":556,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2},{\"majid\":557,\"genid\":5,\"majname\":\"审计学\",\"leval\":2},{\"majid\":558,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2},{\"majid\":559,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2},{\"majid\":560,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2},{\"majid\":561,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2},{\"majid\":562,\"genid\":5,\"majname\":\"工商管理\",\"leval\":2},{\"majid\":563,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2},{\"majid\":564,\"genid\":5,\"majname\":\"工商管理\",\"leval\":2},{\"majid\":565,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2},{\"majid\":566,\"genid\":5,\"majname\":\"工商管理\",\"leval\":2},{\"majid\":567,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2},{\"majid\":568,\"genid\":5,\"majname\":\"工商管理\",\"leval\":2},{\"majid\":569,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2},{\"majid\":570,\"genid\":5,\"majname\":\"工商管理\",\"leval\":2},{\"majid\":571,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2},{\"majid\":572,\"genid\":5,\"majname\":\"工商管理\",\"leval\":2},{\"majid\":573,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2},{\"majid\":574,\"genid\":5,\"majname\":\"工商管理\",\"leval\":2},{\"majid\":575,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2},{\"majid\":576,\"genid\":5,\"majname\":\"工商管理\",\"leval\":2},{\"majid\":577,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2},{\"majid\":578,\"genid\":5,\"majname\":\"工商管理\",\"leval\":2},{\"majid\":579,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2},{\"majid\":580,\"genid\":5,\"majname\":\"工商管理\",\"leval\":2},{\"majid\":581,\"genid\":5,\"majname\":\"工商管理\",\"leval\":2},{\"majid\":582,\"genid\":5,\"majname\":\"国际商务\",\"leval\":2},{\"majid\":583,\"genid\":5,\"majname\":\"工商管理\",\"leval\":2},{\"majid\":584,\"genid\":5,\"majname\":\"国际商务\",\"leval\":2},{\"majid\":585,\"genid\":5,\"majname\":\"文化产业管理\",\"leval\":2},{\"majid\":586,\"genid\":5,\"majname\":\"文化产业管理\",\"leval\":2},{\"majid\":587,\"genid\":5,\"majname\":\"物业管理\",\"leval\":2},{\"majid\":588,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2},{\"majid\":589,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2},{\"majid\":590,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2}]},{\"genid\":6,\"genresname\":\"旅游管理类\",\"leval\":1,\"lsmjrs\":[{\"majid\":600,\"genid\":6,\"majname\":\"酒店管理\",\"leval\":2},{\"majid\":601,\"genid\":6,\"majname\":\"酒店管理\",\"leval\":2},{\"majid\":602,\"genid\":6,\"majname\":\"酒店管理\",\"leval\":2},{\"majid\":603,\"genid\":6,\"majname\":\"酒店管理\",\"leval\":2},{\"majid\":604,\"genid\":6,\"majname\":\"酒店管理\",\"leval\":2},{\"majid\":605,\"genid\":6,\"majname\":\"酒店管理\",\"leval\":2},{\"majid\":606,\"genid\":6,\"majname\":\"酒店管理\",\"leval\":2},{\"majid\":607,\"genid\":6,\"majname\":\"酒店管理\",\"leval\":2},{\"majid\":608,\"genid\":6,\"majname\":\"酒店管理\",\"leval\":2},{\"majid\":609,\"genid\":6,\"majname\":\"会展经济与管理\",\"leval\":2},{\"majid\":610,\"genid\":6,\"majname\":\"会展经济与管理\",\"leval\":2},{\"majid\":611,\"genid\":6,\"majname\":\"会展经济与管理\",\"leval\":2},{\"majid\":612,\"genid\":6,\"majname\":\"旅游管理\",\"leval\":2},{\"majid\":613,\"genid\":6,\"majname\":\"旅游管理\",\"leval\":2},{\"majid\":614,\"genid\":6,\"majname\":\"旅游管理\",\"leval\":2},{\"majid\":615,\"genid\":6,\"majname\":\"旅游管理\",\"leval\":2},{\"majid\":616,\"genid\":6,\"majname\":\"旅游管理\",\"leval\":2},{\"majid\":617,\"genid\":6,\"majname\":\"旅游管理\",\"leval\":2},{\"majid\":618,\"genid\":6,\"majname\":\"旅游管理\",\"leval\":2},{\"majid\":619,\"genid\":6,\"majname\":\"旅游管理\",\"leval\":2},{\"majid\":620,\"genid\":6,\"majname\":\"旅游管理\",\"leval\":2},{\"majid\":621,\"genid\":6,\"majname\":\"旅游管理\",\"leval\":2},{\"majid\":622,\"genid\":6,\"majname\":\"旅游管理\",\"leval\":2},{\"majid\":623,\"genid\":6,\"majname\":\"旅游管理\",\"leval\":2},{\"majid\":624,\"genid\":6,\"majname\":\"旅游管理\",\"leval\":2},{\"majid\":625,\"genid\":6,\"majname\":\"旅游管理\",\"leval\":2},{\"majid\":626,\"genid\":6,\"majname\":\"旅游管理\",\"leval\":2},{\"majid\":627,\"genid\":6,\"majname\":\"旅游管理与服务教育\",\"leval\":2}]},{\"genid\":7,\"genresname\":\"物流管理与工程类\",\"leval\":1,\"lsmjrs\":[{\"majid\":700,\"genid\":7,\"majname\":\"采购管理\",\"leval\":2},{\"majid\":701,\"genid\":7,\"majname\":\"物流管理\",\"leval\":2},{\"majid\":702,\"genid\":7,\"majname\":\"物流管理\",\"leval\":2},{\"majid\":703,\"genid\":7,\"majname\":\"物流管理\",\"leval\":2},{\"majid\":704,\"genid\":7,\"majname\":\"物流管理\",\"leval\":2},{\"majid\":705,\"genid\":7,\"majname\":\"物流管理\",\"leval\":2},{\"majid\":706,\"genid\":7,\"majname\":\"物流管理\",\"leval\":2},{\"majid\":707,\"genid\":7,\"majname\":\"物流工程\",\"leval\":2},{\"majid\":708,\"genid\":7,\"majname\":\"物流工程\",\"leval\":2},{\"majid\":709,\"genid\":7,\"majname\":\"物流工程\",\"leval\":2},{\"majid\":710,\"genid\":7,\"majname\":\"物流管理\",\"leval\":2},{\"majid\":711,\"genid\":7,\"majname\":\"物流管理\",\"leval\":2}]},{\"genid\":8,\"genresname\":\"电子商务类\",\"leval\":1,\"lsmjrs\":[{\"majid\":800,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":801,\"genid\":8,\"majname\":\"电子商务及法律\",\"leval\":2},{\"majid\":802,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":803,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":804,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":805,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":806,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":807,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":808,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":809,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":810,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":811,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":812,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":813,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":814,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":815,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":816,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":817,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":818,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":819,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2},{\"majid\":820,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2}]},{\"genid\":9,\"genresname\":\"管理科学与工程类\",\"leval\":1,\"lsmjrs\":[{\"majid\":900,\"genid\":9,\"majname\":\"保密管理\",\"leval\":2},{\"majid\":901,\"genid\":9,\"majname\":\"管理科学\",\"leval\":2},{\"majid\":902,\"genid\":9,\"majname\":\"信息管理与信息系统\",\"leval\":2},{\"majid\":903,\"genid\":9,\"majname\":\"信息管理与信息系统\",\"leval\":2},{\"majid\":904,\"genid\":9,\"majname\":\"信息管理与信息系统\",\"leval\":2},{\"majid\":905,\"genid\":9,\"majname\":\"信息管理与信息系统\",\"leval\":2},{\"majid\":906,\"genid\":9,\"majname\":\"信息管理与信息系统\",\"leval\":2},{\"majid\":907,\"genid\":9,\"majname\":\"工程管理\",\"leval\":2},{\"majid\":908,\"genid\":9,\"majname\":\"工程管理\",\"leval\":2},{\"majid\":909,\"genid\":9,\"majname\":\"工程管理\",\"leval\":2},{\"majid\":910,\"genid\":9,\"majname\":\"工程管理\",\"leval\":2},{\"majid\":911,\"genid\":9,\"majname\":\"工程管理\",\"leval\":2},{\"majid\":912,\"genid\":9,\"majname\":\"工程造价\",\"leval\":2},{\"majid\":913,\"genid\":9,\"majname\":\"工程造价\",\"leval\":2},{\"majid\":914,\"genid\":9,\"majname\":\"工程造价\",\"leval\":2},{\"majid\":915,\"genid\":9,\"majname\":\"工程造价\",\"leval\":2},{\"majid\":916,\"genid\":9,\"majname\":\"工程造价\",\"leval\":2},{\"majid\":917,\"genid\":9,\"majname\":\"房地产开发与管理\",\"leval\":2}]}]";
        String aaaaa = "[{\"genid\":1,\"genresname\":\"哲学类\",\"leval\":1,\"lsmjrs\":[{\"majid\":100,\"genid\":1,\"majname\":\"宗教学\",\"leval\":2},{\"majid\":101,\"genid\":1,\"majname\":\"逻辑学\",\"leval\":2},{\"majid\":102,\"genid\":1,\"majname\":\"哲学\",\"leval\":2},{\"majid\":103,\"genid\":1,\"majname\":\"伦理学\",\"leval\":2}]}]";
        String ppp = "[{\"genid\":1,\"genresname\":\"公共管理类\",\"leval\":1,\"lsmjrs\":[{\"majid\":100,\"genid\":1,\"majname\":\"公共事业管理\",\"leval\":2},{\"majid\":101,\"genid\":1,\"majname\":\"公共关系学\",\"leval\":2},{\"majid\":102,\"genid\":1,\"majname\":\"海事管理\",\"leval\":2},{\"majid\":103,\"genid\":1,\"majname\":\"交通管理\",\"leval\":2},{\"majid\":104,\"genid\":1,\"majname\":\"海关管理\",\"leval\":2},{\"majid\":105,\"genid\":1,\"majname\":\"城市管理\",\"leval\":2},{\"majid\":106,\"genid\":1,\"majname\":\"土地资源管理\",\"leval\":2},{\"majid\":107,\"genid\":1,\"majname\":\"劳动与社会保障\",\"leval\":2},{\"majid\":108,\"genid\":1,\"majname\":\"行政管理\",\"leval\":2}]},{\"genid\":2,\"genresname\":\"农业经济管理类\",\"leval\":1,\"lsmjrs\":[{\"majid\":200,\"genid\":2,\"majname\":\"农村区域发展\",\"leval\":2},{\"majid\":201,\"genid\":2,\"majname\":\"农林经济管理\",\"leval\":2}]},{\"genid\":3,\"genresname\":\"图书情报与档案管理类\",\"leval\":1,\"lsmjrs\":[{\"majid\":300,\"genid\":3,\"majname\":\"信息资源管理\",\"leval\":2},{\"majid\":301,\"genid\":3,\"majname\":\"档案学\",\"leval\":2},{\"majid\":302,\"genid\":3,\"majname\":\"图书馆学\",\"leval\":2}]},{\"genid\":4,\"genresname\":\"工业工程类\",\"leval\":1,\"lsmjrs\":[{\"majid\":400,\"genid\":4,\"majname\":\"质量管理工程\",\"leval\":2},{\"majid\":401,\"genid\":4,\"majname\":\"标准化工程\",\"leval\":2},{\"majid\":402,\"genid\":4,\"majname\":\"工业工程\",\"leval\":2}]},{\"genid\":5,\"genresname\":\"工商管理类\",\"leval\":1,\"lsmjrs\":[{\"majid\":500,\"genid\":5,\"majname\":\"人力资源管理\",\"leval\":2},{\"majid\":501,\"genid\":5,\"majname\":\"国际商务\",\"leval\":2},{\"majid\":502,\"genid\":5,\"majname\":\"财务管理\",\"leval\":2},{\"majid\":503,\"genid\":5,\"majname\":\"会计学\",\"leval\":2},{\"majid\":504,\"genid\":5,\"majname\":\"市场营销教育\",\"leval\":2},{\"majid\":505,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2},{\"majid\":506,\"genid\":5,\"majname\":\"财务会计教育\",\"leval\":2},{\"majid\":507,\"genid\":5,\"majname\":\"市场营销\",\"leval\":2},{\"majid\":508,\"genid\":5,\"majname\":\"体育经济与管理\",\"leval\":2},{\"majid\":509,\"genid\":5,\"majname\":\"劳动关系\",\"leval\":2},{\"majid\":510,\"genid\":5,\"majname\":\"工商管理\",\"leval\":2},{\"majid\":511,\"genid\":5,\"majname\":\"文化产业管理\",\"leval\":2},{\"majid\":512,\"genid\":5,\"majname\":\"物业管理\",\"leval\":2},{\"majid\":513,\"genid\":5,\"majname\":\"资产评估\",\"leval\":2},{\"majid\":514,\"genid\":5,\"majname\":\"审计学\",\"leval\":2}]},{\"genid\":6,\"genresname\":\"旅游管理类\",\"leval\":1,\"lsmjrs\":[{\"majid\":600,\"genid\":6,\"majname\":\"酒店管理\",\"leval\":2},{\"majid\":601,\"genid\":6,\"majname\":\"旅游管理\",\"leval\":2},{\"majid\":602,\"genid\":6,\"majname\":\"旅游管理与服务教育\",\"leval\":2},{\"majid\":603,\"genid\":6,\"majname\":\"会展经济与管理\",\"leval\":2}]},{\"genid\":7,\"genresname\":\"物流管理与工程类\",\"leval\":1,\"lsmjrs\":[{\"majid\":700,\"genid\":7,\"majname\":\"采购管理\",\"leval\":2},{\"majid\":701,\"genid\":7,\"majname\":\"物流工程\",\"leval\":2},{\"majid\":702,\"genid\":7,\"majname\":\"物流管理\",\"leval\":2}]},{\"genid\":8,\"genresname\":\"电子商务类\",\"leval\":1,\"lsmjrs\":[{\"majid\":800,\"genid\":8,\"majname\":\"电子商务及法律\",\"leval\":2},{\"majid\":801,\"genid\":8,\"majname\":\"电子商务\",\"leval\":2}]},{\"genid\":9,\"genresname\":\"管理科学与工程类\",\"leval\":1,\"lsmjrs\":[{\"majid\":900,\"genid\":9,\"majname\":\"信息管理与信息系统\",\"leval\":2},{\"majid\":901,\"genid\":9,\"majname\":\"管理科学\",\"leval\":2},{\"majid\":902,\"genid\":9,\"majname\":\"保密管理\",\"leval\":2},{\"majid\":903,\"genid\":9,\"majname\":\"工程造价\",\"leval\":2},{\"majid\":904,\"genid\":9,\"majname\":\"房地产开发与管理\",\"leval\":2},{\"majid\":905,\"genid\":9,\"majname\":\"工程管理\",\"leval\":2}]}]";

//        Gson gson = new Gson();
//        JsonReader reader = new JsonReader(new StringReader(jsonstr));
//        mParentBeans = JSON.parseArray(ppp, ParentBean.class);
//        PList = new String[mParentBeans.size()];
//        for (int i = 0; i < PList.length; i++) {
//            PList[i] = mParentBeans.get(i).getGenresname();
//            for (int j = 0; j < mParentBeans.get(i).getLsmjrs().size(); j++) {
//                datasets.put(PList[i], mParentBeans.get(i).getLsmjrs());
//            }
//        }

    }

    public class MyExpandAdapter extends BaseExpandableListAdapter {
        @Override
        public int getGroupCount() {
            if (datasets == null) {
                return 0;
            }
            return datasets.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            if (datasets.get(PList[groupPosition]) == null) {
                return 0;
            }
            return datasets.get(PList[groupPosition]).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return datasets.get(PList[groupPosition]);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return datasets.get(PList[groupPosition]).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) MbsTestActivity
                        .this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_parent_bean, null);
            }
            view.setTag(R.layout.item_parent_bean, groupPosition);
            view.setTag(R.layout.item_child_bean, -1);
            parentText = (TextView) view.findViewById(R.id.item_parent_tv);
            // ImageView parent_img = (ImageView) view.findViewById(R.id.parent_img);
//            设置展开和收缩的文字颜色
            if (isExpanded) {
                parentText.setTextColor(Color.parseColor("#2FD0B5"));
                // parent_img.setImageResource(R.drawable.star_yellow);
            } else {
                parentText.setTextColor(Color.BLACK);
                // parent_img.setImageResource(R.drawable.star_grey);
            }
            parentText.setText(PList[groupPosition]);
            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) MbsTestActivity
                        .this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_child_bean, null);
            }
            view.setTag(R.layout.item_parent_bean, groupPosition);
            view.setTag(R.layout.item_child_bean, childPosition);
            TextView text = (TextView) view.findViewById(R.id.item_child_tv);
            text.setText(datasets.get(PList[groupPosition]).get(childPosition).getMajname());
            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
