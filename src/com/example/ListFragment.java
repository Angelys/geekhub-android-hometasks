package com.example;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/11/12
 * Time: 10:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListFragment extends Fragment {

    private ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.main, container, false);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
    }

}
