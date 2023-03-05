package com.example.lasttp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDataBase extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "Personne";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "Name";
    private static final int DATABASE_VERSION = 2;

    public MyDataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void inserer(String n) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, n);
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    public ArrayList<String> affichage(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> Ai = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT*FROM TABLE_NAME",null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String nom = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                Ai.add("ID : " + id + ", Name : " + nom);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Ai;
    }
}
