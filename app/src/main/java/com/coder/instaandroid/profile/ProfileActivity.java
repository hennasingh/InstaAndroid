package com.coder.instaandroid.profile;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
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
        }
    }

    public static Intent getIntent(Context context){
        return new Intent(context, ProfileActivity.class);
    }
}
