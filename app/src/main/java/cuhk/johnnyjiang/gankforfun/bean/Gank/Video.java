package cuhk.johnnyjiang.gankforfun.bean.Gank;

import java.util.List;

/**
 * Project: GankForFun
 * Created by JohnnyJiang on 2016/12/19.
 */

public class Video {

    private boolean error;
    private List<Gank> results;

    public boolean isError() {
        return error;
    }

    public List<Gank> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "Video{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
