package com.example.incomingcallhandler.db;

import android.app.Application;
import android.content.Context;

import androidx.core.content.ContextCompat;

public class DatabaseRepository {
    private DbHelper dbHelper;
    private static DatabaseRepository instance = null;

    public static DatabaseRepository getInstance(Context application) {
        if (instance == null) {
            instance = new DatabaseRepository(application);
        }
        return instance;
    }

    public DatabaseRepository(Context application) {
        dbHelper = DbHelper.getInstance(application.getApplicationContext());
    }

    public String getInfo(String number) {
        return dbHelper.getInfoByNumber(number);
    }

    public void addNumber(String number, String info) {
        dbHelper.addItem(number, info);
    }
}
