package com.example.grandslams;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class USOfragment extends Fragment {

    Context mContext;
    SwipeRefreshLayout refreshLayout;

    public USOfragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_usofragment, container, false);

        MainActivity.mywebview = view.findViewById(R.id.uso);
        refreshLayout=view.findViewById(R.id.refreshWidget);
        MainActivity.noNet=view.findViewById(R.id.USOnoNet);

        refreshLayout.setColorSchemeColors(Color.parseColor("#ffd60a"));

        if(MainActivity.USO_Bundle!=null)
        {
            if(! MainActivity.USO_Bundle.isEmpty())
            {
                MainActivity.getWebSettings();
                MainActivity.mywebview.restoreState(MainActivity.USO_Bundle);
            }
            else
                MainActivity.loadView("https://usopen.org/",mContext);
        }
        else
            MainActivity.loadView("https://usopen.org/",mContext);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String url=MainActivity.mywebview.getUrl();
                if(url==null)
                    url="https://usopen.org/";
                MainActivity.loadView(url,mContext);
                refreshLayout.setRefreshing(false);
            }
        });

        return view;
    }
    @Override
    public void onPause() {
        super.onPause();

        MainActivity.USO_Bundle= new Bundle();
        MainActivity.mywebview.saveState(MainActivity.USO_Bundle);
    }
}
