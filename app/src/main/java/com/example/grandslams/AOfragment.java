package com.example.grandslams;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AOfragment extends Fragment {

    Context mContext;
    SwipeRefreshLayout refreshLayout;

    public AOfragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_aofragment, container, false);

        MainActivity.mywebview = view.findViewById(R.id.ao);
        refreshLayout=view.findViewById(R.id.refreshWidget);

        refreshLayout.setColorSchemeColors(Color.parseColor("#0091d2"));
        MainActivity.noNet=view.findViewById(R.id.AOnoNet);


        if(MainActivity.Ao_Bundle!=null)
        {
            if(!MainActivity.Ao_Bundle.isEmpty())
            {
                MainActivity.getWebSettings();
                MainActivity.mywebview.restoreState(MainActivity.Ao_Bundle);
            }
            else
                MainActivity.loadView("https://ausopen.com/",mContext);
        }
        else
            MainActivity.loadView("https://ausopen.com/",mContext);


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String url=MainActivity.mywebview.getUrl();
                if(url==null)
                    url="https://ausopen.com/";
                MainActivity.loadView(url,mContext);
                refreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        MainActivity.Ao_Bundle= new Bundle();
        MainActivity.mywebview.saveState(MainActivity.Ao_Bundle);
    }
}
