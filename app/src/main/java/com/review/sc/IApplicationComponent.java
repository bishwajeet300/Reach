package com.review.sc;

import android.app.Application;

import com.review.sc.data.local.DatabaseHelper;
import com.review.sc.data.local.DatabaseManager;
import com.review.sc.data.remote.APIServices;
import com.review.sc.service.scheduler.SchedulerJobService;
import com.review.sc.service.scheduler.SchedulerJobServiceModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ApplicationModule.class, ActivityBuilderModule.class, SchedulerJobServiceModule.class})

interface IApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        IApplicationComponent build();
    }

    void inject(SchedulerJobService schedulerJobService);

    void inject(SCApplication application);

    DatabaseHelper getDatabaseHelper();

    DatabaseManager getDatabaseManager();

    APIServices getAPIServices();
}
