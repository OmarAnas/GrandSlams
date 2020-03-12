package com.example.grandslams;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class RGfragment extends Fragment {

    Context mContext;
    SwipeRefreshLayout refreshLayout;

    public RGfragment() {
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
        View view= inflater.inflate(R.layout.fragment_rgfragment, container, false);

        MainActivity.mywebview = view.findViewById(R.id.rg);
        MainActivity.noNet=view.findViewById(R.id.RGnoNet);

        refreshLayout=view.findViewById(R.id.refreshWidget);
        refreshLayout.setColorSchemeColors(Color.parseColor("#d45524"));

        if(MainActivity.RG_Bundle!=null)
        {
            if(! MainActivity.RG_Bundle.isEmpty())
            {
                MainActivity.getWebSettings();
                MainActivity.mywebview.restoreState(MainActivity.RG_Bundle);
            }
            else
                MainActivity.loadView("https://rolandgarros.com/",mContext);
        }
        else
            MainActivity.loadView("https://rolandgarros.com/",mContext);


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String url=MainActivity.mywebview.getUrl();
                if(url==null)
                    url="https://rolandgarros.com/";
                MainActivity.loadView(url,mContext);
                refreshLayout.setRefreshing(false);
            }
        });
        return view;
    }
    @Override
    public void onPause() {
        super.onPause();

        MainActivity.RG_Bundle= new Bundle();
        MainActivity.mywebview.saveState(MainActivity.RG_Bundle);
    }
}
