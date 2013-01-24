package com.angelys.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.angelys.R;
import com.angelys.social.facebook.FacebookConnector;
import com.angelys.social.twitter.TwitterUtils;
import com.angelys.db.DatabaseHelperFactory;
import com.angelys.objects.Article;
import com.facebook.Session;

import java.sql.SQLException;
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

    private static Article article;
    private MenuInflater menuInflater;
    private String title;
    private String description;
    private Boolean inDB;
    private FacebookConnector facebookConnector;

    public DetailsFragment(){}

    public DetailsFragment(Article post)
    {
        article = post;
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
        this.menuInflater = inflater;
    }

    public void onPrepareOptionsMenu(Menu menu)
    {
        List a = null;

        try
        {
            // TODO it's better to make db queries on a thread, especially for big numbers of data,
            // unless it's already implemented on framework level.
            a = DatabaseHelperFactory.GetHelper().getArticleDao().queryForMatchingArgs(article);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        inDB = !(a == null || a.size() == 0);

        menuInflater.inflate(R.menu.menu, menu);
        menu.findItem(R.id.like_dislike).setTitle(inDB?"dislike":"like");
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.shareFacebook:
            {
                facebookConnector = new FacebookConnector(getActivity());
                facebookConnector.postMessage(article.getTitle());
                break;
            }
            case R.id.shareTwitter:
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TwitterUtils.sendTweetExec(getActivity(), article.getTitle());
                    }
                }).start();
                break;
            }
            case R.id.like_dislike : {
                if(inDB)
                {
                    dislikeArticle();
                } else
                {
                    likeArticle();
                }
                getSherlockActivity().invalidateOptionsMenu();
                break;
            }

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

    public void dislikeArticle()
    {
        try
        {
            List<Article> items =DatabaseHelperFactory.GetHelper().getArticleDao().queryForMatchingArgs(article);

            DatabaseHelperFactory.GetHelper().getArticleDao().delete(items);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
