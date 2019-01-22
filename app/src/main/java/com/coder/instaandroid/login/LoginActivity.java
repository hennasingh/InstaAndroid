package com.coder.instaandroid.login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.coder.instaandroid.R;
import com.coder.instaandroid.profile.ProfileActivity;
import com.coder.instaandroid.utils.Constants;
import com.coder.instaandroid.webView.AuthenticationDialog;
import com.coder.instaandroid.webView.AuthenticationListener;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements AuthenticationListener {

    LoginViewModel mLoginViewModel;
    AuthenticationDialog mAuthenticationDialog;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        if(mLoginViewModel.hasAccessToken()){
            Intent detailIntent = ProfileActivity.getIntent(this);
            startActivity(detailIntent);
        }
    }

    @OnClick(R.id.sign_in_button)
    public void signIn(View view){

        mAuthenticationDialog = new AuthenticationDialog(this,this);
        mAuthenticationDialog.show();
    }

    @Override
    public void onTokenReceived(String code) {

        if(code!=null){
            mAuthenticationDialog.dismiss();
            Intent intent = ProfileActivity.getIntent(this,code);
            startActivity(intent);
        }

    }
}
