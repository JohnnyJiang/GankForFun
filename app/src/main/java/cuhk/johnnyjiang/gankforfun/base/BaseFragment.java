package cuhk.johnnyjiang.gankforfun.base;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;

import butterknife.ButterKnife;
import cuhk.johnnyjiang.gankforfun.R;

/**
 * Project: GankForFun
 * Created by JohnnyJiang on 2016/12/19.
 */

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment{

    protected T mPresenter;

    private boolean isRequestDataRefresh = false;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V)this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(createViewLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        initView(rootView);
        if(isSetRefresh()) {
            setupSwipeRefresh(rootView);
        }
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    private void setupSwipeRefresh(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        if(mSwipeRefreshLayout !=null) {
            mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_1,
                    R.color.refresh_progress_2, R.color.refresh_progress_3);
            mSwipeRefreshLayout.setProgressViewOffset(true, 0, (int) TypedValue
            .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
            mSwipeRefreshLayout.setOnRefreshListener(this::requestDataRefresh);
        }
    }

    public void requestDataRefresh() {
        isRequestDataRefresh = true;
    }

    public void setRefresh(boolean requestDataRefresh) {
        if (mSwipeRefreshLayout == null) {
            return;
        }
        if (!requestDataRefresh) {
            isRequestDataRefresh = false;
            mSwipeRefreshLayout.postDelayed(() -> {
                if (mSwipeRefreshLayout != null) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            },1000);
        } else {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    protected abstract T createPresenter();

    protected abstract int createViewLayoutId();

    protected void initView(View rootView) {}

    public Boolean isSetRefresh() { return true; }
}
