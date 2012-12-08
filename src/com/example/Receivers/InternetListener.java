package com.example.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.example.activities.MainActivity;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 12/8/12
 * Time: 11:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class InternetListener extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getExtras()!=null) {
            NetworkInfo ni=(NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
            if(ni!=null && ni.getState()==NetworkInfo.State.CONNECTED) {
                if(MainActivity.Instance != null)
                {
                    MainActivity.Instance.showConnectionUp();
                }
            }
        }
        if(intent.getExtras().getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
            if(MainActivity.Instance != null)
            {
                MainActivity.Instance.showNoConnection();
            }
        }
    }

}
