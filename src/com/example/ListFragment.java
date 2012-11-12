package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/11/12
 * Time: 10:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListFragment extends Fragment {

    private ListView list;
    private JSONData data;

    onListElementSelectedListener mCallBack;

    interface onListElementSelectedListener
    {
        public void onItemSelected(int item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater,container, savedInstanceState );

        data = new JSONData();
        data.start();
        return inflater.inflate(R.layout.titleslist, container, false);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
            mCallBack = (onListElementSelectedListener) activity;
        } catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement onListElementSelectedListener interface");
        }
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        list = (ListView) view.findViewById(R.id.titlelist);

        while(!JSONData.ready)
        {}

        list.setAdapter(new MyArrayAdapter(getActivity(), JSONData.getData()));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                mCallBack.onItemSelected(position);
            }
        });
    }

}
