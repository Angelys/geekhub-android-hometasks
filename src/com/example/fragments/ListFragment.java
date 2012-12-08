package com.example.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.example.R;
import com.example.activities.MainActivity;
import com.example.adapters.MyArrayAdapter;
import com.example.db.DatabaseHelperFactory;
import com.example.objects.Article;
import com.example.objects.ArticleCollection;
import com.example.utils.JSONData;
import java.util.List;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/11/12
 * Time: 10:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListFragment extends BaseListFragment {

    public static ListFragment Instance;
    public static ArticleCollection savedData;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);

        if(Instance != null)
        {
            this.data = Instance.data;
        }

        Instance = this;

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);

        loading_container = (LinearLayout) view.findViewById(R.id.loading_container);
        loading = View.inflate(getActivity(), R.layout.loading, new LinearLayout(getActivity()));

        if(data == null)
        {
            updateData();
        } else
        {
            updateUI();
        }
    }

    public void onDestroy()
    {
        getActivity().getPreferences(Context.MODE_PRIVATE).edit().remove(MainActivity.preferencesJSONData);
        super.onDestroy();
    }

    public void getData()
    {
        data = JSONData.run();
    }

    public void updateData()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                showLoading();

                getData();

                updateUI();

                hideLoading();
            }
        }).start();
    }

    public void showLoading()
    {
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loading_container.addView(loading);
            }
        });

    }

    public void hideLoading()
    {
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loading_container.removeView(loading);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        menu.add(0, MainActivity.OPT_BUTTON_ALLLIKES,0,"All likes").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == MainActivity.OPT_BUTTON_ALLLIKES)
        {
            showLikes();
        }

        return super.onOptionsItemSelected(item);
    }

    public void showLikes()
    {
        getSherlockActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.listfragment, new AllLikesFragment())
                .addToBackStack(null)
                .commit();
    }

}
