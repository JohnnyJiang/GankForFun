package cuhk.johnnyjiang.gankforfun.bean.Zhihu;

/**
 * Project: GankForFun
 * Created by JohnnyJiang on 2016/12/16.
 */

public class SplashImage {


    /**
     * text : Elena Prokofyeva
     * img : https://pic4.zhimg.com/v2-f2cf38f62276ffe6c86f7d0a31a38e9f_xxdpi.jpg
     */

    private String text;
    private String img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "SplashImage{" +
                "text='" + text + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
