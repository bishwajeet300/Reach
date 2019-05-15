package com.review.sc.view.login;

import com.review.sc.di.scope.PerActivity;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoginModule  {

    @PerActivity
    @Binds
    abstract ILoginContract.ILoginView provideLoginView(LoginActivity loginActivity);


    @PerActivity
    @Binds
    abstract ILoginContract.ILoginPresenter provideLoginPresenter(LoginPresenter loginPresenter);
}
