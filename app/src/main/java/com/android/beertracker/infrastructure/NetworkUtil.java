package com.android.beertracker.infrastructure;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkUtil {

    public static boolean isConnectionAvailable(final Context context) {

        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
