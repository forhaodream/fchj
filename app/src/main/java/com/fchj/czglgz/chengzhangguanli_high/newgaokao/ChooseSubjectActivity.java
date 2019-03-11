package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.lh.ch.updateversion.BuildConfig;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChooseSubjectActivity extends Activity implements View.OnClickListener {
    private List<String> subList = new ArrayList<>();
    private ImageView mTitleFanhui;
    private TextView mTitleText;
    private AutoRelativeLayout mChooseWuli;
    private AutoRelativeLayout mChooseHuaxue;
    private AutoRelativeLayout mChooseShengwu;
    private AutoRelativeLayout mChooseLishi;
    private AutoRelativeLayout mChooseDili;
    private AutoRelativeLayout mChooseZhengzhi;
    private AutoRelativeLayout mChooseJishu;
    private AutoRelativeLayout mChooseSubjectTrue;
    private ArrayList<SubjectModel> mSubList = new ArrayList<>();
    private ArrayList<String> mSubStrList = new ArrayList<>();

    List<String> list;
    private int count1 = 0;
    private int count2 = 0;
    private int count3 = 0;
    private int count4 = 0;
    private int count5 = 0;
    private int count6 = 0;
    private int count7 = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subject);
        initView();
        if (subList.size() == 1) {
            if (BuildConfig.DEBUG) Log.d("ChooseSubjectActivity", "aaaa");
        }

//        testList2();
    }

    private void removeData() {
        List<SubjectModel> tempList = new ArrayList<SubjectModel>();
        for (SubjectModel i : mSubList) {
            if (!tempList.contains(i)) {
                tempList.add(i);
            }
        }
        Log.d("rereqqqq", tempList.size() + "");

//        for (SubjectModel i : tempList) {
//
//            Log.d("rere", tempList.size() + "");
//
//        }
    }

    public static ArrayList getSingle(ArrayList list) {
        ArrayList newList = new ArrayList();     //创建新集合
        Iterator it = list.iterator();        //根据传入的集合(旧集合)获取迭代器
        while (it.hasNext()) {          //遍历老集合
            Object obj = it.next();       //记录每一个元素
            if (!newList.contains(obj)) {      //如果新集合中不包含旧集合中的元素
                newList.add(obj);       //将元素添加
            }
        }
        return newList;
    }

    private void initView() {
        mTitleFanhui = (ImageView) findViewById(R.id.title_fanhui);
        mTitleFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleText = (TextView) findViewById(R.id.title_text);
        mChooseWuli = (AutoRelativeLayout) findViewById(R.id.choose_wuli);
        mChooseWuli.setOnClickListener(this);
        mChooseHuaxue = (AutoRelativeLayout) findViewById(R.id.choose_huaxue);
        mChooseHuaxue.setOnClickListener(this);
        mChooseShengwu = (AutoRelativeLayout) findViewById(R.id.choose_shengwu);
        mChooseShengwu.setOnClickListener(this);
        mChooseLishi = (AutoRelativeLayout) findViewById(R.id.choose_lishi);
        mChooseLishi.setOnClickListener(this);
        mChooseDili = (AutoRelativeLayout) findViewById(R.id.choose_dili);
        mChooseDili.setOnClickListener(this);
        mChooseZhengzhi = (AutoRelativeLayout) findViewById(R.id.choose_zhengzhi);
        mChooseZhengzhi.setOnClickListener(this);
        mChooseJishu = (AutoRelativeLayout) findViewById(R.id.choose_jishu);
        mChooseJishu.setOnClickListener(this);
        mChooseSubjectTrue = (AutoRelativeLayout) findViewById(R.id.choose_subject_true);
        mChooseSubjectTrue.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_subject_true:
                if (mSubStrList.size() == 3) {
                    Intent toPie = new Intent(ChooseSubjectActivity.this, MajorBySubActivity.class);
//                    Toast.makeText(this, mSubList.size() + "", Toast.LENGTH_SHORT).show();
//                    getSingle(mSubList);
//                    removeData();
                    toPie.putExtra("subname1", mSubStrList.get(0));
                    toPie.putExtra("subname2", mSubStrList.get(1));
                    toPie.putExtra("subname3", mSubStrList.get(2));
                    startActivity(toPie);
                } else {
                    Toast.makeText(this, "请选择3个科目", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.choose_wuli:
                count1 += 1;
//                if (mSubStrList.size() != 3) {
                if (count1 % 2 == 1) {
                    mSubStrList.add("物理");
                    mChooseWuli.setBackgroundResource(R.color.red);
                } else {
                    mChooseWuli.setBackgroundResource(R.color.choose_sub);
                    mSubStrList.remove("物理");
                }
//                } else {
//                    Toast.makeText(this, "已经选择三个了", Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(this, "" + mSubStrList.toString(), Toast.LENGTH_SHORT).show();
//                if (mSubList.size() != 3) {
//                    mSubList.add(new SubjectModel(1, "物理"));
//                    mChooseWuli.setBackgroundResource(R.color.red);
//                    mChooseWuli.setClickable(false);
//                } else {
//                    Toast.makeText(this, "已经选择三个了", Toast.LENGTH_SHORT).show();
//                }
                break;
            case R.id.choose_huaxue:
                count2 += 1;
//                if (mSubStrList.size() != 3) {

                if (count2 % 2 == 1) {
                    mSubStrList.add("化学");
                    mChooseHuaxue.setBackgroundResource(R.color.red);
                } else {
                    mChooseHuaxue.setBackgroundResource(R.color.choose_sub);
                    mSubStrList.remove("化学");
                }
//                } else {
//                    Toast.makeText(this, "已经选择三个了", Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(this, "" + mSubStrList.toString(), Toast.LENGTH_SHORT).show();
//                if (mSubList.size() != 3) {
//                    mSubList.add(new SubjectModel(2, "化学"));
//                    mChooseHuaxue.setBackgroundResource(R.color.red);
//                    mChooseHuaxue.setClickable(false);
//                } else {
//                    Toast.makeText(this, "已经选择三个了", Toast.LENGTH_SHORT).show();
//                }

                break;
            case R.id.choose_shengwu:
                count3 += 1;
//                if (mSubStrList.size() != 3) {
                if (count3 % 2 == 1) {
                    mSubStrList.add("生物");
                    mChooseShengwu.setBackgroundResource(R.color.red);
                } else {
                    mChooseShengwu.setBackgroundResource(R.color.choose_sub);
                    mSubStrList.remove("生物");
                }
//                } else {
//                    Toast.makeText(this, "已经选择三个了", Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(this, "" + mSubStrList.toString(), Toast.LENGTH_SHORT).show();
//                if (mSubList.size() != 3) {
//                    mSubList.add(new SubjectModel(3, "生物"));
//                    mChooseShengwu.setBackgroundResource(R.color.red);
//                    mChooseShengwu.setClickable(false);
//                } else {
//                    Toast.makeText(this, "已经选择三个了", Toast.LENGTH_SHORT).show();
//                }
                break;
            case R.id.choose_lishi:
                count4 += 1;
//                if (mSubStrList.size() != 3) {
                if (count4 % 2 == 1) {
                    mSubStrList.add("历史");
                    mChooseLishi.setBackgroundResource(R.color.red);
                } else {
                    mChooseLishi.setBackgroundResource(R.color.choose_sub);
                    mSubStrList.remove("历史");
                }
//                } else {
//                    Toast.makeText(this, "已经选择三个了", Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(this, "" + mSubStrList.toString(), Toast.LENGTH_SHORT).show();
//                if (mSubList.size() != 3) {
//                    mSubList.add(new SubjectModel(4, "历史"));
//                    mChooseLishi.setBackgroundResource(R.color.red);
//                    mChooseLishi.setClickable(false);
//                } else {
//                    Toast.makeText(this, "已经选择三个了", Toast.LENGTH_SHORT).show();
//                }
                break;
            case R.id.choose_dili:
                count5 += 1;
//                if (mSubStrList.size() != 3) {
                if (count5 % 2 == 1) {
                    mSubStrList.add("地理");
                    mChooseDili.setBackgroundResource(R.color.red);
                } else {
                    mChooseDili.setBackgroundResource(R.color.choose_sub);
                    mSubStrList.remove("地理");
                }
//                } else {
//                    Toast.makeText(this, "已经选择三个了", Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(this, "" + mSubStrList.toString(), Toast.LENGTH_SHORT).show();
//                if (mSubList.size() != 3) {
//                    mSubList.add(new SubjectModel(5, "地理"));
//                    mChooseDili.setBackgroundResource(R.color.red);
//                    mChooseDili.setClickable(false);
//                } else {
//                    Toast.makeText(this, "已经选择三个了", Toast.LENGTH_SHORT).show();
//                }
                break;
            case R.id.choose_zhengzhi:
                count6 += 1;
//                if (mSubStrList.size() != 3) {
                if (count6 % 2 == 1) {
                    mSubStrList.add("政治");
                    mChooseZhengzhi.setBackgroundResource(R.color.red);
                } else {
                    mChooseZhengzhi.setBackgroundResource(R.color.choose_sub);
                    mSubStrList.remove("政治");
                }
//                } else {
//                    Toast.makeText(this, "已经选择三个了", Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(this, "" + mSubStrList.toString(), Toast.LENGTH_SHORT).show();
//                if (mSubList.size() != 3) {
//                    mSubList.add(new SubjectModel(6, "政治"));
//                    mChooseZhengzhi.setBackgroundResource(R.color.red);
//                    mChooseZhengzhi.setClickable(false);
//                } else {
//                    Toast.makeText(this, "已经选择三个了", Toast.LENGTH_SHORT).show();
//                }
                break;
            case R.id.choose_jishu:
                count7 += 1;
//                if (mSubStrList.size() != 3) {
                if (count7 % 2 == 1) {
                    mSubStrList.add("技术");
                    mChooseJishu.setBackgroundResource(R.color.red);
                } else {
                    mChooseJishu.setBackgroundResource(R.color.choose_sub);
                    mSubStrList.remove("技术");
                }
//                } else {
//                    Toast.makeText(this, "已经选择三个了", Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(this, "" + mSubStrList.toString(), Toast.LENGTH_SHORT).show();
//                if (mSubList.size() != 3) {
//                    mSubList.add(new SubjectModel(7, "技术"));
//                    mChooseJishu.setBackgroundResource(R.color.red);
//                    mChooseJishu.setClickable(false);
//                } else {
//                    Toast.makeText(this, "已经选择三个了", Toast.LENGTH_SHORT).show();
//                }
                break;

        }
    }

    public void testList2() {
        list = new ArrayList<String>();
        list.add("a");
        list.add("ab");
        list.add("ac");
        list.add("ab");
        list.add("ac");
        list.add("a1");
        list.add("a");
        List<String> tempList = new ArrayList<String>();
        for (String i : list) {
            if (!tempList.contains(i)) {
                tempList.add(i);
            }
        }
        for (String i : tempList) {

            Log.d("mSubStrLista", i);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSubStrList.clear();
        mChooseWuli.setBackgroundResource(R.color.choose_sub);
        mChooseHuaxue.setBackgroundResource(R.color.choose_sub);
        mChooseShengwu.setBackgroundResource(R.color.choose_sub);
        mChooseLishi.setBackgroundResource(R.color.choose_sub);
        mChooseDili.setBackgroundResource(R.color.choose_sub);
        mChooseZhengzhi.setBackgroundResource(R.color.choose_sub);
        mChooseJishu.setBackgroundResource(R.color.choose_sub);
        count1 = 0;
        count2 = 0;
        count3 = 0;
        count4 = 0;
        count5 = 0;
        count6 = 0;
        count7 = 0;
//        mChooseWuli.setClickable(true);
//        mChooseHuaxue.setClickable(true);
//        mChooseShengwu.setClickable(true);
//        mChooseLishi.setClickable(true);
//        mChooseDili.setClickable(true);
//        mChooseZhengzhi.setClickable(true);
//        mChooseJishu.setClickable(true);
    }
}
