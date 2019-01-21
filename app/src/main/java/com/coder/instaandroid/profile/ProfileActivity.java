package com.coder.instaandroid.profile;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.coder.instaandroid.R;
import com.coder.instaandroid.model.MediaDetails;
import com.coder.instaandroid.model.User;
import com.coder.instaandroid.utils.Constants;
import com.coder.instaandroid.utils.ResultDisplay;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    ProfileViewModel mProfileViewModel;

    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;

    @BindView(R.id.tv_name)
    TextView mProfileName;

    @BindView(R.id.tv_posts)
    TextView mMediaCount;

    @BindView(R.id.tv_followers)
    TextView mFollowerCount;

    @BindView(R.id.tv_following)
    TextView mFollowingCount;

    @BindView(R.id.rv_media)
    RecyclerView mMediaRV;

    @BindView(R.id.tv_message)
    TextView mErrorMsg;

    @BindView(R.id.loading_bar)
    ProgressBar mLoadingBar;
    
    MediaAdapter mMediaAdapter;

     Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mUnbinder= ButterKnife.bind(this);

        mProfileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);

        mMediaRV.setLayoutManager(new GridLayoutManager(this, noOfColumns()));
        mMediaAdapter = new MediaAdapter();
        mMediaRV.setAdapter(mMediaAdapter);
        mMediaRV.setHasFixedSize(true);

        Uri uri = getIntent().getData();

        if(uri!=null){
            mProfileViewModel.getAccessToken(uri.getQueryParameter("code"));
        }

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file),MODE_PRIVATE);
        String token = sharedPref.getString(getString(R.string.token_key),"");
        if(!token.isEmpty()){

            mProfileViewModel.getUserDetails(token);
        }

        observerUserData();
        observeUserMedia();

    }

    private int noOfColumns() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthDivider = 400;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2;
        return nColumns;
    }

    @OnClick(R.id.btn_logout)
    public void logOut(View view){
        deleteUser();
    }

    private void deleteUser() {
        SharedPreferences.Editor sharedPref = getSharedPreferences(getString(R.string.preference_file),MODE_PRIVATE).edit();
        sharedPref.clear().apply();
        finish();
    }

    private void observeUserMedia() {

        mProfileViewModel.getMediaDetails().observe(this, new Observer<ResultDisplay<List<MediaDetails>>>() {
            @Override
            public void onChanged(@Nullable ResultDisplay<List<MediaDetails>> listResultDisplay) {

                switch(listResultDisplay.state){
                    case ResultDisplay.STATE_LOADING:
                        mLoadingBar.setVisibility(View.VISIBLE);
                        mErrorMsg.setVisibility(View.GONE);
                        mMediaRV.setVisibility(View.GONE);
                        break;
                    case ResultDisplay.STATE_ERROR:
                        mLoadingBar.setVisibility(View.GONE);
                        mMediaRV.setVisibility(View.GONE);
                        mErrorMsg.setVisibility(View.VISIBLE);
                        mErrorMsg.setText(listResultDisplay.errorMessage);
                        break;
                    case ResultDisplay.STATE_SUCCESS:
                        mLoadingBar.setVisibility(View.GONE);
                        mErrorMsg.setVisibility(View.GONE);
                        mMediaRV.setVisibility(View.VISIBLE);
                        mMediaAdapter.setImages(listResultDisplay.data);

                }
            }
        });
    }

    private void observerUserData() {

        mProfileViewModel.getUserProfile().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if(user!=null) setUpUI(user);
            }
        });

    }

    private void setUpUI(User user) {

        Picasso.get()
                .load(user.getProfilePicture())
                .error(R.drawable.image_not_found)
                .placeholder(R.drawable.loading)
                .into(mProfileImage);

        mProfileName.setText(user.getFullName());
        mMediaCount.setText(String.valueOf(user.getCounts().getMediaCount()));
        mFollowerCount.setText(String.valueOf(user.getCounts().getFollowedBy()));
        mFollowingCount.setText(String.valueOf(user.getCounts().getFollows()));
    }

    public static Intent getIntent(Context context){
        return new Intent(context, ProfileActivity.class);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog= new AlertDialog.Builder(this);

        alertDialog.setMessage(R.string.alert_message);
        alertDialog.setTitle(R.string.alert_title);
        alertDialog.setIcon(R.drawable.ic_warning);

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteUser();

            }
        });

        alertDialog.show();


    }


}
