package cuhk.johnnyjiang.gankforfun.ui.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Project: GankForFun
 * Created by JohnnyJiang on 2016/12/19.
 */

public interface IZhihuView {

    void setDataRefresh(Boolean refresh);
    RecyclerView getRecyclerView();
    LinearLayoutManager getLayoutManager();
}
