package com.lixiaofeng.mbk_compiler;

import com.squareup.javapoet.ClassName;

public class TypeUtil {
    public static final ClassName PROVIDER = ClassName.get("com.lixiaofeng.mbk_api", "Provider");
    public static final ClassName ON_CLICK_LISTENER = ClassName.get("android.view", "View", "OnClickListener");
    public static final ClassName VIEW = ClassName.get("android.view", "View");
    public static final ClassName FINDER = ClassName.get(" com.lixiaofeng.mbk_api", "Finder");
}
