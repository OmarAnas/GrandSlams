package com.example.grandslams;


import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;



/**
 * A simple {@link Fragment} subclass.
 */
public class homeFragment extends Fragment implements  View.OnClickListener, GestureDetector.OnGestureListener {

    Context mContext;
    ImageView cross,ao,wimby,rg,uso;
    Animation rotateAnimation;
    ConstraintLayout scrollView;
    GestureDetector detector;
    public homeFragment() {
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
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        cross=view.findViewById(R.id.imageView);
        ao=view.findViewById(R.id.aoImg);
        wimby=view.findViewById(R.id.wimbyImg);
        rg=view.findViewById(R.id.rgimg);
        uso=view.findViewById(R.id.usoImg);
        scrollView=view.findViewById(R.id.idforsc);
        detector = new GestureDetector(mContext,this);

        ao.setOnClickListener(this);
        wimby.setOnClickListener(this);
        rg.setOnClickListener(this);
        uso.setOnClickListener(this);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return detector.onTouchEvent(motionEvent);
            }
        });
        rotateAnimation= AnimationUtils.loadAnimation(mContext,R.anim.rotate);
        scrollView.startAnimation(rotateAnimation);

        if(MainActivity.mywebview!=null)
          MainActivity.mywebview.clearCache(true);
        return view;
    }



    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.aoImg){
            AOfragment aoFrag = new AOfragment();
            getFragmentManager().beginTransaction().replace(R.id.frameLayout,aoFrag).commit();
            MainActivity.navView.getMenu().getItem(1).setChecked(true);

            MainActivity.navView.getMenu().getItem(1).setIcon(R.drawable.ao_colored);
            MainActivity.navView.setItemIconTintList(null);
            MainActivity.navView.getMenu().getItem(3).setIcon(R.drawable.ic_wimby_black);
            MainActivity.navView.getMenu().getItem(2).setIcon(R.drawable.ic_rg_black);
            MainActivity.navView.getMenu().getItem(4).setIcon(R.drawable.ic_uso_black);
        }
        else if (view.getId()==R.id.wimbyImg){
            wimbledonFragment wimbyFrag = new wimbledonFragment();
            getFragmentManager().beginTransaction().replace(R.id.frameLayout,wimbyFrag).commit();
            MainActivity.navView.getMenu().getItem(3).setChecked(true);

            MainActivity.navView.getMenu().getItem(3).setIcon(R.drawable.wimby_colored);
            MainActivity.navView.setItemIconTintList(null);
            MainActivity.navView.getMenu().getItem(1).setIcon(R.drawable.ic_ao_black);
            MainActivity.navView.getMenu().getItem(2).setIcon(R.drawable.ic_rg_black);
            MainActivity.navView.getMenu().getItem(4).setIcon(R.drawable.ic_uso_black);
        }
        else if (view.getId()==R.id.rgimg){
            RGfragment rgFrag = new RGfragment();
            getFragmentManager().beginTransaction().replace(R.id.frameLayout,rgFrag).commit();
            MainActivity.navView.getMenu().getItem(2).setChecked(true);

            MainActivity.navView.getMenu().getItem(2).setIcon(R.drawable.rg_colored);
            MainActivity.navView.setItemIconTintList(null);
            MainActivity.navView.getMenu().getItem(3).setIcon(R.drawable.ic_wimby_black);
            MainActivity.navView.getMenu().getItem(1).setIcon(R.drawable.ic_ao_black);
            MainActivity.navView.getMenu().getItem(4).setIcon(R.drawable.ic_uso_black);
        }
        else if (view.getId()==R.id.usoImg){
            USOfragment usFrag = new USOfragment();
            getFragmentManager().beginTransaction().replace(R.id.frameLayout,usFrag).commit();
            MainActivity.navView.getMenu().getItem(4).setChecked(true);

            MainActivity.navView.getMenu().getItem(4).setIcon(R.drawable.uso_colored);
            MainActivity.navView.setItemIconTintList(null);
            MainActivity.navView.getMenu().getItem(3).setIcon(R.drawable.ic_wimby_black);
            MainActivity.navView.getMenu().getItem(2).setIcon(R.drawable.ic_rg_black);
            MainActivity.navView.getMenu().getItem(1).setIcon(R.drawable.ic_ao_black);
        }
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

        rotateAnimation= AnimationUtils.loadAnimation(mContext,R.anim.rotate);
        scrollView.startAnimation(rotateAnimation);

        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }


}
