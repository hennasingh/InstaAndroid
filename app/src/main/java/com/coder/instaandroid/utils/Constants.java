package com.coder.instaandroid.utils;

import com.coder.instaandroid.BuildConfig;

public class Constants {

    public static final String BASE_URL = "https://api.instagram.com/v1";
    public static final String REDIRECT_URI = "https://www.instagram.com/";
    public static final String CLIENT_ID = BuildConfig.CLIENT_ID;
    private static final String AUTHURL = "https://api.instagram.com/oauth/authorize/";
    private static final String TOKENURL = "https://api.instagram.com/oauth/access_token";

    public static final String REQUEST_URL = BASE_URL
                                        + "oauth/authorize/?client_id="
                                        + CLIENT_ID
                                        + "&redirect_uri="
                                        +  REDIRECT_URI
                                        + "&response_type=token"
                                        + "&display=touch&scope=public_content";


    public static final String CLIENT_SECRET = BuildConfig.CLIENT_SECRET;


}
