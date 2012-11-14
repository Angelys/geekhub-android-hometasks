package com.example.objects;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/14/12
 * Time: 2:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Article {

    private String title;
    private String description;

    public Article(){}

    public Article(JSONObject article)
    {
        try
        {
            this.title = article.getJSONObject("title").getString("$t");
            this.description = article.getJSONObject("content").getString("$t");

        } catch (JSONException e){}
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getDescription()
    {
        return this.description;
    }

}
