package com.review.sc.view.splash;

import javax.inject.Inject;

public class SplashPresenter implements ISplashContract.ISplashPresenter {


    @Inject
    ISplashContract.ISplashView mView;

    @Inject
    public SplashPresenter() {

    }
}
