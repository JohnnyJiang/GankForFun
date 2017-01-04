package cuhk.johnnyjiang.gankforfun;

import android.app.Application;
import android.content.Context;

/**
 * Project: GankForFun
 * Created by JohnnyJiang on 2016/12/19.
 */

public class App extends Application{

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
