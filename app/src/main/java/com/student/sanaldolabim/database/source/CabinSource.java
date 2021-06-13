package com.student.sanaldolabim.database.source;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.student.sanaldolabim.database.DatabaseColumns;
import com.student.sanaldolabim.database.DatabaseSource;
import com.student.sanaldolabim.database.DatabaseTables;
import com.student.sanaldolabim.model.SDCabin;

import java.util.ArrayList;
import java.util.List;

public class CabinSource extends DatabaseSource {

    private ClothesSource clothesSource;

    public CabinSource(Context c) {
        super(c);
        clothesSource = new ClothesSource(c);
    }

    public static String[] getColumns() {
        return new String[]{DatabaseColumns._ID, DatabaseColumns.NAME, DatabaseColumns.SECTION_1, DatabaseColumns.SECTION_2, DatabaseColumns.SECTION_3, DatabaseColumns.SECTION_4, DatabaseColumns.SECTION_5};
    }

    private SDCabin cursorToObject(Cursor cursor) {
        SDCabin cabin = new SDCabin();
        cabin.setId(cursor.getLong(cursor.getColumnIndex(DatabaseColumns._ID)));
        cabin.setName(cursor.getString(cursor.getColumnIndex(DatabaseColumns.NAME)));
        if (clothesSource.fetch(DatabaseColumns._ID + "=" + cursor.getLong(cursor.getColumnIndex(DatabaseColumns.SECTION_1))).size() > 0)
            cabin.setSectionOne(clothesSource.fetch(DatabaseColumns._ID + "=" + cursor.getLong(cursor.getColumnIndex(DatabaseColumns.SECTION_1))).get(0));
        if (clothesSource.fetch(DatabaseColumns._ID + "=" + cursor.getLong(cursor.getColumnIndex(DatabaseColumns.SECTION_2))).size() > 0)
            cabin.setSectionTwo(clothesSource.fetch(DatabaseColumns._ID + "=" + cursor.getLong(cursor.getColumnIndex(DatabaseColumns.SECTION_2))).get(0));
        if (clothesSource.fetch(DatabaseColumns._ID + "=" + cursor.getLong(cursor.getColumnIndex(DatabaseColumns.SECTION_3))).size() > 0)
            cabin.setSectionThree(clothesSource.fetch(DatabaseColumns._ID + "=" + cursor.getLong(cursor.getColumnIndex(DatabaseColumns.SECTION_3))).get(0));
        if (clothesSource.fetch(DatabaseColumns._ID + "=" + cursor.getLong(cursor.getColumnIndex(DatabaseColumns.SECTION_4))).size() > 0)
            cabin.setSectionFour(clothesSource.fetch(DatabaseColumns._ID + "=" + cursor.getLong(cursor.getColumnIndex(DatabaseColumns.SECTION_4))).get(0));
        if (clothesSource.fetch(DatabaseColumns._ID + "=" + cursor.getLong(cursor.getColumnIndex(DatabaseColumns.SECTION_5))).size() > 0)
            cabin.setSectionFive(clothesSource.fetch(DatabaseColumns._ID + "=" + cursor.getLong(cursor.getColumnIndex(DatabaseColumns.SECTION_5))).get(0));
        return cabin;
    }

    public void insert(SDCabin cabin) {
        synchronized (databaseLock) {
            try {
                open();

                ContentValues contentValue = new ContentValues();
                contentValue.put(DatabaseColumns.NAME, cabin.getName());
                contentValue.put(DatabaseColumns.SECTION_1, cabin.getSectionOne().getId());
                contentValue.put(DatabaseColumns.SECTION_2, cabin.getSectionTwo().getId());
                contentValue.put(DatabaseColumns.SECTION_3, cabin.getSectionThree().getId());
                contentValue.put(DatabaseColumns.SECTION_4, cabin.getSectionFour().getId());
                contentValue.put(DatabaseColumns.SECTION_5, cabin.getSectionFive().getId());
                getDatabase().insert(DatabaseTables.TABLE_CABIN, null, contentValue);
            } finally {
                close();
            }
        }
    }

    public List<SDCabin> fetch() {
        return fetch(null);
    }

    public List<SDCabin> fetch(String where) {
        final List<SDCabin> cabins = new ArrayList<>();
        synchronized (databaseLock) {
            try {
                open();

                final Cursor cursor = getDatabase().query(DatabaseTables.TABLE_CABIN, getColumns(), where, null, null, null, null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    SDCabin file = cursorToObject(cursor);
                    cabins.add(file);
                    cursor.moveToNext();
                }
                cursor.close();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                close();
            }
            return cabins;
        }
    }

    public void delete(SDCabin cabin) {
        synchronized (databaseLock) {
            try {
                open();

                getDatabase().delete(DatabaseTables.TABLE_CABIN, DatabaseColumns._ID + "=" + cabin.getId(), null);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }
    }

}
