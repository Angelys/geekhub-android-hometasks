package com.angelys.objects;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/14/12
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArticleCollection extends ArrayList<Article> {

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
        for(int i = 0; i < array.length() ; i++ )
        {
            this.add(i, new Article(array.optJSONObject(i)));
        }
    }

    public ArticleCollection(List list)
    {
        for(int i = 0; i < list.size() ; i++ )
        {
            this.add(i, (Article)list.get(i));
        }
    }

}
