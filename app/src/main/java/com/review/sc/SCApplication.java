package com.review.sc;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;

import com.flurry.android.FlurryAgent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasBroadcastReceiverInjector;
import dagger.android.HasServiceInjector;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

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


        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/Raleway-Regular.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

        new FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(this, "MZ8QRCBB9DYXPZN4X5Y4");
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
