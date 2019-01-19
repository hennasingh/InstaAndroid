package com.coder.instaandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MediaResponse {

    @SerializedName("data")
    private List<MediaDetails> mMediaDetails;

    public List<MediaDetails> getMediaDetails() {
        return mMediaDetails;
    }

    public void setMediaDetails(List<MediaDetails> mediaDetails) {
        mMediaDetails = mediaDetails;
    }
}
