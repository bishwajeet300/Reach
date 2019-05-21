package com.review.sc.view.landing;

import com.review.sc.di.scope.PerActivity;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LandingModule {

    @PerActivity
    @Binds
    abstract ILandingContract.ILandingView provideLoginView(LandingActivity landingActivity);


    @PerActivity
    @Binds
    abstract ILandingContract.ILandingPresenter provideLoginPresenter(LandingPresenter landingPresenter);
}
