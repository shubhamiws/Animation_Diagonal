package com.hitherejoe.animate.ui.activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.hitherejoe.animate.R;

public class MainActivity extends AppCompatActivity {

    static RecyclerView mRecyclerView;
    ListAdapter mListadapter;
    onitemClick onitemClick;
    RelativeLayout.LayoutParams rel_btn;
    ImageView mInterpolator_main;
    ImageView ii;
    int duration=900;
    int delay=200;
    static int win_widht;

    static int tb_btn_right;
    int tb_btn_top;
    int tb_btn_left;
    int tb_btn_bottom;
    Toolbar toolbar;
    int exx,eyy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterpolator_main = (ImageView)findViewById(R.id.interpolator_main);
        ii = (ImageView)findViewById(R.id.ii);
        rel_btn = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        toolbar= (Toolbar) findViewById(R.id.toolbar);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        win_widht = displayMetrics.widthPixels;

        tb_btn_right =ii.getPaddingRight();
        tb_btn_top=ii.getPaddingTop();
        tb_btn_left=ii.getPaddingLeft();
        tb_btn_bottom=ii.getPaddingBottom();

        onitemClick=new onitemClick() {
            @Override
            public void onitemClickcoord(final float startX, final float startY, final int wwidht, final int hheight)
            {

                rel_btn.leftMargin = (int) startX;
                rel_btn.topMargin = (int) startY-75;
                rel_btn.width=wwidht;
                rel_btn.height=hheight;

                mInterpolator_main.setLayoutParams(rel_btn);
                mInterpolator_main.setVisibility(View.VISIBLE);

                mInterpolator_main.bringToFront();
                exx=(win_widht);
                eyy= (int) -(startY);

                mInterpolator_main.animate().setInterpolator(new LinearInterpolator())
                        .setDuration(1000)
                        .setStartDelay(delay)
                        .scaleXBy(-1)
                        .scaleYBy(-1)
                        .translationYBy((float) eyy)
                        .translationXBy((float) exx)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {}
                            @Override
                            public void onAnimationEnd(Animator animation) {

                                mInterpolator_main.setVisibility(View.INVISIBLE);

                                mInterpolator_main.animate().setInterpolator(new LinearInterpolator())
                                        .setDuration(50)
                                        .setStartDelay(100)
                                        .scaleXBy(1)
                                        .scaleYBy(1)
                                        .translationYBy((float) -eyy)
                                        .translationXBy((float) -exx)
                                        .setListener(new Animator.AnimatorListener() {
                                            @Override
                                            public void onAnimationStart(Animator animation) {}
                                            @Override
                                            public void onAnimationEnd(Animator animation) {}
                                            @Override
                                            public void onAnimationCancel(Animator animation) {}
                                            @Override
                                            public void onAnimationRepeat(Animator animation) { }
                                        })
                                        .start();
                            }
                            @Override
                            public void onAnimationCancel(Animator animation) { }
                            @Override
                            public void onAnimationRepeat(Animator animation) { }
                        })
                        .start();
            }
        };

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(layoutManager);
        mListadapter = new ListAdapter(this,onitemClick);
        mRecyclerView.setAdapter(mListadapter);

    }

}
