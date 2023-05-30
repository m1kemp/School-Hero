package com.example.school_hero;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Creating table for headmaster
        String qry1 = "create table User(name TEXT, username TEXT, password TEXT, proff TEXT)";
        sqLiteDatabase.execSQL(qry1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void register(String name, String username, String password, String proff) {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("username", username);
        cv.put("password", password);
        cv.put("proff", proff);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("User", null, cv);
        db.close();
    }

    public int login(String username, String password) {

        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from User where username=? and password=?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        return result;

    }

    public String check(String username, String password) {

        String result = "";
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from User where username=? and password=?", str);
        if (c.moveToFirst()) {
           result = c.getString(3);
        }
        return result;
    }
}
