package com.angelys.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.angelys.R;
import com.angelys.db.DatabaseHelperFactory;
import com.angelys.objects.ArticleCollection;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 12/8/12
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class AllLikesFragment extends BaseListFragment {

    public static AllLikesFragment Instance;
    public View no_items;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Instance = this;

        try
        {
            data = new ArticleCollection(DatabaseHelperFactory.GetHelper().getArticleDao().queryForAll());
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        no_items = inflater.inflate(R.layout.no_items, container, false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void refresh()
    {
        if(getView() != null)
        {
            try
            {
                data = new ArticleCollection(DatabaseHelperFactory.GetHelper().getArticleDao().queryForAll());
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
            LinearLayout layout = (LinearLayout)getView().findViewById(R.id.loading_container);

            if(data.isEmpty())
            {
                layout.addView(no_items);
            } else
            {
                layout.removeAllViews();
            }

            updateUI();
        }


    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);

        if(data.isEmpty())
        {
            LinearLayout layout = (LinearLayout)getView().findViewById(R.id.loading_container);

            layout.addView(no_items);
        }

        updateUI();
    }

}
