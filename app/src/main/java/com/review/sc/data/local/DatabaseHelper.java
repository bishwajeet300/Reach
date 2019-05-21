package com.review.sc.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.review.sc.data.local.dao.TracksDAO;
import com.review.sc.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DatabaseHelper extends SQLiteOpenHelper {

    private static String TAG = DatabaseHelper.class.getName();
    private static final String DATABASE_NAME = "SCReview";
    private static final int DATABASE_VERSION = 1;


    @Inject
    public DatabaseHelper(@ApplicationContext Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(TAG, "DatabaseHelper called");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "DatabaseHelper onCreate Version number is " + db.getVersion());
        createTableStructures(db);
    }


    private void createTableStructures(SQLiteDatabase db) {
        db.execSQL(TracksDAO.getCreateTableQuery());
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}