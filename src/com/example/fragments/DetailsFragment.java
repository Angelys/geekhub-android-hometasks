package com.example.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import com.example.R;
import com.example.objects.Article;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/12/12
 * Time: 3:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class DetailsFragment extends Fragment {

    private String title;
    private String description;

    public DetailsFragment(){}

    public DetailsFragment(Article post)
    {
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

        return inflater.inflate(R.layout.details, container, false);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        if(this.title != null && this.description != null)
        {
            TextView title = (TextView)view.findViewById(R.id.descriprion_title);
            WebView description = (WebView)view.findViewById(R.id.descriprion);

            title.setText(this.title);
            description.loadData(this.description, "text/html", null);
        }

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

}
