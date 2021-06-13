package com.student.sanaldolabim.database.source;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.student.sanaldolabim.database.DatabaseColumns;
import com.student.sanaldolabim.database.DatabaseSource;
import com.student.sanaldolabim.database.DatabaseTables;
import com.student.sanaldolabim.model.SDClothes;
import com.student.sanaldolabim.model.SDDrawer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClothesSource extends DatabaseSource {

    private DrawerSource drawerSource;

    public ClothesSource(Context c) {
        super(c);
        drawerSource = new DrawerSource(c);
    }

    public static String[] getColumns() {
        return new String[]{DatabaseColumns._ID, DatabaseColumns.NAME, DatabaseColumns.DRAWER_ID,
                DatabaseColumns.TYPE, DatabaseColumns.COLOR,
                DatabaseColumns.PATTERN, DatabaseColumns.PURCHASE_DATE,
                DatabaseColumns.PRICE, DatabaseColumns.PHOTO_URL};
    }

    private SDClothes cursorToObject(Cursor cursor) {
        SDClothes clothe = new SDClothes();
        clothe.setId(cursor.getLong(cursor.getColumnIndex(DatabaseColumns._ID)));
        clothe.setName(cursor.getString(cursor.getColumnIndex(DatabaseColumns.NAME)));
        clothe.setClotheType(SDClothes.ClotheType.valueOf(cursor.getString(cursor.getColumnIndex(DatabaseColumns.TYPE))));
        clothe.setColor(SDClothes.ClotheColor.valueOf(cursor.getString(cursor.getColumnIndex(DatabaseColumns.COLOR))));
        clothe.setPattern(SDClothes.ClothePattern.valueOf(cursor.getString(cursor.getColumnIndex(DatabaseColumns.PATTERN))));
        clothe.setPurchaseDate(new Date(cursor.getLong(cursor.getColumnIndex(DatabaseColumns.PURCHASE_DATE))));
        clothe.setPrice(cursor.getDouble(cursor.getColumnIndex(DatabaseColumns.PRICE)));
        clothe.setPhoto(cursor.getString(cursor.getColumnIndex(DatabaseColumns.PHOTO_URL)));

        if (drawerSource.fetch(DatabaseColumns._ID + "=" + cursor.getLong(cursor.getColumnIndex(DatabaseColumns.DRAWER_ID))).size() > 0)
            clothe.setSdDrawer(drawerSource.fetch(DatabaseColumns._ID + "=" + cursor.getLong(cursor.getColumnIndex(DatabaseColumns.DRAWER_ID))).get(0));
        return clothe;
    }

    public void insert(SDClothes clothe) {
        synchronized (databaseLock) {
            try {
                open();

                ContentValues contentValue = new ContentValues();
                contentValue.put(DatabaseColumns.DRAWER_ID, clothe.getSdDrawer().getId());
                contentValue.put(DatabaseColumns.NAME, clothe.getName());
                contentValue.put(DatabaseColumns.TYPE, clothe.getClotheType().toString());
                contentValue.put(DatabaseColumns.COLOR, clothe.getColor().toString());
                contentValue.put(DatabaseColumns.PATTERN, clothe.getPattern().toString());
                contentValue.put(DatabaseColumns.PURCHASE_DATE, clothe.getPurchaseDate().getTime());
                contentValue.put(DatabaseColumns.PRICE, clothe.getPrice());
                contentValue.put(DatabaseColumns.PHOTO_URL, clothe.getPhoto());
                getDatabase().insert(DatabaseTables.TABLE_CLOTHES, null, contentValue);
            } finally {
                close();
            }
        }
    }

    public List<SDClothes> fetch() {
        return fetch(null);
    }

    public List<SDClothes> fetch(String where) {
        final List<SDClothes> clothes = new ArrayList<>();
        synchronized (databaseLock) {
            try {
                open();

                final Cursor cursor = getDatabase().query(DatabaseTables.TABLE_CLOTHES, getColumns(), where, null, null, null, null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    SDClothes file = cursorToObject(cursor);
                    clothes.add(file);
                    cursor.moveToNext();
                }
                cursor.close();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                close();
            }
            return clothes;
        }
    }
}
