package com.example.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import com.example.R;
import com.example.activities.MainActivity;

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
    NotificationManager nm;

    public void onCreate()
    {
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        timer = new Timer();
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId)
    {
        this.sendNotification();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent i = new Intent();

                i.setAction(MainActivity.updateData);

                sendBroadcast(i);

            }
        }, 60000, 60000);
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void sendNotification()
    {
        Notification notification = new Notification(R.drawable.ic_launcher, "Text in status bar", System.currentTimeMillis());

        // 3-я часть
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // 2-я часть
        notification.setLatestEventInfo(this, "Notification's title", "Notification's text", pIntent);

        // ставим флаг, чтобы уведомление пропало после нажатия
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        // отправляем
        nm.notify(1, notification);
    }
}
