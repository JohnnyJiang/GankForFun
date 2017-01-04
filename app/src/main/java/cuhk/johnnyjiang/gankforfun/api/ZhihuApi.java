package cuhk.johnnyjiang.gankforfun.api;

import cuhk.johnnyjiang.gankforfun.bean.Zhihu.SplashImage;
import cuhk.johnnyjiang.gankforfun.bean.Zhihu.ZhihuNews;
import cuhk.johnnyjiang.gankforfun.bean.Zhihu.ZhihuNewsList;
import retrofit2.http.GET;
import rx.Observable;
import retrofit2.http.Path;

/**
 * Project: GankForFun
 * Created by JohnnyJiang on 2016/12/16.
 */

public interface ZhihuApi {

    @GET("start-image/1080*1920")
    Observable<SplashImage> getSplashImage();

    @GET("news/latest")
    Observable<ZhihuNewsList> getLatestNewsList();

    @GET("news/before/{time}")
    Observable<ZhihuNewsList> getBeforeNewsList(@Path("time") String time);

    @GET("news/{id}")
    Observable<ZhihuNews> getNewsDetail(@Path("id") String id);

}
