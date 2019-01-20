package com.coder.instaandroid.login;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.coder.instaandroid.InstaApplication;
import com.coder.instaandroid.network.WebRepository;

public class LoginViewModel extends AndroidViewModel {

    private Boolean hasToken= false;

    public LoginViewModel(@NonNull Application application) {
        super(application);
       hasToken = InstaApplication.sWebRepository.checkUserAccessToken(application.getApplicationContext());
    }

    public boolean hasAccessToken(){
        return hasToken;
    }



}
