package com.example.casa.booksearch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "books.db";
    public static final String TABLE_NAME = "books_fav";
    public static final String col1 = "ID";
    public static final String col2 = "NAME";
    public static final String col3= "AUTHOR";
    public static final String col4 = "SYNOPSIS";
    public static final String col5 = "IMG";

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +" (" + col1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + col2 + " TEXT, " + col3 + " TEXT, " + col4 + " TEXT, " + col5 + " TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String name,String author,String synopsis) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2,name);
        contentValues.put(col3,author);
        contentValues.put(col4,synopsis);
        long result = db.insert(TABLE_NAME, null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME , null);
        return res;

    }

    public boolean updateData(String id,String name,String author,String synopsis) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1, id);
        contentValues.put(col2, name);
        contentValues.put(col3, author);
        contentValues.put(col4, synopsis);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] { id });
        return true;

    }

    public Integer deleteData (String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});

    }
}
