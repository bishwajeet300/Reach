package com.review.sc;

import android.app.Application;
import android.content.Context;

import com.review.sc.data.local.DatabaseHelper;
import com.review.sc.data.local.DatabaseManager;
import com.review.sc.data.remote.APIServices;
import com.review.sc.data.remote.RestClient;
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
    static DatabaseHelper provideDatabaseHelper(@ApplicationContext Context context) {
        return new DatabaseHelper(context);
    }


    @Provides
    @Singleton
    static DatabaseManager provideDatabaseManager(@ApplicationContext Context context, DatabaseHelper helper) {
        return new DatabaseManager(context, helper);
    }


    @Provides
    @Singleton
    static APIServices providesAPIServices() {
        return RestClient.getRetrofitInstance().create(APIServices.class);
    }
}
