package com.review.sc.data.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDatabaseManager<T> {

    private static String TAG = AbstractDatabaseManager.class.getSimpleName();

    private SQLiteDatabase database;
    private DatabaseManager databaseManager;


    public AbstractDatabaseManager(DatabaseManager dbManager) {
        this.databaseManager = dbManager;
    }

    public abstract ContentValues generateContentValuesFromObject(T t);
    public abstract T generateObjectFromCursor(Cursor c);


    protected boolean saveAll(AbstractDatabaseManager abstractDM, String tableName, List<T> entity) {
        database = databaseManager.getWritableDatabase();
        long result = 0;

        if (null == entity) {
            return false;
        }

        try {
            for (int i = 0; i < entity.size(); i++) {
                try {
                    result = database.insertWithOnConflict(tableName, null, abstractDM.generateContentValuesFromObject(entity.get(i)), SQLiteDatabase.CONFLICT_IGNORE);
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            databaseManager.closeDatabase();
        }

        return result != -1;
    }


    protected long count(final String TABLE_NAME) {
        long count;
        try {
            database = databaseManager.getReadableDatabase();
            count = DatabaseUtils.queryNumEntries(database, TABLE_NAME);
            databaseManager.closeDatabase();
        } catch (Exception | OutOfMemoryError e) {
            if (e instanceof OutOfMemoryError) {
                System.gc();
            }
            System.gc();
            count = 0;
        }
        return count;
    }


    protected LinkedList load(AbstractDatabaseManager abstractDM, String query) {
        database = databaseManager.getReadableDatabase();

        Cursor cursor = database.rawQuery(query, null);
        LinkedList<Object> dataObject = new LinkedList<>();
        try {
            if (cursor != null && cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    dataObject.add(abstractDM.generateObjectFromCursor(cursor));
                    cursor.moveToNext();
                }
                cursor.close();
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        databaseManager.closeDatabase();
        return dataObject;
    }


    public void delete(String table, String whereClause, String[] whereArgs) {
        database = databaseManager.getWritableDatabase();

        Log.e(TAG, "delete: " + whereClause);
        database.delete(table, whereClause, whereArgs);
        databaseManager.closeDatabase();
    }
}
