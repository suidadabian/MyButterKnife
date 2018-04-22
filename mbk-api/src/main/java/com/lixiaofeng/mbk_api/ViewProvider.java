package com.lixiaofeng.mbk_api;

import android.app.Activity;
import android.view.View;

public class ViewProvider implements Provider {
    @Override
    public View findViewById(Object source, int resId) {
        if (source == null || !(source instanceof Activity)) {
            return null;
        }

        View view = (View) source;
        return view.findViewById(resId);
    }
}
