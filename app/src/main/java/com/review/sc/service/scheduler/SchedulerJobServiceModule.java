package com.review.sc.service.scheduler;

import android.os.Build;
import android.support.annotation.RequiresApi;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SchedulerJobServiceModule {

    @ContributesAndroidInjector
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    abstract SchedulerJobService providesJobService();
}
