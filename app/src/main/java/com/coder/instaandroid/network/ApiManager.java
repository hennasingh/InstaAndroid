package com.coder.instaandroid.network;

import com.coder.instaandroid.model.ApiResponse;
import com.coder.instaandroid.model.MediaDetails;
import com.coder.instaandroid.model.MediaResponse;
import com.coder.instaandroid.utils.Constants;

import butterknife.internal.Utils;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static ApiCall sApiCall;
    private static ApiManager sApiManager;

    private ApiManager() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(new OkHttpClient.Builder().addInterceptor(interceptor).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sApiCall = retrofit.create(ApiCall.class);

    }

    public static ApiManager getInstance(){
        if(sApiManager==null){
            sApiManager = new ApiManager();
        }
        return sApiManager;
    }

    public void getUserProfile(String accessToken, Callback<ApiResponse> callback){
        Call<ApiResponse> instaApiResponse = sApiCall.getUserDetail(accessToken);
        instaApiResponse.enqueue(callback);
    }

    public void getUserMedia(String accessToken, Callback<MediaResponse> callback){
        Call<MediaResponse> instaMediaResponse = sApiCall.getUserMedia(accessToken);
        instaMediaResponse.enqueue(callback);
    }
}
