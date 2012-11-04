package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 10/29/12
 * Time: 7:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Fragment1 extends Fragment {

    onListElementSelectedListener mCallBack;

    interface onListElementSelectedListener
    {
        public void onItemSelected(CharSequence item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        return inflater.inflate(R.layout.fragment1, container, false);
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

    @Override
    public void onViewCreated(View view, Bundle bundle)
    {
        ListView lv = (ListView) getView().findViewById(R.id.list);
        lv.setAdapter(ArrayAdapter.createFromResource(getActivity(), R.array.list, android.R.layout.simple_list_item_1));



        lv.setOnItemClickListener(new OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                TextView tv = (TextView)view;

                mCallBack.onItemSelected(tv.getText());
            }
        });
    }
}
