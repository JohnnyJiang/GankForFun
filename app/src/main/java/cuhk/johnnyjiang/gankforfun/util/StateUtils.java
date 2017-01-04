package cuhk.johnnyjiang.gankforfun.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Project: GankForFun
 * Created by JohnnyJiang on 2016/12/19.
 */

public class StateUtils {

    public static boolean isNetworkAvailable(Context context) {
        if (context!=null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info!=null){
                return info.isAvailable();
            }
        }
        return false;
    }
}
