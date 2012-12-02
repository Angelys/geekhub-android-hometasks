package com.example;

import android.app.Application;
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
    }
    @Override
    public void onTerminate() {
        DatabaseHelperFactory.ReleaseHelper();
        super.onTerminate();
    }

}
