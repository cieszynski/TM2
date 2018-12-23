package de.cieszynski.tm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class BrowserActivity extends AppCompatActivity implements TabLayout.BaseOnTabSelectedListener, View.OnClickListener {

    private WebView mWebView;
    private TabLayout mTabLayout;
    private FloatingActionButton mFab;
    private FloatingActionButton mFab1;
    private FloatingActionButton mFab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_browser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab1 = (FloatingActionButton) findViewById(R.id.fab1);
        mFab2 = (FloatingActionButton) findViewById(R.id.fab2);

        mFab.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTabLayout = findViewById(R.id.tablayout);
        mTabLayout.addOnTabSelectedListener(this);

        mWebView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (!TextUtils.isEmpty(title)) {
                    BrowserActivity.this.setTitle(title);
                }
            }

            @Override
            public boolean onConsoleMessage (ConsoleMessage consoleMessage) {
                Log.d("ConsoleMessage", consoleMessage.message());
                return true;
            }

        });
        if (savedInstanceState == null) {
            mWebView.loadUrl("file:///android_asset/index.html");
        } else {
            mWebView.restoreState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("XXX", "onSaveInstanceState");
        super.onSaveInstanceState(outState);

        mWebView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("XXX", "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        mWebView.restoreState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTabLayout.removeOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.d("XXX", "onTabSelected");

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        Log.d("XXX", "onTabUnselected");

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        Log.d("XXX", "onTabReselected");

    }

    @Override
    public void onClick(View v) {
        //mWebView.reload();
        mFab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        mFab2.animate().translationY(-getResources().getDimension(R.dimen.standard_100));
        mWebView.reload();
    }
}
