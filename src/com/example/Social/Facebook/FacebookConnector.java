package com.example.Social.Facebook;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Base64;
import android.content.pm.Signature;
import android.util.Log;
import android.widget.Toast;
import com.facebook.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 1/13/13
 * Time: 12:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class FacebookConnector {

    public List<String> permissions = Arrays.asList("publish_stream");
    public static Session session;
    public Activity context;
    public String appid = "319905308120793";

    public String TAG = "FacebookConnector";

    public Session.StatusCallback statusCallback = new Session.StatusCallback() {

        public void call (Session session, SessionState state, Exception exception)
        {
            if(state.isOpened())
            {
                Log.e("Status changed", "opened");
                Toast.makeText(context, "Logged in", Toast.LENGTH_SHORT).show();
            } else if(state.isClosed())
            {
                Log.e("Status changed", "closed");
                Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public FacebookConnector(Activity activity)
    {
        this.context = activity;

        session = new Session(context);

        Session.setActiveSession(session);

    }

    public void postMessage(String message)
    {
        final String mess = message;

        session.openForPublish(new Session.OpenRequest(context).setPermissions(permissions).setCallback(new Session.StatusCallback() {
            @Override
            public void call(Session session, SessionState state, Exception exception) {
                if(state.isOpened())
                {
                    postMessageRequest(mess);
                }
            }
        }));
    }

    public void postMessageRequest(String message)
    {
        Bundle postParams = new Bundle();
        postParams.putString("message", message);

        Request.Callback callback= new Request.Callback() {
            public void onCompleted(Response response) {


                FacebookRequestError error = response.getError();
                if (error != null) {
                    Toast.makeText(context
                            .getApplicationContext(),
                            error.getErrorMessage(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context
                            .getApplicationContext(),
                            "Thank you!",
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
