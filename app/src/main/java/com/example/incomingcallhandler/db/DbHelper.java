package com.example.incomingcallhandler.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {
    private static DbHelper instance = null;
    public final static String DATABASE_NAME = "app_db.db";
    public final static String TABLE_NAME = "numbers";

    public static DbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DbHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE table "
                + TABLE_NAME
                + " ( number text primary key,"
                + "info text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP table if EXISTS " + TABLE_NAME);
    }

    public boolean addItem(String number, String info) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("number", number);
        contentValues.put("info", info);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public String getInfoByNumber(String number) {
        String info = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * from " +
                TABLE_NAME + " where number='" + number + "'", null);
        if (res.moveToFirst()) {
            info = res.getString(res.getColumnIndex("info"));
        }
        return info;
    }


}
