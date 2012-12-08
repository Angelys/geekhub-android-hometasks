package com.example.utils;

import com.example.objects.ArticleCollection;
import com.example.objects.Article;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/11/12
 * Time: 9:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class JSONData {

    public static ArticleCollection run()
    {
        return parseData(getHTTP());
    }

    public static String getHTTP()
    {
        try
        {
            URL url = new URL("http://android-developers.blogspot.com/feeds/posts/default?alt=json");
            URLConnection urlConnection = url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));

            String line;
            StringBuilder result = new StringBuilder();

            while ( ( line = in.readLine()) != null)
            {
                result.append(line);
            }

            return result.toString();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return "";
    }

    public static ArticleCollection parseData(String json_string)
    {
        ArticleCollection result = new ArticleCollection();

        try{

         JSONObject json =  new JSONObject(json_string);

         result = new ArticleCollection(json.optJSONObject("feed").optJSONArray("entry"));

        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return result;

    }

    public static void main(String[] args)
    {
        JSONData obj = new JSONData();
        ArticleCollection collection = obj.run();

        for(Object item : collection)
        {
             System.out.println(((Article)item).getTitle());
        }

        System.out.println("done");

    }




}
