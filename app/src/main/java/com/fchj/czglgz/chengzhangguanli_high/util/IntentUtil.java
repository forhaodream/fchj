package com.fchj.czglgz.chengzhangguanli_high.util;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class IntentUtil {
    public static void showIntent(Activity context, Class<?> clzz, String[] keys, String[] values) {
        Intent intent = new Intent(context, clzz);
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                if (!TextUtils.isEmpty(keys[i]) && !TextUtils.isEmpty(values[i])) {
                    intent.putExtra(keys[i], values[i]);
                }
            }
        }
        context.startActivity(intent);
    }

    public static void showIntent(Activity context, Class<?> clzz) {
        showIntent(context, clzz, null, null);
    }
}
