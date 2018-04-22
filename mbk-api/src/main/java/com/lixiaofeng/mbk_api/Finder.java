package com.lixiaofeng.mbk_api;

public interface Finder<T> {
    void inject(T host, Object source, Provider provider);
}
