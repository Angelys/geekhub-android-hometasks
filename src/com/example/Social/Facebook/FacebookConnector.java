package com.example.Social.Facebook;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.facebook.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 1/13/13
 * Time: 12:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class FacebookConnector {

    public List<String> permissions = new ArrayList<String>();
    public Session session;
    public Activity context;

    public String TAG = "FacebookConnector";

    public Session.StatusCallback statusCallback = new Session.StatusCallback() {

        public void call (Session session, SessionState state, Exception exception)
        {
            if(state.isOpened())
            {
                Toast.makeText(context, "Logged in", Toast.LENGTH_SHORT).show();
            } else if(state.isClosed())
            {
                Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public FacebookConnector(Activity activity)
    {
        permissions.add("basic_info");
        permissions.add("publish_actions");
        this.context = activity;
    }

    public void logIn()
    {
        session = Session.getActiveSession();
        if (!session.isOpened() && !session.isClosed()) {
            session.openForRead(new Session.OpenRequest(context)
                    .setPermissions(permissions)
                    .setCallback(statusCallback));
        } else {
            session = Session.openActiveSession(context, true, statusCallback);
        }
    }

    public void postMessage(String message)
    {
        if(session.isOpened())
        {
            postMessageRequest(message);
        } else
        {
            logIn();
        }
    }

    public void postMessageRequest(String message)
    {
        Bundle postParams = new Bundle();
        postParams.putString("name", "Facebook SDK for Android");
        //postParams.putString("caption", "Build great social apps and get more installs.");
        postParams.putString("description", "The Facebook SDK for Android makes it easier and faster to develop Facebook integrated Android apps.");
        //postParams.putString("link", "https://developers.facebook.com/android");
        //postParams.putString("picture", "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");

        Request.Callback callback= new Request.Callback() {
            public void onCompleted(Response response) {
                JSONObject graphResponse = response
                        .getGraphObject()
                        .getInnerJSONObject();
                String postId = null;
                try {
                    postId = graphResponse.getString("id");
                } catch (JSONException e) {
                    Log.i(TAG,
                            "JSON error " + e.getMessage());
                }
                FacebookRequestError error = response.getError();
                if (error != null) {
                    Toast.makeText(context
                            .getApplicationContext(),
                            error.getErrorMessage(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context
                            .getApplicationContext(),
                            postId,
                            Toast.LENGTH_LONG).show();
                }
            }
        };

        Request request = new Request(session, "me/feed", postParams,
                HttpMethod.POST, callback);

        RequestAsyncTask task = new RequestAsyncTask(request);
        task.execute();
    }

}
