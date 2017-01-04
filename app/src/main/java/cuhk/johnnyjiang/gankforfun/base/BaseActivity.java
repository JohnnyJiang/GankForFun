package cuhk.johnnyjiang.gankforfun.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import butterknife.ButterKnife;
import cuhk.johnnyjiang.gankforfun.R;

/**
 * Project: GankForFun
 * Created by JohnnyJiang on 2016/12/14.
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity{

    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    protected T mPresenter;
//    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean isRequestDataRefresh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(createPresenter()!=null){
            mPresenter = createPresenter();
            mPresenter.attachView((V) this);
        }
        setContentView(provideContentViewId());
        ButterKnife.bind(this);

        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mAppBarLayout != null && mToolbar !=null) {
            setSupportActionBar(mToolbar);
            if (canBack()) {
                if (getSupportActionBar() !=null) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }else {
                    Log.i("Error","No SupportActionBar!");
                }
            }else{
                Log.i("Error","Can not back.");
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

//        if (isSetRefresh()) {
//            setupSwipeRefresh();
//;        }
    }

//    private void setupSwipeRefresh() {
//        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
//        if (mSwipeRefreshLayout != null) {
//            mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_1,
//                    R.color.refresh_progress_2,R.color.refresh_progress_3);
//            mSwipeRefreshLayout.setProgressViewOffset(true, 0,
//                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24,
//                            getResources().getDisplayMetrics()));
//            mSwipeRefreshLayout.setOnRefreshListener(this::requestDataRefresh);
//        }
//    }
//
//    public void requestDataRefresh() {
//        isRequestDataRefresh = true;
//    }
//
//    public void setRefresh(boolean requestDataRefresh) {
//        if (mSwipeRefreshLayout == null) {
//            return;
//        }
//        if (!requestDataRefresh) {
//            isRequestDataRefresh = false;
//            mSwipeRefreshLayout.postDelayed(() -> {
//                if (mSwipeRefreshLayout != null) {
//                    mSwipeRefreshLayout.setRefreshing(false);
//                }
//            }, 1000);
//        } else {
//            mSwipeRefreshLayout.setRefreshing(true);
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter != null) {
            mPresenter.detachView();
        }
    }

    public boolean canBack() { return false; }

    public void toHome() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    public Boolean isSetRefresh() { return false; }

    protected abstract T createPresenter();

    protected abstract int provideContentViewId();

}
