package com.example.grandslams;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    static WebView mywebview;
    static BottomNavigationView navView;
    static ConstraintLayout noNet;
    static Bundle Ao_Bundle,RG_Bundle,wimby_Bundle,USO_Bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         navView = findViewById(R.id.nav_view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.whiteColor));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        navView.setItemIconTintList(null);
        navView.setOnNavigationItemSelectedListener(this);
        homeFragment homeFrag = new homeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,homeFrag).commit();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem){
        if (menuItem.getItemId()==R.id.navigation_home)
        {
            homeFragment homeFrag = new homeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,homeFrag).commit();

            navView.getMenu().getItem(1).setIcon(R.drawable.ic_ao_black);
            navView.getMenu().getItem(3).setIcon(R.drawable.ic_wimby_black);
            navView.getMenu().getItem(2).setIcon(R.drawable.ic_rg_black);
            navView.getMenu().getItem(4).setIcon(R.drawable.ic_uso_black);
        }
        else if (menuItem.getItemId()==R.id.navigation_ao)
        {
            AOfragment aoFrag = new AOfragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,aoFrag).commit();

            menuItem.setIcon(R.drawable.ao_colored);
            navView.setItemIconTintList(null);

            navView.getMenu().getItem(3).setIcon(R.drawable.ic_wimby_black);
            navView.getMenu().getItem(2).setIcon(R.drawable.ic_rg_black);
            navView.getMenu().getItem(4).setIcon(R.drawable.ic_uso_black);

        }
        else if (menuItem.getItemId()==R.id.navigation_wimbledon)
        {
            wimbledonFragment wimbyFrag = new wimbledonFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,wimbyFrag).commit();

            menuItem.setIcon(R.drawable.wimby_colored);
            navView.setItemIconTintList(null);

            navView.getMenu().getItem(1).setIcon(R.drawable.ic_ao_black);
            navView.getMenu().getItem(2).setIcon(R.drawable.ic_rg_black);
            navView.getMenu().getItem(4).setIcon(R.drawable.ic_uso_black);

        }
        else if (menuItem.getItemId()==R.id.navigation_rg)
        {
            RGfragment rgFrag = new RGfragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,rgFrag).commit();

            menuItem.setIcon(R.drawable.rg_colored);
            navView.setItemIconTintList(null);

            navView.getMenu().getItem(3).setIcon(R.drawable.ic_wimby_black);
            navView.getMenu().getItem(1).setIcon(R.drawable.ic_ao_black);
            navView.getMenu().getItem(4).setIcon(R.drawable.ic_uso_black);

        }
        else if (menuItem.getItemId()==R.id.navigation_uso)
        {
            USOfragment usFrag = new USOfragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,usFrag).commit();

            menuItem.setIcon(R.drawable.uso_colored);
            navView.setItemIconTintList(null);

            navView.getMenu().getItem(3).setIcon(R.drawable.ic_wimby_black);
            navView.getMenu().getItem(2).setIcon(R.drawable.ic_rg_black);
            navView.getMenu().getItem(1).setIcon(R.drawable.ic_ao_black);

        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onBackPressed() {
        if (mywebview == null)
            super.onBackPressed();
        else {
        if (mywebview.canGoBack()) {
            mywebview.goBack();
        } else {
            super.onBackPressed();
        }
    }
    }

    public static void getWebSettings()
    {
        MainActivity.mywebview.getSettings().setJavaScriptEnabled(true);
        MainActivity.mywebview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        MainActivity.mywebview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        MainActivity.mywebview.getSettings().setAppCacheEnabled(true);
        MainActivity.mywebview.getSettings().setDomStorageEnabled(true);
        MainActivity.mywebview.getSettings().setUseWideViewPort(true);
        MainActivity.mywebview.getSettings().setEnableSmoothTransition(true);
        MainActivity.mywebview.getSettings().setBuiltInZoomControls(true);
        MainActivity.mywebview.getSettings().setDisplayZoomControls(false);

        mywebview.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                mywebview.setVisibility(View.GONE);
                noNet.setVisibility(View.VISIBLE);
            }
        });
        mywebview.setWebChromeClient(new WebChromeClient());
    }
    public static void loadView(String url, Context context)
    {
        if(!isConnected(context)){
            mywebview.setVisibility(View.GONE);
            noNet.setVisibility(View.VISIBLE);
        }
       else {

           mywebview.setVisibility(View.VISIBLE);
           noNet.setVisibility(View.GONE);

           getWebSettings();

           mywebview.loadUrl(url);
//           mywebview.clearCache(true);
        }
    }

    public static boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != cm) {
            NetworkInfo info = cm.getActiveNetworkInfo();

            return (info != null && info.isConnected());
        }
        return false;
    }

}
