package com.lixiaofeng.mbk_api;

import android.app.Fragment;
import android.view.View;

public class FragmentProvider implements Provider {
    @Override
    public View findViewById(Object source, int resId) {
        if (source == null || !(source instanceof Fragment)) {
            return null;
        }

        Fragment fragment = (Fragment) source;
        return fragment.getView().findViewById(resId);
    }
}
