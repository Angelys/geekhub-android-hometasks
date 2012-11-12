package com.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/11/12
 * Time: 9:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class JSONData extends Thread {

    private static JSONObject data;
    private String error;
    public static Boolean ready = false;

    @Override
    public void run()
    {
        parseData(getHTTP());
    }

    private BufferedReader getHTTP()
    {
        try
        {
            URL url = new URL("http://android-developers.blogspot.com/feeds/posts/default?alt=json");
            URLConnection urlConnection = url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));

            return in;

        } catch (Exception e)
        {
            e.printStackTrace();
             error = "request";
        }

        return new BufferedReader(new InputStreamReader(new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }
        }));
    }

    private void parseData(BufferedReader json_string)
    {
        data = (JSONObject)JSONValue.parse(json_string);

        ready = true;
    }

    public static ArrayList getData()
    {
        JSONObject feed = (JSONObject)data.get("feed");

        return (ArrayList)feed.get("entry");
    }

    public static void main(String[] args)
    {
        JSONData obj = new JSONData();
        obj.start();
        while(!JSONData.ready){
             try{sleep(1000);}catch (Exception e){}
        }

        ArrayList data = JSONData.getData();

        for(Object item : data)
        {
             System.out.println(((HashMap)((HashMap) item).get("title")).get("$t"));
        }

        System.out.println("done");

    }




}
