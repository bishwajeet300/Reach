package com.review.sc.view.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.review.sc.di.ActivityContext;
import com.review.sc.di.scope.PerActivity;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BaseActivityModule {

    @Binds
    @PerActivity
    @ActivityContext
    abstract Context activityContext(AppCompatActivity activity);
}
