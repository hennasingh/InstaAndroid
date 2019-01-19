package com.coder.instaandroid.CustomViews;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.coder.instaandroid.AuthenticationListener;
import com.coder.instaandroid.R;
import com.coder.instaandroid.utils.Constants;

import butterknife.BindView;

public class AuthenticationDialog extends Dialog {

    private AuthenticationListener mAuthenticationListener;

    @BindView(R.id.webView)
    WebView mWebView;

    private Context mContext;

    public AuthenticationDialog(@NonNull Context context, AuthenticationListener listener) {
        super(context);
        mContext = context;
        mAuthenticationListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_dialog);
        initializeWebView();
    }

    private void initializeWebView() {
        mWebView.loadUrl(Constants.REQUEST_URL);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

    }
}
