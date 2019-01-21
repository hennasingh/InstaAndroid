package com.coder.instaandroid.model;

import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("standard_resolution")
    private StandardResolution mStandardResolution;

    public StandardResolution getStandardResolution() {
        return mStandardResolution;
    }
}
