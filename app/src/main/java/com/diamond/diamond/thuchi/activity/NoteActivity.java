package com.diamond.diamond.thuchi.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.diamond.diamond.thuchi.R;

public class NoteActivity extends AppCompatActivity {
private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
webView= findViewById(R.id.webview);
webView.setWebViewClient(new WebViewClient());
webView.loadUrl("https://vietnamnet.vn/vn/kinh-doanh/thi-truong/");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
