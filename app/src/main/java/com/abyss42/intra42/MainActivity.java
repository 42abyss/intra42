package com.abyss42.intra42;



import android.annotation.SuppressLint;

import android.app.Activity;

import android.content.Intent;

import android.net.Uri;

import android.os.Bundle;

import android.view.Gravity;
import android.view.Window;

import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.FrameLayout;



public class MainActivity extends Activity
{

    private WebView webView;



    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // NoTitleBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);


        // Layout parameters
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT, Gravity.CENTER);

        // Create a FrameLayout
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setFitsSystemWindows(true);
        frameLayout.setBackgroundColor(this.getColor(android.R.color.black));
        frameLayout.setLayoutParams(layoutParams);

        // Create a WebView
        webView = new WebView(this);
        webView.setFitsSystemWindows(true);
        webView.setBackgroundColor(this.getColor(android.R.color.black));
        webView.setLayoutParams(layoutParams);

        // Add WebView to the FrameLayout
        frameLayout.addView(webView);

        // Display
        setContentView(frameLayout);


        WebSettings webSettings = webView.getSettings();

        // Enable JavaScript
        webSettings.setJavaScriptEnabled(true);

        // Enable DOM storage
        webSettings.setDomStorageEnabled(true);

        // Responsive design
        webSettings.setUseWideViewPort(false);
        webSettings.setLoadWithOverviewMode(true);


        webView.setWebViewClient(new WebViewClient()
        {


            // Management external browser
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                String url = request.getUrl().toString();
                if (!url.contains("42.fr") && !url.contains("42paris.fr"))
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                }
                else
                {
                    view.loadUrl(url);
                    return false;   
                }
            }


        });


        webView.loadUrl("https://profile.intra.42.fr/");
    }



    @Override
    public void onBackPressed()
    {
        if (webView.canGoBack())
        {
            webView.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }

}