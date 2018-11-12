package com.hitherejoe.animate.ui.activity;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hitherejoe.animate.R;

import butterknife.Bind;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    onitemClick onitemClick;
    float startX,startY;
    int duration=500,delay=200,wwidth,hheight;
    Context context;
    int ssy,ssx;

    public ListAdapter(MainActivity mainActivity, onitemClick onitemClick) {
        this.onitemClick=onitemClick;
        this.context=mainActivity;
    }
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        Button mAnimateText;
        ImageView mInterpolator,interpolator_same_set;
        boolean mIsButtonAtTop;

        public ViewHolder(View itemView) {
            super(itemView);

            mIsButtonAtTop = true;
            this.mAnimateText = (Button) itemView.findViewById(R.id.text_animate);
            this.mInterpolator = (ImageView) itemView.findViewById(R.id.interpolator);
            this.interpolator_same_set = (ImageView) itemView.findViewById(R.id.interpolator_same_set);
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mAnimateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {

                    holder.mInterpolator.setVisibility(View.GONE);

                    int fromLoc[] = new int[2];
                    holder.mInterpolator.getLocationOnScreen(fromLoc);
                    startX = fromLoc[0];
                    startY = fromLoc[1];
                    wwidth=holder.mInterpolator.getWidth();
                    hheight=holder.mInterpolator.getHeight();

                    ssy= (int) -(startY-20);
                    ssx=(MainActivity.win_widht-(3*MainActivity.tb_btn_right-10));

                    onitemClick.onitemClickcoord(startX,startY,wwidth,hheight);

                    holder.mInterpolator.animate().setInterpolator(new LinearInterpolator())
                            .setDuration(duration)
                            .setStartDelay(delay)
                            .translationYBy((float)ssy)
                            .translationXBy((float) ssx)
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {  }
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    holder.mInterpolator.animate().setInterpolator(new LinearInterpolator())
                                            .setDuration(duration)
                                            .setStartDelay(delay)
                                            .translationYBy((float)-ssy)
                                            .translationXBy((float)- ssx)
                                            .setListener(new Animator.AnimatorListener() {
                                                @Override
                                                public void onAnimationStart(Animator animation) { }
                                                @Override
                                                public void onAnimationEnd(Animator animation) {}
                                                @Override
                                                public void onAnimationCancel(Animator animation) { }
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
                Log.d("strt",startX+" "+startY);
            }
        });
    }
    @Override
    public int getItemCount() {
        return 6;
    }
}
