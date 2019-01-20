package com.coder.instaandroid.profile;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coder.instaandroid.R;

public class ProfileActivity extends AppCompatActivity {

    ProfileViewModel mProfileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mProfileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);

        Uri uri = getIntent().getData();

        if(uri!=null){
            mProfileViewModel.getAccessToken(uri.getQueryParameter("code"));
        }else{
            SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file),MODE_PRIVATE);
            String token = sharedPref.getString(getString(R.string.token_key),"");
            if(!token.isEmpty()){
                mProfileViewModel.getUserDetails(token);
                observerUserData();
            }
        }
    }

    private void observerUserData() {
    }

    public static Intent getIntent(Context context){
        return new Intent(context, ProfileActivity.class);
    }
}
