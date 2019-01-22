package com.coder.instaandroid.webView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.coder.instaandroid.R;
import com.coder.instaandroid.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthenticationDialog extends Dialog {

    private static final String TAG = AuthenticationDialog.class.getSimpleName();

    AuthenticationListener mListener;

    @BindView(R.id.webView)
    WebView mWebView;


    public AuthenticationDialog(Context context, AuthenticationListener listener) {
        super(context);
        mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_dialog);
        ButterKnife.bind(this);
        setUpWebView(mWebView);

    }

    private void setUpWebView(WebView webView){

        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setWebViewClient(new AuthWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(Constants.REQUEST_URL);
    }

    private class AuthWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG, "Redirecting URL " + url);

            if (url.startsWith(Constants.REDIRECT_URI)) {
                String urls[] = url.split("=");
                mListener.onTokenReceived(urls[1]);

                return true;
            }

            return false;
        }

    }
}
