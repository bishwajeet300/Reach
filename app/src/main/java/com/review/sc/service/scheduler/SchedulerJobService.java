package com.review.sc.service.scheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import com.review.sc.data.local.DatabaseHelper;
import com.review.sc.data.local.DatabaseManager;
import com.review.sc.data.local.dao.TracksDAO;

public class SchedulerJobService extends JobService {

    public static final String TAG = SchedulerJobService.class.getSimpleName();
    DatabaseManager dbManager;
    TracksDAO tracksDAO;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e(TAG, "onStartJob");
        dbManager = new DatabaseManager(this, new DatabaseHelper(this));
        tracksDAO = new TracksDAO(dbManager);
        deleteDatabaseRecords();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }


    void deleteDatabaseRecords() {
        if(tracksDAO.getDataCount() > 0) {
            tracksDAO.delete(TracksDAO.TABLE_NAME, null, null);
        }
    }
}
