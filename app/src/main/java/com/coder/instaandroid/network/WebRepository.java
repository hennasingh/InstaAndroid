package com.coder.instaandroid.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.net.wifi.WifiEnterpriseConfig;
import android.util.Log;

import com.coder.instaandroid.InstaApplication;
import com.coder.instaandroid.R;
import com.coder.instaandroid.model.ApiResponse;
import com.coder.instaandroid.model.MediaDetails;
import com.coder.instaandroid.model.MediaResponse;
import com.coder.instaandroid.model.TokenResponse;
import com.coder.instaandroid.model.User;
import com.coder.instaandroid.utils.Constants;
import com.coder.instaandroid.utils.ResultDisplay;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class WebRepository {

    private static final String TAG = WebRepository.class.getSimpleName();

    private static volatile WebRepository sWebRepository;

    private MutableLiveData<User> mUserDataObservable = new MutableLiveData<>();

    private MutableLiveData<ResultDisplay<List<MediaDetails>>> mMediaListObservable = new MutableLiveData<>();

    private WebRepository(){}

    public static WebRepository getInstance(){
        if(sWebRepository==null){
            sWebRepository = new WebRepository();
        }
        return sWebRepository;
    }

    public boolean checkUserAccessToken(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file),
                MODE_PRIVATE);
        String token = sharedPref.getString(context.getString(R.string.token_key),"");
        return !token.isEmpty();
    }

    public void fetchAccessToken(String code, final Context context){

        InstaApplication.sApiManager.getAccessToken(Constants.CLIENT_ID, Constants.CLIENT_SECRET,
                "authorization_code", Constants.REDIRECT_URI, code, new Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {

                        if(response.code() ==200) {
                            if(response.body().getAccessToken() !=null) {
                                SharedPreferences.Editor sharedPref = context.getSharedPreferences(
                                        context.getString(R.string.preference_file),MODE_PRIVATE).edit();
                                sharedPref.putString(context.getString(R.string.token_key),
                                        response.body().getAccessToken()).apply();
                                Log.d("WebRepo Class", " "+response.body().getAccessToken());
                                getUserProfileData(response.body().getAccessToken());
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {

                        Log.d(TAG, "HTTP Token request failed");
                    }
                });
    }

    public void getUserProfileData(final String token){

        InstaApplication.sApiManager.getUserProfile(token, new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                if(response.isSuccessful()){
                    if(response.body().getUserData()!=null){
                        mUserDataObservable.postValue(response.body().getUserData());
                        getUserMediaData(token);
                    }else{
                        mUserDataObservable.postValue(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                Log.d(TAG, "HTTP profile request failed");
            }
        });
    }

    public LiveData<User> getUserProfile(){
        return mUserDataObservable;
    }

    public LiveData<ResultDisplay<List<MediaDetails>>> getMediaDetails(){
        return mMediaListObservable;
    }

    public void getUserMediaData(String token){

        mMediaListObservable.postValue(ResultDisplay.loading(Collections.<MediaDetails>emptyList()));

        InstaApplication.sApiManager.getUserMedia(token, new Callback<MediaResponse>() {
            @Override
            public void onResponse(Call<MediaResponse> call, Response<MediaResponse> response) {

                if(response.isSuccessful()){
                    mMediaListObservable.setValue(ResultDisplay.success(response.body().getMediaDetails()));
                }else{
                    mMediaListObservable.setValue(ResultDisplay.error(response.message(),
                            Collections.<MediaDetails>emptyList()));
                }
            }

            @Override
            public void onFailure(Call<MediaResponse> call, Throwable t) {

                mMediaListObservable.setValue(ResultDisplay.error(t.getMessage(),Collections.<MediaDetails>emptyList()));
                Log.d(TAG, "HTTP media request failed");
            }
        });
    }


}
