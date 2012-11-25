package com.example.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.example.objects.ArticleCollection;
import com.example.objects.Article;
import com.example.utils.JSONData;
import com.example.adapters.MyArrayAdapter;
import com.example.R;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/11/12
 * Time: 10:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListFragment extends Fragment {

    private ListView list;
    public ArticleCollection data;
    private View loading;
    private LinearLayout loading_container;

    onListElementSelectedListener mCallBack;

    public interface onListElementSelectedListener
    {
        public void onItemSelected(Article item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater,container, savedInstanceState );

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
        loading_container = (LinearLayout) view.findViewById(R.id.loading_container);
        loading = View.inflate(getActivity(), R.layout.loading, new LinearLayout(getActivity()));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                mCallBack.onItemSelected(data.get(position));
            }
        });

        if(data == null)
        {
            getData();
        } else
        {
            updateUI();
        }
    }

    public void getData()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {

                showLoading();

                data = JSONData.run();

                updateUI();

                hideLoading();

            }
        }).start();
    }

    public void updateUI()
    {
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                list.setAdapter(new MyArrayAdapter(getActivity(), data));

            }
        });

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

}
