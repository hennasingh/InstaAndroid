package com.coder.instaandroid.model;

import android.service.autofill.UserData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("data")
    private User mUserData;

    public User getUserData() {
        return mUserData;
    }

    public void setUserData(User userData) {
        mUserData = userData;
    }
}
