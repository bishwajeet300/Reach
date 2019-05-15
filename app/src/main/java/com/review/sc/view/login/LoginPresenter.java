package com.review.sc.view.login;

import android.content.Context;

import com.review.sc.di.ApplicationContext;

import javax.inject.Inject;

public class LoginPresenter implements ILoginContract.ILoginPresenter {

    @Inject
    @ApplicationContext
    Context context;


    @Inject
    ILoginContract.ILoginView mView;


    @Inject
    public LoginPresenter() {

    }
}
