package com.review.sc.view.splash;

import com.review.sc.di.scope.PerActivity;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SplashModule {

    @PerActivity
    @Binds
    abstract ISplashContract.ISplashView provideLoginView(SplashActivity splashActivity);


    @PerActivity
    @Binds
    abstract ISplashContract.ISplashPresenter provideSplashPresenter(SplashPresenter SplashPresenter);
}
