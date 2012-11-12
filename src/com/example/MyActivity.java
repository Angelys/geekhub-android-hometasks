package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.HashMap;

public class MyActivity extends FragmentActivity implements ListFragment.onListElementSelectedListener {
    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if(savedInstanceState == null)
        {
            ListFragment frag = new ListFragment();
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            trans.add(R.id.fragment, frag);

            trans.commit();
        }


    }

    public void onItemSelected(int position)
    {
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

        DetailsFragment frag = new DetailsFragment((HashMap)JSONData.getData().get(position));

        trans.replace(R.id.fragment, frag);
        trans.addToBackStack(null);
        trans.commit();

    }

}
