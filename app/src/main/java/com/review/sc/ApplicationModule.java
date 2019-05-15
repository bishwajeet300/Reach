package com.review.sc;

import android.app.Application;
import android.content.Context;

import com.review.sc.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    @ApplicationContext
    public Context provideContext(Application application) {
        return application.getApplicationContext();
    }


    @Provides
    @Singleton
    public SCApplication provideApplication(Application application) {
        return (SCApplication) application;
    }
}
