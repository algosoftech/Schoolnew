package com.algosoft.gov.school.utility;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by anil on 3/15/2017.
 */
public class NetworkStatus {

    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
