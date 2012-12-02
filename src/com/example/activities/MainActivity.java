package com.example.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
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
import com.example.fragments.DetailsFragment;
import com.example.fragments.ListFragment;
import com.example.objects.Article;
import com.example.utils.JSONData;

public class MainActivity extends SherlockFragmentActivity implements ListFragment.onListElementSelectedListener {
    /**
     * Called when the activity is first created.
     */
    InternetListener listener;
    ListFragment list_frag;
    private static final int OPT_BUTTON_LIKE = 1;
    private static final int OPT_BUTTON_ALLLIKES = 2;

    public static final String updateData = "com.example.updateData";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        startService(new Intent(this, DataUpdater.class));

        if( getSupportFragmentManager().findFragmentById(R.id.listfragment) == null)
        {
            list_frag = new ListFragment();
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            trans.add(R.id.listfragment, list_frag);

            trans.commit();
        }

        if(listener == null)
        {
            listener = new InternetListener();
            listener.activity = this;

            registerReceiver
                    (
                        listener,
                        new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                    );
        }


    }

    public void onDestroy()
    {
        stopService(new Intent(this, DataUpdater.class));
        unregisterReceiver(listener);
        super.onDestroy();
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

    public void onReceive(Context context, Intent intent)
    {
            Log.i("MainActivity", "Catched");
            //updateList();

    }

    public static class InternetListener extends BroadcastReceiver {

        MainActivity activity;

        @Override
        public void onReceive(Context context, Intent intent) {
            //TODO find out why it works just once on start and screen orientation change
            if(intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false))
            {
                activity.showNoConnection();
            } else
            {
                activity.hideNoConnection();
            }
        }
    }

    public void showNoConnection()
    {
        Toast.makeText(this, "No connection", Toast.LENGTH_LONG).show();
    }

    public void hideNoConnection()
    {
        Toast.makeText(this, "Connection up", Toast.LENGTH_LONG).show();
    }

    public void updateList()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                list_frag.data = JSONData.run();
                list_frag.notifyDataSetChanged();
            }
        });
    }


}
