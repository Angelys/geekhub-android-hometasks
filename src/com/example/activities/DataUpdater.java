package com.example.activities;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/25/12
 * Time: 8:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataUpdater extends Service {

    Timer timer;

    public void onCreate()
    {
        timer = new Timer();
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId)
    {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //TODO update data
            }
        }, 60000, 60000);
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
