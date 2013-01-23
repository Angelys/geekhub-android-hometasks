package com.angelys.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockFragment;
import com.angelys.R;
import com.angelys.adapters.MyArrayAdapter;
import com.angelys.objects.Article;
import com.angelys.objects.ArticleCollection;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 12/8/12
 * Time: 4:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseListFragment extends SherlockFragment {

    protected ListView list;
    public ArticleCollection data;
    protected View loading;
    protected LinearLayout loading_container;

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

    public void onViewCreated(View view, Bundle bundle)
    {
        list = (ListView) view.findViewById(R.id.titlelist);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                mCallBack.onItemSelected(data.get(position));
            }
        });
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

    public void updateUI()
    {
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                list.setAdapter(new MyArrayAdapter(getActivity(), data));

            }
        });

    }

}
