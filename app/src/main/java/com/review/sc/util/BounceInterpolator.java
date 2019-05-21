package com.review.sc.util;

public class BounceInterpolator implements android.view.animation.Interpolator {

    double mAmplitude = 1;
    double mFrequency = 10;

    public BounceInterpolator(double amplitude, double frequency) {
        mAmplitude = .15;
        mFrequency = 15;
    }

    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time / mAmplitude) *
                Math.cos(mFrequency * time) + 1);
    }
}
