package com.angelys;

import android.app.Application;
import android.content.Intent;
import com.angelys.Services.DataUpdater;
import com.angelys.db.DatabaseHelperFactory;

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
        startService(new Intent(this, DataUpdater.class));
    }

}
