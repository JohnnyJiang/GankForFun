package cuhk.johnnyjiang.gankforfun.ui.presenter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import cuhk.johnnyjiang.gankforfun.base.BasePresenter;
import cuhk.johnnyjiang.gankforfun.bean.Zhihu.ZhihuNewsList;
import cuhk.johnnyjiang.gankforfun.ui.adapter.ZhihuAdapter;
import cuhk.johnnyjiang.gankforfun.ui.view.IZhihuView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Project: GankForFun
 * Created by JohnnyJiang on 2016/12/19.
 */

public class ZhihuPresenter extends BasePresenter<IZhihuView> {

    public Context mContext;
    private IZhihuView zhihuView;
    private RecyclerView mRecylerView;
    private LinearLayoutManager layoutManager;
    private ZhihuNewsList mZhihuNewsList;
    private int lastVisibleItem;
    private boolean isLoadMore = false;
    private String time;
    private ZhihuAdapter zhihuAdapter;

    public ZhihuPresenter(Context context) { this.mContext = context; }

    public void getLastestNews() {
        zhihuView = getView();
        if (zhihuView != null) {
            mRecylerView = zhihuView.getRecyclerView();
            layoutManager = zhihuView.getLayoutManager();

            zhihuApi.getLatestNewsList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(zhihuNewsList -> {
                        disPlayZhihuList(zhihuNewsList, mContext, zhihuView, mRecylerView);
                    },this::loadError);
        }
    }

    private void getBeforeNews(String time) {
        zhihuView = getView();
        if (zhihuView != null) {
            mRecylerView = zhihuView.getRecyclerView();
            layoutManager = zhihuView.getLayoutManager();

            zhihuApi.getBeforeNewsList(time)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(newsTimeLine -> {
                        disPlayZhihuList(newsTimeLine,mContext, zhihuView,mRecylerView);
                    },this::loadError);
        }
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(mContext, "出错了", Toast.LENGTH_SHORT).show();
    }

    private void disPlayZhihuList(ZhihuNewsList zhihuNewsList, Context context, IZhihuView iZhihuView,
                                  RecyclerView recyclerView) {
        if (isLoadMore) {
            if (time == null) {
                zhihuAdapter.updateLoadStatus(zhihuAdapter.LOAD_NONE);
                iZhihuView.setDataRefresh(false);
                return;
            } else {
                mZhihuNewsList.getStories().addAll(zhihuNewsList.getStories());
            }
            zhihuAdapter.notifyDataSetChanged();
        } else {
            mZhihuNewsList = zhihuNewsList;
            zhihuAdapter = new ZhihuAdapter(context, zhihuNewsList);
            recyclerView.setAdapter(zhihuAdapter);
            zhihuAdapter.notifyDataSetChanged();
        }
        iZhihuView.setDataRefresh(false);
        time = zhihuNewsList.getDate();
    }

    public void scrollRecycleView() {
        mRecylerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = layoutManager
                            .findLastVisibleItemPosition();
                    if (layoutManager.getItemCount() == 1) {
                        zhihuAdapter.updateLoadStatus(zhihuAdapter.LOAD_NONE);
                        return;
                    }
                    if (lastVisibleItem + 1 == layoutManager
                            .getItemCount()) {
                        zhihuAdapter.updateLoadStatus(zhihuAdapter.LOAD_PULL_TO);
                        isLoadMore = true;
                        zhihuAdapter.updateLoadStatus(zhihuAdapter.LOAD_MORE);
                        new Handler().postDelayed(() -> getBeforeNews(time), 1000);
                        mRecylerView.setFocusable(false);
                        mRecylerView.smoothScrollBy(0, 1000);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }
}
