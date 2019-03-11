package com.fchj.czglgz.chengzhangguanli_high.major;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.test.FileBean;
import com.fchj.czglgz.chengzhangguanli_high.test.SimpleTreeAdapter;
import com.fchj.czglgz.chengzhangguanli_high.treelist.Node;
import com.fchj.czglgz.chengzhangguanli_high.treelist.TreeListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ZhuanKeFragment extends Fragment {
    private List<FileBean> mDatas = new ArrayList<FileBean>();
    private ListView mTree;
    private TreeListViewAdapter mAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_zhuanke, null);
        initDatas();
        mTree = (ListView) view.findViewById(R.id.frag_zhuanke_lv);
        try {
            mAdapter = new SimpleTreeAdapter<FileBean>(mTree, getActivity(), mDatas, 0);

            mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                @Override
                public void onClick(Node node, int position) {
                    if (node.isLeaf()) {
                        Intent toThreeList = new Intent(getActivity(), ThirdListActivity.class);
                        toThreeList.putExtra("zy_name", node.getName());
                        toThreeList.putExtra("zy_title", "专业列表");
                        toThreeList.putExtra("zy_type", "专科专业");
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
        mDatas.add(new FileBean(1, 0, "公共管理与服务大类"));//
        mDatas.add(new FileBean(2, 0, "公安与司法大类"));//
        mDatas.add(new FileBean(3, 0, "医药卫生大类"));//
        mDatas.add(new FileBean(4, 0, "教育与体育大类"));//
        mDatas.add(new FileBean(5, 0, "文化艺术大类"));//
        mDatas.add(new FileBean(6, 0, "新闻传播大类"));//
        mDatas.add(new FileBean(7, 0, "旅游大类"));//
        mDatas.add(new FileBean(8, 0, "电子信息大类"));//
        mDatas.add(new FileBean(9, 0, "财经商贸大类"));//
        mDatas.add(new FileBean(10, 0, "交通运输大类"));//
        mDatas.add(new FileBean(11, 0, "资源环境与安全大类"));
        mDatas.add(new FileBean(12, 0, "装备制造大类"));
        mDatas.add(new FileBean(13, 0, "财经商贸大类"));
        mDatas.add(new FileBean(14, 0, "土木建筑大类"));
        mDatas.add(new FileBean(15, 0, "水利大类"));
        mDatas.add(new FileBean(16, 0, "食品药品与粮食大类"));
        mDatas.add(new FileBean(17, 0, "生物与化工大类"));
        mDatas.add(new FileBean(18, 0, "轻工纺织大类"));
        mDatas.add(new FileBean(90, 0, "农林牧鱼大类"));

        // 本科科目类--第二层
        // 农学 1-1
        mDatas.add(new FileBean(13, 1, "公共事业类"));
        mDatas.add(new FileBean(14, 1, "公共服务类"));
        mDatas.add(new FileBean(15, 1, "公共管理类"));
        // 2-1
        mDatas.add(new FileBean(19, 2, "侦查类"));
        mDatas.add(new FileBean(19, 2, "公安技术类"));
        mDatas.add(new FileBean(19, 2, "公安指挥类"));
        mDatas.add(new FileBean(19, 2, "公安管理类"));
        mDatas.add(new FileBean(19, 2, "司法技术类"));
        mDatas.add(new FileBean(19, 2, "法律实务类"));
        mDatas.add(new FileBean(19, 2, "法律执行类"));
        //3-1
        mDatas.add(new FileBean(19, 3, "临床医学类"));
        mDatas.add(new FileBean(19, 3, "人口与计划生育类"));
        mDatas.add(new FileBean(19, 3, "健康管理与促进类"));
        mDatas.add(new FileBean(19, 3, "公共卫生与卫生管理类"));
        mDatas.add(new FileBean(19, 3, "医学技术类"));
        mDatas.add(new FileBean(19, 3, "康复治疗类"));
        mDatas.add(new FileBean(19, 3, "护理类"));
        mDatas.add(new FileBean(19, 3, "药学类"));

        // 4-1
        mDatas.add(new FileBean(19, 4, "体育类"));
        mDatas.add(new FileBean(19, 4, "教育类"));
        mDatas.add(new FileBean(19, 4, "文秘类"));
        mDatas.add(new FileBean(19, 4, "语言类"));
        // 5-1
        mDatas.add(new FileBean(19, 5, "文化服务类"));
        mDatas.add(new FileBean(19, 5, "民族文化类"));
        mDatas.add(new FileBean(19, 5, "艺术设计类"));
        mDatas.add(new FileBean(19, 5, "表演艺术类"));
        // 6-1
        mDatas.add(new FileBean(19, 6, "广播影视类"));
        mDatas.add(new FileBean(19, 6, "新闻出版类"));
        // 7-1
        mDatas.add(new FileBean(19, 7, "会展类"));
        mDatas.add(new FileBean(19, 7, "旅游类"));
        mDatas.add(new FileBean(19, 7, "餐饮类"));
        // 8-1
        mDatas.add(new FileBean(19, 8, "电子信息类"));
        mDatas.add(new FileBean(19, 8, "计算机类"));
        mDatas.add(new FileBean(19, 8, "通信类"));
        // 9-1
        mDatas.add(new FileBean(19, 9, "工商管理类"));
        mDatas.add(new FileBean(19, 9, "市场营销类"));
        mDatas.add(new FileBean(19, 9, "物流类"));
        mDatas.add(new FileBean(19, 9, "电子商务类"));
        mDatas.add(new FileBean(19, 9, "经济贸易类"));
        mDatas.add(new FileBean(19, 9, "统计类"));
        mDatas.add(new FileBean(19, 9, "财务会计类"));
        mDatas.add(new FileBean(19, 9, "财政税务类"));

        //10-1
        mDatas.add(new FileBean(20, 10, "道路运输类"));
        mDatas.add(new FileBean(20, 10, "铁道运输类"));
        //11-1
        mDatas.add(new FileBean(20, 11, "地质类"));
        mDatas.add(new FileBean(20, 11, "安全类"));
        mDatas.add(new FileBean(20, 11, "气象类"));
        mDatas.add(new FileBean(20, 11, "测绘地理信息类"));
        mDatas.add(new FileBean(20, 11, "煤炭类"));
        mDatas.add(new FileBean(20, 11, "环境保护类"));
        mDatas.add(new FileBean(20, 11, "石油与天然气类"));
        mDatas.add(new FileBean(20, 11, "资源勘查类"));
        mDatas.add(new FileBean(20, 11, "金属与非金属矿类"));
        //12-1
        mDatas.add(new FileBean(20, 12, "机械设计制造类"));
        mDatas.add(new FileBean(20, 12, "机电设备类"));
        mDatas.add(new FileBean(20, 12, "汽车制造类"));
        mDatas.add(new FileBean(20, 12, "自动化类"));
        mDatas.add(new FileBean(20, 12, "航空装备类"));
        mDatas.add(new FileBean(20, 12, "船舶与海洋工程装备类"));
        mDatas.add(new FileBean(20, 12, "铁道装备类"));
        // 13-1
        mDatas.add(new FileBean(20, 13, "工商管理类"));
        mDatas.add(new FileBean(20, 13, "市场营销类"));
        mDatas.add(new FileBean(20, 13, "物流类"));
        mDatas.add(new FileBean(20, 13, "电子商务类"));
        mDatas.add(new FileBean(20, 13, "经济贸易类"));
        mDatas.add(new FileBean(20, 13, "统计类"));
        mDatas.add(new FileBean(20, 13, "财务会计类"));
        mDatas.add(new FileBean(20, 13, "金融类"));
        // 14-1
        mDatas.add(new FileBean(20, 14, "土建施工类"));
        mDatas.add(new FileBean(20, 14, "城乡规划与管理类"));
        mDatas.add(new FileBean(20, 14, "市政工程类"));
        mDatas.add(new FileBean(20, 14, "建筑设备类"));
        mDatas.add(new FileBean(20, 14, "建筑设计类"));
        mDatas.add(new FileBean(20, 14, "建设工程管理类"));
        mDatas.add(new FileBean(20, 14, "房地产类"));
        //15-1
        mDatas.add(new FileBean(20, 15, "水利工程与管理类"));
        mDatas.add(new FileBean(20, 15, "水利水电设备类"));
        mDatas.add(new FileBean(20, 15, "水土保持与水环境类"));
        mDatas.add(new FileBean(20, 15, "水文水资源类"));
        //16-1
        mDatas.add(new FileBean(20, 16, "城市轨道交通类"));
        mDatas.add(new FileBean(20, 16, "水上运输类"));
        mDatas.add(new FileBean(20, 16, "管道运输类"));
        mDatas.add(new FileBean(20, 16, "粮食储检类"));
        mDatas.add(new FileBean(20, 16, "粮食工业类"));
        mDatas.add(new FileBean(20, 16, "航空运输类"));
        mDatas.add(new FileBean(20, 16, "药品制造类"));
        mDatas.add(new FileBean(20, 16, "道路运输类"));
        mDatas.add(new FileBean(20, 16, "邮政类"));
        mDatas.add(new FileBean(20, 16, "食品工业类"));
        mDatas.add(new FileBean(20, 16, "食品药品管理类"));
        //17-1
        mDatas.add(new FileBean(20, 17, "化工技术类"));
        mDatas.add(new FileBean(20, 17, "生物技术类"));
        //18-1
        mDatas.add(new FileBean(20, 18, "包装类 "));
        mDatas.add(new FileBean(20, 18, "印刷类"));
        mDatas.add(new FileBean(20, 18, "纺织服装类"));
        mDatas.add(new FileBean(20, 18, "轻化工类"));
        //19-1
        mDatas.add(new FileBean(20, 90, "农业类"));
        mDatas.add(new FileBean(20, 90, "林业类"));
        mDatas.add(new FileBean(20, 90, "渔业类"));
        mDatas.add(new FileBean(20, 90, "畜牧业类"));

    }

}
