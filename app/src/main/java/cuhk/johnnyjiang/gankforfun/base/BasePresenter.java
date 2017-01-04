package cuhk.johnnyjiang.gankforfun.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import cuhk.johnnyjiang.gankforfun.api.ApiFactory;
import cuhk.johnnyjiang.gankforfun.api.GankApi;
import cuhk.johnnyjiang.gankforfun.api.ZhihuApi;
import cuhk.johnnyjiang.gankforfun.bean.Gank.Gank;

/**
 * Project: GankForFun
 * Created by JohnnyJiang on 2016/12/14.
 */

public abstract class BasePresenter<V> {

    protected Reference<V> mViewReference;

    public static final ZhihuApi zhihuApi = ApiFactory.getZhihuApiSingleton();
    public static final GankApi gankApi = ApiFactory.getGankApiSingleton();

    public void attachView(V view) {
        mViewReference = new WeakReference<V>(view);
    }

    protected V getView() {
        return mViewReference.get();
    }

    public boolean isViewAttached() {
        return mViewReference !=  null && mViewReference.get()!=null;
    }

    public void detachView() {
        if(mViewReference != null) {
            mViewReference.clear();
            mViewReference = null;
        }
    }
}
