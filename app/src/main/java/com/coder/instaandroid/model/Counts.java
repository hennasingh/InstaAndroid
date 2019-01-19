package com.coder.instaandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Counts {

    @SerializedName("media")
    private Integer mMediaCount;

    @SerializedName("follows")
    private Integer mFollows;

    @SerializedName("followed_by")
    private Integer mFollowedBy;

    public Integer getMediaCount() {
        return mMediaCount;
    }

    public void setMediaCount(Integer mediaCount) {
        mMediaCount = mediaCount;
    }

    public Integer getFollows() {
        return mFollows;
    }

    public void setFollows(Integer follows) {
        mFollows = follows;
    }

    public Integer getFollowedBy() {
        return mFollowedBy;
    }

    public void setFollowedBy(Integer followedBy) {
        mFollowedBy = followedBy;
    }
}
