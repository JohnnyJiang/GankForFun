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
import cuhk.johnnyjiang.gankforfun.util.GetColorUtils;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

/**
 * Project: GankForFun
 * Created by JohnnyJiang on 2016/12/19.
 */

public class ZhihuFragment extends BaseFragment<IZhihuView, ZhihuPresenter> implements IZhihuView {


    @Bind(R.id.content_list)
    RecyclerView contentList;
    @Bind(R.id.swipe_refresh)
    PtrFrameLayout ptrFrameLayout;
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
        ptrFrameLayout = (PtrFrameLayout) rootView.findViewById(R.id.swipe_refresh);
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(getContext());
        header.setBackgroundColor(GetColorUtils.getColor(getContext(), R.color.background_color));
        header.setPadding(0, PtrLocalDisplay.dp2px(20), 0, PtrLocalDisplay.dp2px(20));
        ptrFrameLayout.setDurationToCloseHeader(300);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.setRatioOfHeaderHeightToRefresh(0.8f);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.getLastestNews();
                        ptrFrameLayout.refreshComplete();
                    }
                },300);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setDataRefresh(true);
        mPresenter.getLastestNews();
        mPresenter.scrollRecycleView();
    }

//    @Override
//    public void requestDataRefresh() {
//        super.requestDataRefresh();
//        setDataRefresh(true);
//        mPresenter.getLastestNews();
//    }
//
    @Override
    public void setDataRefresh(Boolean refresh) {

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
