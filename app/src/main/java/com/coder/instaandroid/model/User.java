package com.coder.instaandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private String mId;

    @SerializedName("username")
    private String mUsername;

    @SerializedName("full_name")
    private String mFullName;

    @SerializedName("profile_picture")
    private String mProfilePicture;

    @SerializedName("counts")
    private Counts mCounts;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getProfilePicture() {
        return mProfilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        mProfilePicture = profilePicture;
    }

    public Counts getCounts() {
        return mCounts;
    }

    public void setCounts(Counts counts) {
        mCounts = counts;
    }
}

