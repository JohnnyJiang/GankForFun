package cuhk.johnnyjiang.gankforfun.api;

import cuhk.johnnyjiang.gankforfun.bean.Gank.Fuli;
import cuhk.johnnyjiang.gankforfun.bean.Gank.GankInfo;
import cuhk.johnnyjiang.gankforfun.bean.Gank.Video;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Project: GankForFun
 * Created by JohnnyJiang on 2016/12/16.
 */

public interface GankApi {

    @GET("data/福利/10/{page}")
    Observable<Fuli> getFuliData(@Path("page") int page);

    @GET("day/{year}/{month}/{day}")
    Observable<GankInfo> getGankData(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    @GET("data/休息视频/10/{page}")
    Observable<Video> getVideoData(@Path("page") int page);
}
