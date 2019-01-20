package com.coder.instaandroid.model;

import android.location.Location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MediaDetails {


    @SerializedName("likes")
    private Likes likes;

    @SerializedName("link")
    private String link;

    @SerializedName("created_time")
    private String createdTime;

    @SerializedName("images")
    private Images images;

}
