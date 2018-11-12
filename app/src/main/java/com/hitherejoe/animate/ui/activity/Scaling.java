package com.hitherejoe.animate.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.hitherejoe.animate.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Scaling extends AppCompatActivity {

    @Bind(R.id.fab_smile)
    FloatingActionButton mSmileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_properties);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.text_start_animation)
    public void onAnimateTextClicked() {
        buildAndStartAnimation(mSmileButton);
    }

    private void buildAndStartAnimation(View view) {
        ViewPropertyAnimator animator = view.animate();
            float animationValue = view.getScaleY() == 0f ? 1f : 0f;
            animator.scaleX(animationValue).scaleY(animationValue);
        animator.start();
    }


}