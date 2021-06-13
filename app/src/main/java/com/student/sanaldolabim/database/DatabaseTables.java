package com.student.sanaldolabim.database;

public class DatabaseTables {

    public static final String TABLE_DRAWER = "SD_DRAWER";
    public static final String TABLE_DRAWER_CREATE = "create table " + TABLE_DRAWER + " ("
            + DatabaseColumns._ID + " integer primary key autoincrement, "
            + DatabaseColumns.NAME + " string not null);";

    public static final String TABLE_CLOTHES = "SD_CLOTHES";
    public static final String TABLE_CLOTHES_CREATE = "create table " + TABLE_CLOTHES + " ("
            + DatabaseColumns._ID + " integer primary key autoincrement, "
            + DatabaseColumns.NAME + " string, "
            + DatabaseColumns.DRAWER_ID + " integer, "
            + DatabaseColumns.TYPE + " string not null, "
            + DatabaseColumns.COLOR + " string, "
            + DatabaseColumns.PATTERN + " string not null, "
            + DatabaseColumns.PURCHASE_DATE + " int, "
            + DatabaseColumns.PRICE + " NUMERIC, "
            + DatabaseColumns.PHOTO_URL + " string ,"
            + " FOREIGN KEY (" + DatabaseColumns.DRAWER_ID + ") REFERENCES " + TABLE_DRAWER + "(" + DatabaseColumns._ID + "));"
            + ");";

    public static final String TABLE_CABIN = "SD_CABIN";
    public static final String TABLE_CABIN_CREATE = "create table " + TABLE_CABIN + " ("
            + DatabaseColumns._ID + " integer primary key autoincrement, " + DatabaseColumns.NAME + " string not null, "
            + DatabaseColumns.SECTION_1 + " integer, "
            + DatabaseColumns.SECTION_2 + " integer, "
            + DatabaseColumns.SECTION_3 + " integer, "
            + DatabaseColumns.SECTION_4 + " integer, "
            + DatabaseColumns.SECTION_5 + " integer "
            + ");";

}
