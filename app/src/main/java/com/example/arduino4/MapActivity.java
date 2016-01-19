package com.example.arduino4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;

import android.webkit.WebView;


public class MapActivity extends AppCompatActivity{

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_map);

            WebView webview = (WebView) findViewById(R.id.webView1);
//        webview.setWebViewClient(new WebViewClient());

            webview.getSettings().setJavaScriptEnabled(true);
            webview.getSettings().setGeolocationEnabled(true);
            webview.getSettings().setGeolocationDatabasePath(this.getFilesDir().getPath());
//        webview.getSettings().setGeolocationDatabasePath(Context.getFilesDir().getPath());
            webview.setWebChromeClient(new MyChromeClient());

            webview.getSettings().setAppCacheEnabled(true);
            webview.getSettings().setDatabaseEnabled(true);
            webview.getSettings().setDomStorageEnabled(true);

            webview.loadUrl("https://www.google.com/maps/d/viewer?mid=z-psI8rfdCrw.k-HnB-okZ894&hl=en_US");

        }
        ///////////////////////////////////////


        class MyChromeClient extends WebChromeClient
        {
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                // Should implement this function.
                final String myOrigin = origin;
                final GeolocationPermissions.Callback myCallback = callback;
                AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);

                builder.setTitle("Request message");
                builder.setMessage("Allow current location?");
                builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        myCallback.invoke(myOrigin, true, false);
                    }
                });

                builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        myCallback.invoke(myOrigin, false, false);
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        }

    }