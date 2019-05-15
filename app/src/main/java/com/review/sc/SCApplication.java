package com.review.sc;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasBroadcastReceiverInjector;
import dagger.android.HasServiceInjector;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class SCApplication extends Application implements HasActivityInjector, HasServiceInjector, HasBroadcastReceiverInjector {

    @Inject
    DispatchingAndroidInjector<Activity> mActivityDispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<Service> mServiceDispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<BroadcastReceiver> mBroadcastReceiverDispatchingAndroidInjector;


    @Override
    public void onCreate() {
        super.onCreate();

        DaggerIApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/SNFNS-Light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityDispatchingAndroidInjector;
    }


    @Override
    public AndroidInjector<Service> serviceInjector() {
        return mServiceDispatchingAndroidInjector;
    }


    @Override
    public AndroidInjector<BroadcastReceiver> broadcastReceiverInjector() {
        return mBroadcastReceiverDispatchingAndroidInjector;
    }
}
