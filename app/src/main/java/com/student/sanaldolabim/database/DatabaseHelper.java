package com.student.sanaldolabim.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "SANAL_DOLABIM.DB";
    static final int DATABASE_VERSION = 10;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseTables.TABLE_DRAWER_CREATE);
        sqLiteDatabase.execSQL(DatabaseTables.TABLE_CLOTHES_CREATE);
        sqLiteDatabase.execSQL(DatabaseTables.TABLE_CABIN_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseTables.TABLE_DRAWER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseTables.TABLE_CLOTHES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseTables.TABLE_CABIN);
        onCreate(sqLiteDatabase);
    }

}
