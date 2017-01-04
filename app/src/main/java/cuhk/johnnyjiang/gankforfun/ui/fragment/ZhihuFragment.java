package cuhk.johnnyjiang.gankforfun.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import cuhk.johnnyjiang.gankforfun.R;
import cuhk.johnnyjiang.gankforfun.base.BaseFragment;
import cuhk.johnnyjiang.gankforfun.ui.presenter.ZhihuPresenter;
import cuhk.johnnyjiang.gankforfun.ui.view.IZhihuView;

/**
 * Project: GankForFun
 * Created by JohnnyJiang on 2016/12/19.
 */

public class ZhihuFragment extends BaseFragment<IZhihuView, ZhihuPresenter> implements IZhihuView {


    @Bind(R.id.content_list)
    RecyclerView contentList;
    private LinearLayoutManager mLayoutManager;


    @Override
    protected ZhihuPresenter createPresenter() {
        return new ZhihuPresenter(getContext());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.zhihu_fragment;
    }

    @Override
    protected void initView(View rootView) {
        mLayoutManager = new LinearLayoutManager(getContext());
        contentList.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setDataRefresh(true);
        mPresenter.getLastestNews();
        mPresenter.scrollRecycleView();
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        setDataRefresh(true);
        mPresenter.getLastestNews();
    }

    @Override
    public void setDataRefresh(Boolean refresh) {
        setRefresh(refresh);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return contentList;
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }

}
