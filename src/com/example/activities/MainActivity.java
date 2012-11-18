package com.example.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.example.fragments.DetailsFragment;
import com.example.objects.Article;
import com.example.utils.JSONData;
import com.example.fragments.ListFragment;
import com.example.R;

import java.util.HashMap;

public class MainActivity extends FragmentActivity implements ListFragment.onListElementSelectedListener {
    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if( getSupportFragmentManager().findFragmentById(R.id.listfragment) == null)
        {
            ListFragment frag = new ListFragment();
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            trans.add(R.id.listfragment, frag);

            trans.commit();
        }


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

}
