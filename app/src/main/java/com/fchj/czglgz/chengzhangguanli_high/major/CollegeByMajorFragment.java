package com.fchj.czglgz.chengzhangguanli_high.major;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.dropdownmenu.DropActivity;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.SearchCollegeModel;
import com.fchj.czglgz.chengzhangguanli_high.volunteeradapter.GetAllCollegeAdapter;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.StringReader;


public class CollegeByMajorFragment extends Fragment {
    private ListView mListView;
    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_college_by_major, null);
        mListView = (ListView) mView.findViewById(R.id.fragment_college_lv);
        return mView;
    }

    private void getAllCollegeByKind() {

    }
}
