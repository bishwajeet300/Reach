package com.review.sc.crawler;

import android.os.Process;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DefaultExecutorSupplier {

    // Number of cores to decide the number of threads
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();


    // ThreadPoolExecutor for background tasks
    private final ThreadPoolExecutor mForBackgroundTasks;


    // ThreadPoolExecutor for main thread tasks
    private final Executor mMainThreadExecutor;


    // An instance of DefaultExecutorSupplier
    private static DefaultExecutorSupplier sInstance;


    // Returns the instance of DefaultExecutorSupplier
    public static DefaultExecutorSupplier getInstance() {
        if (sInstance == null) {
            synchronized (DefaultExecutorSupplier.class) {
                sInstance = new DefaultExecutorSupplier();
            }
        }
        return sInstance;
    }


    private DefaultExecutorSupplier() {

        ThreadFactory backgroundPriorityThreadFactory = new PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND);

        // Setting the ThreadPoolExecutor for BackgroundTasks;
        mForBackgroundTasks = new ThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                backgroundPriorityThreadFactory
        );

        // Setting the ThreadPoolExecutor for MainThreadExecutor;
        mMainThreadExecutor = new MainThreadExecutor();
    }


    // Returns the thread pool executor for background task
    public ThreadPoolExecutor forBackgroundTasks() {
        return mForBackgroundTasks;
    }


    // Returns the thread pool executor for main thread task
    public Executor forMainThreadTasks() {
        return mMainThreadExecutor;
    }
}
