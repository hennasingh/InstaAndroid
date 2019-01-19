package com.coder.instaandroid;

import android.app.Application;

import com.coder.instaandroid.network.ApiManager;
import com.coder.instaandroid.network.WebRepository;

public class InstaApplication extends Application {

    public static ApiManager sApiManager;
    public static WebRepository sWebRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        sApiManager = ApiManager.getInstance();
        sWebRepository = WebRepository.getInstance();
    }
}
