package com.example.user.webviewinjava;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    ProgressDialog dialog;

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.wview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.addJavascriptInterface(this,"MyJavaInterface");
        final EditText et1 = findViewById(R.id.et1);
        ImageView search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("https://"+et1.getText().toString());
                webView.setWebViewClient(new WebViewClient() {
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);
                        dialog = new ProgressDialog(MainActivity.this, ProgressDialog.STYLE_SPINNER);
                        dialog.setMessage("Loading..");
                        dialog.setCancelable(true);
                        dialog.show();

                    }

                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        dialog.dismiss();
                    }

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        return super.shouldOverrideUrlLoading(view, request);
                    }
                });
            }
        });
    }
    @JavascriptInterface
    public void display(String uname, String pass) {
        Toast.makeText(this, uname + "\t" + pass, Toast.LENGTH_SHORT).show();
    }

    public void ClickEvent(View v)
    {
        //Toast.makeText(this,"Button pressed",Toast.LENGTH_SHORT).show();
        switch (v.getId()) {
            case R.id.fb:
                webView.loadUrl("https://www.facebook.com");
                webView.setWebViewClient(new WebViewClient());
                break;
            case R.id.google:
                webView.loadUrl("https://www.google.com");
                webView.setWebViewClient(new WebViewClient());
                break;
            case R.id.youtube:
                webView.loadUrl("https://www.youtube.com");
                webView.setWebViewClient(new WebViewClient());
                break;
            case R.id.html:

                webView.loadUrl("file:///android_asset/login_new.html");
                webView.setWebViewClient(new WebViewClient());
                break;

        }
    }

}
        