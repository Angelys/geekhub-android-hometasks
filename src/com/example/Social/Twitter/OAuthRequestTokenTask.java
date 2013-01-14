package com.example.Social.Twitter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.example.activities.MainActivity;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 1/6/13
 * Time: 11:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class OAuthRequestTokenTask extends AsyncTask<Void, Void, Void> {

    final String TAG = getClass().getName();

    private MainActivity context;
    private OAuthProvider provider;
    private OAuthConsumer consumer;
    private Handler errorHandler;
    private String url;

    public OAuthRequestTokenTask(Context context, OAuthConsumer consumer, OAuthProvider provider, Handler errorHandler) {
        this.context = (MainActivity) context;
        this.consumer = consumer;
        this.provider = provider;
        this.errorHandler = errorHandler;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Log.i(TAG, "Retrieving request token");

            url = provider.retrieveRequestToken(consumer,
                    TwitterConstants.OAUTH_CALLBACK_URL);//запрос
//на получение Access Token’ов
        } catch (Exception e) {
            Log.e(TAG, "Error during OAUth retrieve request token", e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if (!TextUtils.isEmpty(url)) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

        }
    }
}