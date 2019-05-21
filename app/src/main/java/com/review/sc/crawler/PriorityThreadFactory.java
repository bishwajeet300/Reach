package com.review.sc.crawler;

import android.os.Process;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.ThreadFactory;

public class PriorityThreadFactory implements ThreadFactory {

    public static final String TAG = PriorityThreadFactory.class.getName();

    private final int mThreadPriority;

    public PriorityThreadFactory(int mThreadPriority) {
        this.mThreadPriority = mThreadPriority;
    }


    @Override
    public Thread newThread(@NonNull final Runnable runnable) {
        Runnable wrapperRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Process.setThreadPriority(mThreadPriority);
                } catch(Exception e) {
                    Log.e(TAG, e.getMessage());
                }

                runnable.run();
            }
        };

        return new Thread(wrapperRunnable);
    }
}
