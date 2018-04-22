package com.lixiaofeng.mbk_api;

import android.app.Activity;
import android.view.View;

public class ActivityProvider implements Provider {
    @Override
    public View findViewById(Object source, int resId) {
        if (source == null || !(source instanceof Activity)) {
            return null;
        }

        Activity activity = (Activity) source;
        return activity.findViewById(resId);
    }
}
