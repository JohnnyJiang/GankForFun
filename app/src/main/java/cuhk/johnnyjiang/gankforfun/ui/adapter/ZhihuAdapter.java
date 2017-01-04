package cuhk.johnnyjiang.gankforfun.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cuhk.johnnyjiang.gankforfun.R;
import cuhk.johnnyjiang.gankforfun.bean.Zhihu.ZhihuNewsList;
import cuhk.johnnyjiang.gankforfun.bean.Zhihu.ZhihuStory;
import cuhk.johnnyjiang.gankforfun.bean.Zhihu.ZhihuTopStory;
import cuhk.johnnyjiang.gankforfun.util.ScreenUtils;
import cuhk.johnnyjiang.gankforfun.widget.TopViewPager;

/**
 * Created by jiang.yuheng on 2016/12/30.
 */

public class ZhihuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int status = 1;
    private Context context;
    private ZhihuNewsList zhihuNewsList;
    public static final int LOAD_MORE = 0;
    public static final int LOAD_PULL_TO = 1;
    public static final int LOAD_NONE = 2;
    public static final int LOAD_END = 3;
    private static final int TYPE_TOP = -1;
    private static final int TYPE_FOOTER = -2;


    public ZhihuAdapter(Context context, ZhihuNewsList zhihuNewsList) {
        this.context = context;
        this.zhihuNewsList = zhihuNewsList;
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        if(holder instanceof TopVpViewHolder) {
            TopVpViewHolder topVpViewHolder = (TopVpViewHolder) holder;
            topVpViewHolder.vpTopZhihu.startAutoRun();
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if(holder instanceof TopVpViewHolder) {
            TopVpViewHolder topVpViewHolder = (TopVpViewHolder) holder;
            topVpViewHolder.vpTopZhihu.stopAutoRun();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TOP) {
            View rootView = View.inflate(parent.getContext(), R.layout.vp_zhihu, null);
            return new TopVpViewHolder(rootView);
        } else if(viewType == TYPE_FOOTER){
            View view = View.inflate(parent.getContext(), R.layout.footer, null);
            return new FooterViewHolder(view);
        }else {
            View rootView = View.inflate(parent.getContext(), R.layout.item_zhihu_news, null);
            return new NewsViewHolder(rootView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TopVpViewHolder) {
            TopVpViewHolder topVpViewHolder = (TopVpViewHolder) holder;
            topVpViewHolder.bindItem(zhihuNewsList.getTop_stories());
        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            footerViewHolder.bindItem();
        } else {
            NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
            newsViewHolder.bindItem(zhihuNewsList.getStories().get(position-1));
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (zhihuNewsList.getTop_stories() != null) {
            if (position == 0) {
                return TYPE_TOP;
            } else if (position + 1 == getItemCount()) {
                return TYPE_FOOTER;
            } else {
                return position;
            }
        } else if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return position;
        }
    }

    @Override
    public int getItemCount() {
        return zhihuNewsList.getStories().size() + 2;
    }

    //Top ViewPager ViewHolder
    class TopVpViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.vp_top_zhihu)
        TopViewPager vpTopZhihu;
        @Bind(R.id.tv_vp_zhihu)
        TextView tvVpZhihu;

        public TopVpViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindItem(List<ZhihuTopStory> list) {
            vpTopZhihu.init(list, tvVpZhihu, item -> {
                //点击跳转ZhihuWebActivity
            });
        }


    }

    //item ViewHolder
    class NewsViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.tv_zhihu_news_title)
        TextView tvZhihuNewsTitle;
        @Bind(R.id.tv_zhihu_image)
        ImageView tvZhihuImage;
        @Bind(R.id.card_story)
        CardView cardStory;

        public NewsViewHolder(View itemVIew) {
            super(itemVIew);
            ButterKnife.bind(this, itemVIew);
            int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            cardStory.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, LinearLayout.LayoutParams.WRAP_CONTENT));
        }

        public void bindItem(ZhihuStory zhihuStory) {
            tvZhihuNewsTitle.setText(zhihuStory.getTitle());
            String[] images = zhihuStory.getImages();
            Glide.with(context).load(images[0]).centerCrop().into(tvZhihuImage);
            //cardStory.setOnClickListener(v -> context.startActivities());

        }
    }

    //footer ViewHolder
    class FooterViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.progress)
        ProgressBar progress;
        @Bind(R.id.tv_footer_load)
        TextView tvFooterLoad;

        FooterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat
                    .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.instance(context).dip2px(40));
            view.setLayoutParams(params);
        }

        private void bindItem() {
            switch (status) {
                case LOAD_MORE:
                    progress.setVisibility(View.VISIBLE);
                    tvFooterLoad.setText("正在加载...");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_PULL_TO:
                    progress.setVisibility(View.GONE);
                    tvFooterLoad.setText("上拉加载更多");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_NONE:
                    progress.setVisibility(View.GONE);
                    tvFooterLoad.setText("没有啦");
                case LOAD_END:
                    itemView.setVisibility(View.GONE);
            }
        }
    }
    public void updateLoadStatus(int status) {
        this.status = status;
        notifyDataSetChanged();
    }


}
