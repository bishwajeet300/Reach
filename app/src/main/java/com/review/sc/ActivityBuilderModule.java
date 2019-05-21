package com.review.sc;

import com.review.sc.di.scope.PerActivity;
import com.review.sc.view.landing.LandingActivity;
import com.review.sc.view.landing.LandingModule;
import com.review.sc.view.splash.SplashActivity;
import com.review.sc.view.splash.SplashModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    //Used @ContributesAndroidInjector as no subComponents were present for this Activity
    @PerActivity
    @ContributesAndroidInjector(modules = SplashModule.class)
    abstract SplashActivity splashActivityInjector();


    //Used @ContributesAndroidInjector as no subComponents were present for this Activity
    @PerActivity
    @ContributesAndroidInjector(modules = LandingModule.class)
    abstract LandingActivity loginActivityInjector();
}
