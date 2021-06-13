package com.student.sanaldolabim.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseSource {

    private DatabaseHelper databaseHelper;
    private Context context;
    private SQLiteDatabase database;
    public final Object databaseLock = new Object();

    public DatabaseSource(Context c) {
        context = c;
    }

    public DatabaseSource open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }
}
