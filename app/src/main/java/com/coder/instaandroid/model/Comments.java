package com.coder.instaandroid.model;

import com.google.gson.annotations.SerializedName;

public class Comments {

    @SerializedName("count")
    private int mCount;

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }
}
