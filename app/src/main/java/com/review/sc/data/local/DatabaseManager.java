package com.review.sc.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.review.sc.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DatabaseManager {
    private static final String TAG = "DatabaseManager";
    private static DatabaseManager instance;
    private Context context;

    private static DatabaseHelper mDatabaseHelper;
    private int mOpenCounter;
    private SQLiteDatabase mDatabase;


    @Inject
    public DatabaseManager(@ApplicationContext Context context, DatabaseHelper helper) {
        mDatabaseHelper = helper;
        this.context = context;
    }


    public synchronized SQLiteDatabase getWritableDatabase() throws SQLiteException {
        mOpenCounter++;
        return mDatabaseHelper.getWritableDatabase();
    }


    public synchronized SQLiteDatabase getReadableDatabase() throws SQLiteException {
        mOpenCounter++;
        return mDatabaseHelper.getReadableDatabase();
    }


    public synchronized void closeDatabase() throws SQLiteException {
        mOpenCounter--;
        if (mOpenCounter == 0) {
            mDatabaseHelper.close();
        }
    }
}
