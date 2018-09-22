   package com.henok_solomon.cartrack.wunder.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

 

public final class NetworkUtils {

    private NetworkUtils() {
      
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
