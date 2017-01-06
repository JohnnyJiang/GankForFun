package cuhk.johnnyjiang.gankforfun.ui.presenter;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import cuhk.johnnyjiang.gankforfun.R;
import cuhk.johnnyjiang.gankforfun.base.BasePresenter;
import cuhk.johnnyjiang.gankforfun.bean.Zhihu.ZhihuNews;
import cuhk.johnnyjiang.gankforfun.ui.view.IZhihuWebview;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jiang.yuheng on 2017/1/6.
 */

public class ZhihuWebPresenter extends BasePresenter<IZhihuWebview>{

    private Context context;

    public ZhihuWebPresenter(Context context) {
        this.context = context;
    }

    public void getNewsDetail(String id) {
        zhihuApi.getNewsDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(news -> {
                    setWebView(news);
                },this::loadError);
    }

    public void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(context, R.string.load_more, Toast.LENGTH_SHORT).show();
    }

    private void setWebView(ZhihuNews news) {
        WebView webView = getView().getWebView();
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        String head = "<head>\n" + "\t<link rel=\"stylesheet\" href=\"" + news.getCss()[0] +
        "\"/>\n" + "</head>";
        String img = "<div class=\"headline\">";
        String html = head + news.getBody().replace(img, " ");
        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
    }
}
