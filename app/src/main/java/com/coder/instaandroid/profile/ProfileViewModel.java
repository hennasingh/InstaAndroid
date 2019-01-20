package com.coder.instaandroid.profile;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.coder.instaandroid.InstaApplication;

public class ProfileViewModel extends AndroidViewModel {


    Application mApplication;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;
    }

    public void getAccessToken(String code){

        InstaApplication.sWebRepository.fetchAccessToken(code,mApplication.getApplicationContext());
    }
}
