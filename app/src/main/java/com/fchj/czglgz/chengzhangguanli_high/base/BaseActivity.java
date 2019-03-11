package com.fchj.czglgz.chengzhangguanli_high.base;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;

public class BaseActivity extends Activity {

    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setToDefaults();
        return resources;
    }
}
