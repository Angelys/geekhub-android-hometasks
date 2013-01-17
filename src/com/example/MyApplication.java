package com.example;

import android.app.Application;
import android.content.Intent;
import com.example.Services.DataUpdater;
import com.example.db.DatabaseHelperFactory;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 12/2/12
 * Time: 1:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelperFactory.SetHelper(getApplicationContext());
        startService(new Intent(this, DataUpdater.class));
    }

}
