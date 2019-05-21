package com.review.sc.service.scheduler;

import android.app.AlarmManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

public class SchedularUtil {

    public static final String TAG = SchedularUtil.class.getSimpleName();
    private static int jobId = 5555;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void scheduleJob(Context context) {
        ComponentName serviceComponent = new ComponentName(context, SchedulerJobService.class);

        JobInfo.Builder builder = new JobInfo.Builder(jobId, serviceComponent);
        builder.setPeriodic(AlarmManager.INTERVAL_HOUR / 4);
        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);

        try {
            if (jobScheduler != null) {
                jobScheduler.schedule(builder.build());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void cancelJob(Context context) {
        try {
            JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
            if (jobScheduler != null) {
                jobScheduler.cancel(jobId);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
