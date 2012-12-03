package com.example.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.example.R;
import com.example.activities.MainActivity;
import com.example.db.DatabaseHelperFactory;
import com.example.objects.Article;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/12/12
 * Time: 3:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class DetailsFragment extends SherlockFragment {

    public static DetailsFragment Instance;

    private Article article;

    private String title;
    private String description;

    public DetailsFragment(){}

    public DetailsFragment(Article post)
    {
        this.article = post;
        this.title = post.getTitle();
        this.description = post.getDescription();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater,container, savedInstanceState );
        if(savedInstanceState != null)
        {
            this.title = (String)savedInstanceState.get("title");
            this.description = (String)savedInstanceState.get("description");
        }

        Instance = this;

        return inflater.inflate(R.layout.details, container, false);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        setHasOptionsMenu(true);

        if(this.title != null && this.description != null)
        {
            TextView title = (TextView)view.findViewById(R.id.descriprion_title);
            WebView description = (WebView)view.findViewById(R.id.descriprion);

            title.setText(this.title);
            description.loadData(this.description, "text/html", null);
        }

    }

    public void onDestroy()
    {
        Instance = null;
        super.onDestroy();
    }

    public void onSaveInstanceState(Bundle out)
    {
        out.putString("title", this.title);
        out.putString("description", this.description);
    }

    public void showArticle(Article post)
    {
        this.title = post.getTitle();
        this.description = post.getDescription();

        TextView title = (TextView)getView().findViewById(R.id.descriprion_title);
        WebView description = (WebView)getView().findViewById(R.id.descriprion);

        title.setText(this.title);
        description.loadData(this.description, "text/html", null);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        List a = null;

        try
        {
            a = DatabaseHelperFactory.GetHelper().getArticleDao().queryForMatchingArgs(article);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        if(a == null)
        {
            menu.add(0, MainActivity.OPT_BUTTON_LIKE,0,"Like").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        } else
        {
            menu.add(0,MainActivity.OPT_BUTTON_LIKE,0,"Liked").setEnabled(false).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == MainActivity.OPT_BUTTON_LIKE)
        {
            likeArticle();
        }

        return super.onOptionsItemSelected(item);
    }

    public void likeArticle()
    {
        try
        {
            DatabaseHelperFactory.GetHelper().getArticleDao().create(article);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
