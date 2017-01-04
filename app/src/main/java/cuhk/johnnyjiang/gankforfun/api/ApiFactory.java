package cuhk.johnnyjiang.gankforfun.api;

import java.io.ObjectStreamException;

import cuhk.johnnyjiang.gankforfun.bean.Gank.Gank;

/**
 * Project: GankForFun
 * Created by JohnnyJiang on 2016/12/16.
 * Retrofit Api factory
 */

public class ApiFactory {

    protected static final Object monitor = new Object();
    static ZhihuApi zhihuApiSingleton = null;
    static GankApi gankApiSingleton = null;

    public static ZhihuApi getZhihuApiSingleton() {
        synchronized (monitor) {
            if (zhihuApiSingleton == null) {
                zhihuApiSingleton = new RetrofitInstance().getZhihuApi();
            }
            return zhihuApiSingleton;
        }
    }

    public static GankApi getGankApiSingleton() {
        synchronized (monitor) {
            if (gankApiSingleton == null) {
                gankApiSingleton = new RetrofitInstance().getGankApi();
            }
            return gankApiSingleton;
        }
    }
}
