package com.coder.instaandroid.network;

import com.coder.instaandroid.model.ApiResponse;
import com.coder.instaandroid.model.MediaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCall {

    @GET("/v1/users/self")
    Call<ApiResponse> getUserDetail(@Query("access_token") String token);

    @GET("/v1/users/self/media/recent")
    Call<MediaResponse> getUserMedia(@Query("access_token") String token);



}
