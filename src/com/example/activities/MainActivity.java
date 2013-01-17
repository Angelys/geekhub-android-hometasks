package com.example.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.example.R;
import com.example.Services.DataUpdater;
import com.example.db.DatabaseHelperFactory;
import com.example.fragments.DetailsFragment;
import com.example.fragments.ListFragment;
import com.example.objects.Article;
import com.example.utils.JSONData;
import com.facebook.Session;

public class MainActivity extends SherlockFragmentActivity implements ListFragment.onListElementSelectedListener {
    /**
     * Called when the activity is first created.
     */
    BroadcastReceiver updateReceiver;
    ListFragment list_frag;

    public static MainActivity Instance;

    public static final int OPT_BUTTON_LIKE = 1;
    public static final int OPT_BUTTON_DISLIKE = 2;
    public static final int OPT_BUTTON_ALLLIKES = 3;

    public static final String preferencesJSONData = "JSONData";

    public static final String updateData = "com.example.updateData";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        Instance = this;

        if( getSupportFragmentManager().findFragmentById(R.id.listfragment) == null)
        {
            list_frag = new ListFragment();
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            trans.add(R.id.listfragment, list_frag);

            trans.commit();
        }

        updateReceiver = new BroadcastReceiver()
        {
            public void onReceive(Context context, Intent intent)
            {
                if(ListFragment.Instance != null)
                {
                    ListFragment fragment = (ListFragment) ListFragment.Instance;

                    fragment.updateData();
                }
            }
        };

        registerReceiver( updateReceiver , new IntentFilter(updateData) );


    }

    protected void onPause()
    {
        unregisterReceiver(updateReceiver);
        super.onPause();
    }

    protected void onResume()
    {
        registerReceiver(updateReceiver, new IntentFilter(updateData));
        super.onResume();
    }

    protected void onDestroy()
    {
        ListFragment.Instance = null;
        Instance = null;
        super.onDestroy();
        stopService(new Intent(this, DataUpdater.class));
        DatabaseHelperFactory.ReleaseHelper();
    }

    public void onItemSelected(Article article)
    {
        DetailsFragment detfrag = (DetailsFragment)getSupportFragmentManager().findFragmentById(R.id.detailsfragment);

        if(detfrag != null)
        {
             detfrag.showArticle(article);
        } else
        {
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

            DetailsFragment frag = new DetailsFragment(article);

            trans.replace(R.id.listfragment, frag);
            trans.addToBackStack(null);
            trans.commit();
        }
    }

    public void showNoConnection()
    {
        Toast.makeText(this, "No connection", Toast.LENGTH_LONG).show();
    }

    public void showConnectionUp()
    {
        Toast.makeText(this, "Connection up", Toast.LENGTH_LONG).show();
    }


}
