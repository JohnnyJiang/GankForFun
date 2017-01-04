package cuhk.johnnyjiang.gankforfun.bean.Gank;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: GankForFun
 * Created by JohnnyJiang on 2016/12/16.
 */

public class GankInfo {

    private ResultsBean results;
    private List<String> category;

    public static class ResultsBean {
        public List<Gank> android;
        public List<Gank> iOS;
        public List<Gank> video;
        public List<Gank> frontEnd;
        public List<Gank> exResource;
        public List<Gank> fuli;
        public List<Gank> recommend;

        public List<Gank> getAllResults() {

            List<Gank> mGankList = new ArrayList<>();
            if (android != null) mGankList.addAll(0,android);
            if (iOS != null) mGankList.addAll(0,iOS);
            if (frontEnd != null) mGankList.addAll(0,frontEnd);
            if (recommend != null) mGankList.addAll(0,recommend);
            if (exResource != null) mGankList.addAll(0,exResource);
            return mGankList;
        }

        @Override
        public String toString() {
            return "ResultsBean{" +
                    "android=" + android +
                    ", iOS=" + iOS +
                    ", video=" + video +
                    ", frontEnd=" + frontEnd +
                    ", exResource=" + exResource +
                    ", fuli=" + fuli +
                    ", recommend=" + recommend +
                    '}';
        }

    }

    @Override
    public String toString() {
        return "GankInfo{" +
                "results=" + results +
                ", category=" + category +
                '}';
    }
}
