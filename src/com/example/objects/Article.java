package com.example.objects;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/14/12
 * Time: 2:55 PM
 * To change this template use File | Settings | File Templates.
 */
@DatabaseTable
public class Article {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String title;
    @DatabaseField
    private String description;
    @DatabaseField
    private String published;

    public Article(){}

    public Article(JSONObject article)
    {
        try
        {
            this.title = article.getJSONObject("title").getString("$t");
            this.description = article.getJSONObject("content").getString("$t");
            this.published = article.getJSONObject("published").getString("$t");

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

    public void setPublished(String published)
    {
        this.published = published;
    }

    public String getPublished()
    {
        return this.published;
    }

}
