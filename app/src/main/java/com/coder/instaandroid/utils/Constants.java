package com.coder.instaandroid.utils;

import com.coder.instaandroid.BuildConfig;

public class Constants {

    public static final String BASE_URL = "https://api.instagram.com";
    public static final String REDIRECT_URI = "https://com.coder.geeknest";
    public static final String CLIENT_ID = BuildConfig.CLIENT_ID;
    public static final String CLIENT_SECRET = BuildConfig.CLIENT_SECRET;


    public static final String REQUEST_URL = BASE_URL
                                        + "/oauth/authorize/?client_id="
                                        + CLIENT_ID
                                        + "&redirect_uri="
                                        +  REDIRECT_URI
                                        + "&response_type=code";





}
