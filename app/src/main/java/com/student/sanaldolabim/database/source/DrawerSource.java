package com.student.sanaldolabim.database.source;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.student.sanaldolabim.database.DatabaseColumns;
import com.student.sanaldolabim.database.DatabaseSource;
import com.student.sanaldolabim.database.DatabaseTables;
import com.student.sanaldolabim.model.SDDrawer;

import java.util.ArrayList;
import java.util.List;

public class DrawerSource extends DatabaseSource {

    public DrawerSource(Context c) {
        super(c);
    }

    public static String[] getColumns() {
        return new String[]{DatabaseColumns._ID, DatabaseColumns.NAME};
    }

    private SDDrawer cursorToObject(Cursor cursor) {
        SDDrawer drawer = new SDDrawer();
        drawer.setId(cursor.getLong(cursor.getColumnIndex(DatabaseColumns._ID)));
        drawer.setName(cursor.getString(cursor.getColumnIndex(DatabaseColumns.NAME)));
        return drawer;
    }

    public void insert(SDDrawer drawer) {
        synchronized (databaseLock) {
            try {
                open();

                ContentValues contentValue = new ContentValues();
                contentValue.put(DatabaseColumns.NAME, drawer.getName());
                getDatabase().insert(DatabaseTables.TABLE_DRAWER, null, contentValue);
            } finally {
                close();
            }
        }
    }

    public List<SDDrawer> fetch() {
        return fetch(null);
    }

    public List<SDDrawer> fetch(String where) {
        final List<SDDrawer> drawers = new ArrayList<>();
        synchronized (databaseLock) {
            try {
                open();

                final Cursor cursor = getDatabase().query(DatabaseTables.TABLE_DRAWER, getColumns(), where, null, null, null, null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    SDDrawer file = cursorToObject(cursor);
                    drawers.add(file);
                    cursor.moveToNext();
                }
                cursor.close();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                close();
            }
            return drawers;
        }
    }

    public void delete(SDDrawer drawer) {
        synchronized (databaseLock) {
            try {
                open();

                getDatabase().delete(DatabaseTables.TABLE_DRAWER, DatabaseColumns._ID + "=" + drawer.getId(), null);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }
    }

}
