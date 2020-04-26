package com.example.membersbdapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.membersbdapp.Utilidades.Utilidades;

public class SqliteHerper  extends SQLiteOpenHelper {



    public SqliteHerper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MIS_MIEMBROS(FOTO BLOB, NOMBRE TEXT, CIUDAD TEXT, MATRICULA TEXT, EXPRESION TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS miembro");
        onCreate(db);

    }
}
