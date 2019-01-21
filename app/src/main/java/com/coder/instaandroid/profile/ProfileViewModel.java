package com.coder.instaandroid.profile;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.coder.instaandroid.InstaApplication;
import com.coder.instaandroid.model.MediaDetails;
import com.coder.instaandroid.model.User;
import com.coder.instaandroid.utils.ResultDisplay;

import java.util.List;

public class ProfileViewModel extends AndroidViewModel {


    Application mApplication;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;
    }

    public void getAccessToken(String code){

        InstaApplication.sWebRepository.fetchAccessToken(code,mApplication.getApplicationContext());
    }

    public void getUserDetails(String accessToken){
        InstaApplication.sWebRepository.getUserProfileData(accessToken);

    }

    public LiveData<User> getUserProfile(){

        return InstaApplication.sWebRepository.getUserProfile();
    }

    public LiveData<ResultDisplay<List<MediaDetails>>> getMediaDetails(){

        return InstaApplication.sWebRepository.getMediaDetails();
    }

}
