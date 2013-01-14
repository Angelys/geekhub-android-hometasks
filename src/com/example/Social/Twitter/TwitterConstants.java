package com.example.Social.Twitter;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 1/6/13
 * Time: 10:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class TwitterConstants {

    public static final String CONSUMER_KEY = "BRk9DqseqCmT8q9hRlO1Qw";
    public static final String CONSUMER_SECRET= "w5wwySrz0M79lNYsmtBczBsnzpT4EDhEMpxWoahE";

    public static final String REQUEST_URL = "https://api.twitter.com/oauth/request_token";
    public static final String ACCESS_URL = "https://api.twitter.com/oauth/access_token";
    public static final String AUTHORIZE_URL = "https://api.twitter.com/oauth/authorize";

    public static final String OAUTH_CALLBACK_SCHEME	= "appfortwitter";
    public static final String OAUTH_CALLBACK_HOST	= "callback";
    public static final String OAUTH_CALLBACK_URL	=  OAUTH_CALLBACK_SCHEME + "://" + OAUTH_CALLBACK_HOST;

}
