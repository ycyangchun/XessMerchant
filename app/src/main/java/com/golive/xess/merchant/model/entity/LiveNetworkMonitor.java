package com.golive.xess.merchant.model.entity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.golive.xess.merchant.model.api.NetworkMonitor;

/**
 * Created by YangChun .
 * on 2017/3/7.
 */

public class LiveNetworkMonitor implements NetworkMonitor {
    private final Context applicationContext;

    public LiveNetworkMonitor(Context context) {
        applicationContext = context.getApplicationContext();
    }

    public boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
