package com.coder.instaandroid.network;

import android.content.Context;
import android.content.SharedPreferences;

import com.coder.instaandroid.R;

import static android.content.Context.MODE_PRIVATE;

public class WebRepository {

    private static volatile WebRepository sWebRepository;

    private WebRepository(){}

    public static WebRepository getInstance(){
        if(sWebRepository==null){
            sWebRepository = new WebRepository();
        }
        return sWebRepository;
    }

    public boolean checkUserAccessToken(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file),MODE_PRIVATE);
        String token = sharedPref.getString("token","");
        return !token.isEmpty();
    }
}
