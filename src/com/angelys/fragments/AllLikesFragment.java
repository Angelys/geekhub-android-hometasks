package com.angelys.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);

        updateUI();
    }

}
