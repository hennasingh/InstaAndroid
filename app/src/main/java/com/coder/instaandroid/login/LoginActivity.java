package com.coder.instaandroid.login;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.coder.instaandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    LoginViewModel mLoginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        if(mLoginViewModel.hasAccessToken()){
            mLoginViewModel.getUserDetails();
        }
    }

    @OnClick(R.id.sign_in_button)
    public void signIn(View view){

    }

}
