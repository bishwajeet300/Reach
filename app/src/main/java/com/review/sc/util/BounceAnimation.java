package com.review.sc.util;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.review.sc.R;

public class BounceAnimation {

    private Context context;
    private BounceAnimationEndListner bounceAnimationEndListner;

    public BounceAnimation(Context context) {
        this.context = context;
    }


    public BounceAnimation(Context context, BounceAnimationEndListner bounceAnimationEndListner) {
        this.context = context;
        this.bounceAnimationEndListner = bounceAnimationEndListner;
    }


    public void loadAnimation(View view) {
        view.clearAnimation();
        final Animation bouceAnimation = AnimationUtils.loadAnimation(context, R.anim.bounce);
        BounceInterpolator interpolator = new BounceInterpolator(0.2, 10);
        bouceAnimation.setInterpolator(interpolator);
        view.startAnimation(bouceAnimation);
        bouceAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (bounceAnimationEndListner != null) {
                    bounceAnimationEndListner.onAnimationEnd();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    public interface BounceAnimationEndListner {
        void onAnimationEnd();
    }
}
