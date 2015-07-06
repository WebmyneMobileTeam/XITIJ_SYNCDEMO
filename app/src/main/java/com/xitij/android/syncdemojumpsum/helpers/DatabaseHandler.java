package com.xitij.android.syncdemojumpsum.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.xitij.android.syncdemojumpsum.model.User;

import java.util.ArrayList;

/**
 * Created by jaydeeprana on 01-07-2015.
 */
public class DatabaseHandler extends SQLiteAssetHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME  = "jumpsum.db";

    // Table Names
    public static final String TABLE_TODO = "User";

    // Common column names
    public static final String KEY_ID = "_id";

    // Routes Table - column names
    public static final String NAME = "name";
    public static final String LAST_NAME = "last_name";

    // Constructor
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ArrayList<User> getAllToDos() {
        SQLiteDatabase db = null;
        try {
            db = this.getReadableDatabase();
            Cursor c = db.rawQuery("select * from " + TABLE_TODO, null);
            ArrayList<User> arrToDos = new ArrayList<User>();
            if (c.moveToFirst()) {
                do {
                    User rm = new User();
                    rm.first_name = c.getString(c.getColumnIndex(NAME));
                    rm.last_name = (c.getString(c.getColumnIndex(LAST_NAME)));
                    arrToDos.add(rm);

                } while (c.moveToNext());
            }
            db.close();
            return arrToDos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            db.close();
        }
    }
}
