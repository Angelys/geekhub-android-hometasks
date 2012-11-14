package com.example.objects;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/14/12
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArticleCollection extends Vector<Article> {

    public static ArticleCollection fromJSONObject(JSONArray array)
    {
        ArticleCollection collection = new ArticleCollection();

        for(int i = 0; i <= array.length() ; i++ )
        {
            collection.set(i, new Article(array.optJSONObject(i)));
        }

        return collection;

    }

    public ArticleCollection(){}

    public ArticleCollection(JSONArray array)
    {
        for(int i = 0; i <= array.length() ; i++ )
        {
            this.set(i, new Article(array.optJSONObject(i)));
        }
    }

}
