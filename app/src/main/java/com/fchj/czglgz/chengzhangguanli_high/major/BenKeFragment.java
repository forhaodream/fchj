package com.fchj.czglgz.chengzhangguanli_high.major;

import android.accounts.AbstractAccountAuthenticator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.test.FileBean;
import com.fchj.czglgz.chengzhangguanli_high.test.SimpleTreeAdapter;
import com.fchj.czglgz.chengzhangguanli_high.treelist.Node;
import com.fchj.czglgz.chengzhangguanli_high.treelist.TreeListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class BenKeFragment extends Fragment {
    private List<FileBean> mDatas = new ArrayList<FileBean>();
    private ListView mTree;
    private TreeListViewAdapter mAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_benke, null);
        initDatas();
        mTree = (ListView) view.findViewById(R.id.frag_benke_lv);
        try {
            mAdapter = new SimpleTreeAdapter<FileBean>(mTree, getActivity(), mDatas, 0);

            mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                @Override
                public void onClick(Node node, int position) {
                    if (node.isLeaf()) {
                        Intent toThreeList = new Intent(getActivity(), ThirdListActivity.class);
                        toThreeList.putExtra("zy_name", node.getName());
                        toThreeList.putExtra("zy_title", "专业列表");
                        toThreeList.putExtra("zy_type", "本科专业");
                        startActivity(toThreeList);
                    }
                }

            });

            mTree.setAdapter(mAdapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return view;
    }

    /**
     * id , pid , label , 其他属性
     */
    private void initDatas() {

        // 本科大类 1
        mDatas.add(new FileBean(1, 0, "农学"));
        mDatas.add(new FileBean(2, 0, "医学"));
        mDatas.add(new FileBean(3, 0, "历史学"));
        mDatas.add(new FileBean(4, 0, "哲学"));
        mDatas.add(new FileBean(5, 0, "工学"));
        mDatas.add(new FileBean(6, 0, "教育学"));
        mDatas.add(new FileBean(7, 0, "文学"));
        mDatas.add(new FileBean(8, 0, "法学"));
        mDatas.add(new FileBean(9, 0, "理学"));
        mDatas.add(new FileBean(10, 0, "管理学"));
        mDatas.add(new FileBean(11, 0, "经济学"));
        mDatas.add(new FileBean(12, 0, "艺术学"));

        // 本科科目类--第二层
        // 农学 1-1
        mDatas.add(new FileBean(13, 1, "动物医学类"));
        mDatas.add(new FileBean(14, 1, "动物生产类"));
        mDatas.add(new FileBean(15, 1, "林学类"));
        mDatas.add(new FileBean(16, 1, "植物生产类"));
        mDatas.add(new FileBean(17, 1, "水产类"));
        mDatas.add(new FileBean(18, 1, "自然保护与环境生态类"));
        mDatas.add(new FileBean(19, 1, "草学类"));
        // 2-1
        mDatas.add(new FileBean(19, 2, "中医学类"));
        mDatas.add(new FileBean(19, 2, "中药学类"));
        mDatas.add(new FileBean(19, 2, "中西医结合类"));
        mDatas.add(new FileBean(19, 2, "临床医学类"));
        mDatas.add(new FileBean(19, 2, "公共卫生与预防医学类"));
        mDatas.add(new FileBean(19, 2, "医学技术类"));
        mDatas.add(new FileBean(19, 2, "口腔医学类"));
        mDatas.add(new FileBean(19, 2, "基础医学类"));
        mDatas.add(new FileBean(19, 2, "护理学类"));
        mDatas.add(new FileBean(19, 2, "法医学类"));
        mDatas.add(new FileBean(19, 2, "药学类"));
        //3-1
        mDatas.add(new FileBean(19, 3, "历史学类"));

        // 4-1
        mDatas.add(new FileBean(19, 4, "哲学类"));
        // 5-1
        mDatas.add(new FileBean(19, 5, "交通运输类"));
        mDatas.add(new FileBean(19, 5, "公安技术类"));
        mDatas.add(new FileBean(19, 5, "兵器类"));
        mDatas.add(new FileBean(19, 5, "农业工程类"));
        mDatas.add(new FileBean(19, 5, "力学类"));
        mDatas.add(new FileBean(19, 5, "化工与制药类"));
        mDatas.add(new FileBean(19, 5, "土木类"));
        mDatas.add(new FileBean(19, 5, "地质类"));
        mDatas.add(new FileBean(19, 5, "安全科学与工程类"));
        mDatas.add(new FileBean(19, 5, "建筑类学"));
        mDatas.add(new FileBean(19, 5, "机械类"));
        mDatas.add(new FileBean(19, 5, "材料类"));
        mDatas.add(new FileBean(19, 5, "林业工程类"));
        mDatas.add(new FileBean(19, 5, "核工程类"));
        mDatas.add(new FileBean(19, 5, "水利类"));
        mDatas.add(new FileBean(19, 5, "测绘类"));
        mDatas.add(new FileBean(19, 5, "海洋工程类"));
        mDatas.add(new FileBean(19, 5, "生物医学工程类"));
        mDatas.add(new FileBean(19, 5, "生物工程类"));
        mDatas.add(new FileBean(19, 5, "电气类"));
        mDatas.add(new FileBean(19, 5, "矿业类"));
        mDatas.add(new FileBean(19, 5, "纺织类"));
        mDatas.add(new FileBean(19, 5, "能源动力类"));
        mDatas.add(new FileBean(19, 5, "自动化类"));
        mDatas.add(new FileBean(19, 5, "航空航天类"));
        mDatas.add(new FileBean(19, 5, "计算机类"));
        mDatas.add(new FileBean(19, 5, "轻工类"));
        mDatas.add(new FileBean(19, 5, "食品科学与工程类"));
        // 6-1
        mDatas.add(new FileBean(19, 6, "体育学类"));
        mDatas.add(new FileBean(19, 6, "教育学类"));
        // 7-1
        mDatas.add(new FileBean(19, 7, "中国语言文学类"));
        mDatas.add(new FileBean(19, 7, "外国语言文学类"));
        mDatas.add(new FileBean(19, 7, "新闻传播学类"));
        // 8-1
        mDatas.add(new FileBean(19, 8, "公安学类"));
        mDatas.add(new FileBean(19, 8, "政治学类"));
        mDatas.add(new FileBean(19, 8, "民族学类"));
        mDatas.add(new FileBean(19, 8, "法学类"));
        mDatas.add(new FileBean(19, 8, "社会学类"));
        mDatas.add(new FileBean(19, 8, "马克思主义理论类"));
        // 9-1
        mDatas.add(new FileBean(19, 9, "化学类"));
        mDatas.add(new FileBean(19, 9, "地球物理学类"));
        mDatas.add(new FileBean(19, 9, "地理科学类"));
        mDatas.add(new FileBean(19, 9, "地质学类"));
        mDatas.add(new FileBean(19, 9, "大气科学类"));
        mDatas.add(new FileBean(19, 9, "天文学类"));
        mDatas.add(new FileBean(19, 9, "心理学类"));
        mDatas.add(new FileBean(19, 9, "数学类"));
        mDatas.add(new FileBean(19, 9, "海洋科学类"));
        mDatas.add(new FileBean(19, 9, "物理学类"));
        mDatas.add(new FileBean(19, 9, "生物科学类"));
        mDatas.add(new FileBean(19, 9, "统计学类"));
        // 10-1
        mDatas.add(new FileBean(19, 10, "公共管理类"));
        mDatas.add(new FileBean(19, 10, "农业经济管理类"));
        mDatas.add(new FileBean(19, 10, "图书情报与档案管理类"));
        mDatas.add(new FileBean(19, 10, "工业工程类"));
        mDatas.add(new FileBean(19, 10, "工商管理类"));
        mDatas.add(new FileBean(19, 10, "旅游管理类"));
        mDatas.add(new FileBean(19, 10, "物流管理与工程类"));
        mDatas.add(new FileBean(19, 10, "电子商务类"));
        mDatas.add(new FileBean(19, 10, "管理科学与工程类"));
        // 11-1
        mDatas.add(new FileBean(19, 11, "经济与贸易类"));
        mDatas.add(new FileBean(19, 11, "经济学类"));
        mDatas.add(new FileBean(19, 11, "财政学类"));
        mDatas.add(new FileBean(19, 11, "金融学类"));
        // 12-1
        mDatas.add(new FileBean(19, 12, "戏剧与影视学类"));
        mDatas.add(new FileBean(19, 12, "美术学类"));
        mDatas.add(new FileBean(19, 12, "艺术学理论类"));
        mDatas.add(new FileBean(19, 12, "设计学类"));
        mDatas.add(new FileBean(19, 12, "音乐与舞蹈学类"));
//        // 本科科目--第三层
//        //  动物医学类 1-1-1
//        mDatas.add(new FileBean(20, 13, "动植物检疫"));
//        mDatas.add(new FileBean(21, 13, "动物医学"));
//        mDatas.add(new FileBean(22, 13, "动物药学"));
//        // 1-1-2
//        mDatas.add(new FileBean(13, 14, "动物科学"));
//        mDatas.add(new FileBean(14, 14, "蚕学"));
//        mDatas.add(new FileBean(15, 14, "蜂学"));
//        // 1-1-3
//        mDatas.add(new FileBean(15, 15, "园林"));
//        mDatas.add(new FileBean(15, 15, "林学"));
//        mDatas.add(new FileBean(15, 15, "林学"));
//        // 1-1-4
//        mDatas.add(new FileBean(15, 15, "林学"));

    }

}
