package com.example.phonenote.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.phonenote.category.DataBase;

public class ControlSQL {

    public static void delete(DataBase dbHelper, int usedId){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.execSQL("delete from Note where id = '"+usedId+"' ;");
    }

    public static void store(DataBase dbHelper, String title, String markdown){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",title);
        values.put("markdown",markdown);
        db.insert("Note",null,values);
    }

    public static void store(DataBase dbHelper, String title, String markdown, int usedId){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",title);
        values.put("markdown",markdown);
        db.execSQL("DELETE FROM Note WHERE id = "+usedId+" ;");
        db.insert("Note",null,values);
    }
}
