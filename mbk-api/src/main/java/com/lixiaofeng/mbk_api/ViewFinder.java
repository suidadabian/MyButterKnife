package com.lixiaofeng.mbk_api;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class ViewFinder {
    private static Provider ACTIVITY_PROVIDER = new ActivityProvider();
    private static Provider FRAGMENT_PROVIDER = new FragmentProvider();
    private static Provider VIEW_PROVIDER = new ViewProvider();
    private static final Map<String, Finder> FINDER_MAP = new HashMap<>();

    public static void inject(Activity activity) {
        inject(activity, activity, ACTIVITY_PROVIDER);
    }

    public static void inject(Fragment fragment) {
        inject(fragment, fragment, FRAGMENT_PROVIDER);
    }

    public static void inject(View view) {
        inject(view, view, VIEW_PROVIDER);
    }

    public static void inject(Object host, Object source, Provider provider) {
        String className = host.getClass().getName();
        try {
            Finder finder = FINDER_MAP.get(className);
            if (finder == null) {
                Class<?> finderClass = Class.forName(className + "$$Finder");
                finder = (Finder) finderClass.newInstance();
                FINDER_MAP.put(className, finder);
            }
            finder.inject(host, source, provider);
        } catch (Exception e) {
            throw new RuntimeException("Unable to inject for " + className, e);
        }
    }
}
