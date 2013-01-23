package com.angelys.db;

import android.content.Context;
import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 12/2/12
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseHelperFactory {

    private static DatabaseHelper databaseHelper;

    public static DatabaseHelper GetHelper(){
        return databaseHelper;
    }
    public static void SetHelper(Context context){
        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }
    public static void ReleaseHelper(){
        OpenHelperManager.releaseHelper();
        databaseHelper = null;
    }

}
