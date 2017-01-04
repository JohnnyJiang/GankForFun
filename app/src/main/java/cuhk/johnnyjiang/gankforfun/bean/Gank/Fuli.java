package cuhk.johnnyjiang.gankforfun.bean.Gank;

import java.util.List;

/**
 * Project: GankForFun
 * Created by JohnnyJiang on 2016/12/19.
 */

public class Fuli {

    private boolean error;
    private List<Gank> results;

    public boolean isError() { return error; }

    public List<Gank> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "Fuli{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
