package cuhk.johnnyjiang.gankforfun.bean.Zhihu;

import java.io.Serializable;
import java.util.List;

/**
 * Project: GankForFun
 * Created by JohnnyJiang on 2016/12/16.
 */

public class ZhihuNewsList implements Serializable {

    private String date;
    private List<ZhihuStory> stories;
    private List<ZhihuTopStory> top_stories;

    public String getDate() {
        return date;
    }

    public List<ZhihuStory> getStories() {
        return stories;
    }

    public List<ZhihuTopStory> getTop_stories() {
        return top_stories;
    }



    @Override
    public String toString() {
        return "NewsTimeLine{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }
}
