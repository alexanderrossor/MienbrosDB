package com.example.membersbdapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class ConexionSQLiteHerper extends SQLiteOpenHelper {
    public static final String DataBase_Name = "miembrosdb";
    public static final String Table_name = "miembros_t";

    public ConexionSQLiteHerper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DataBase_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + Table_name + "(NOMBRE TEXT, CIUDAD TEXT, MATRICULA TEXT, EXPRESION TEXT, FOTO BLOB )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_name);
        onCreate(db);

    }

    public boolean addData(String nombre, String ciudad, String matricula, String expresion, byte[] img) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOMBRE", nombre);
        contentValues.put("CIUDAD", ciudad);
        contentValues.put("MATRICULA", matricula);
        contentValues.put("EXPRESION", expresion);
        contentValues.put("FOTO", img);
        long result = db.insert(Table_name, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + Table_name;
        Cursor data = db.rawQuery(query, null);
        return data;
    }



}
