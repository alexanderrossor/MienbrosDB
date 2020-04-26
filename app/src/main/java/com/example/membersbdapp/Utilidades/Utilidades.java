package com.example.membersbdapp.Utilidades;

import android.graphics.Bitmap;

public class Utilidades {

    //constante campos tablas miembros
    public static final String TABLA_MIEMBRO="miembros_t";
    public static final String CAMPO_FOTO="foto";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_CIUDAD="ciudad";
    public static final String CAMPO_MATRICULA="matricula";
    public static final String CAMPO_EXPRESION="expresion";

    public static final String Crear_Tabla_Miembro = "CREATE TABLE " +
            ""+TABLA_MIEMBRO+" ("+CAMPO_FOTO+" BLOB,"+CAMPO_NOMBRE+" TEXT, "+CAMPO_CIUDAD+" TEXT , "+CAMPO_MATRICULA+" TEXT, "+CAMPO_EXPRESION+" TEXT )";
}
