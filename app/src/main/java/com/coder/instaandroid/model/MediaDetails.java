package com.coder.instaandroid.model;

import com.google.gson.annotations.SerializedName;

public class MediaDetails {

    @SerializedName("comments")
    private Comments mComments;

    @SerializedName("likes")
    private Likes mlikes;

    @SerializedName("images")
    private Images images;

    public Comments getComments() {
        return mComments;
    }

    public Likes getLikes() {
        return mlikes;
    }

    public Images getImages() {
        return images;
    }
}
