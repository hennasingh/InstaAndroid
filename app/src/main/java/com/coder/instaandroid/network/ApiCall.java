package com.coder.instaandroid.network;

import com.coder.instaandroid.model.ApiResponse;
import com.coder.instaandroid.model.MediaResponse;
import com.coder.instaandroid.model.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiCall {

    @GET("/v1/users/self")
    Call<ApiResponse> getUserDetail(@Query("access_token") String token);

    @GET("/v1/users/self/media/recent")
    Call<MediaResponse> getUserMedia(@Query("access_token") String token);

    @FormUrlEncoded
    @POST("/oauth/access_token")
    Call<TokenResponse> getAccessToken(
            @Field("client_id") String client_id,
            @Field("client_secret") String client_secret,
            @Field("redirect_uri") String redirect_uri,
            @Field("grant_type") String grant_type,
            @Field("code") String code);



}
