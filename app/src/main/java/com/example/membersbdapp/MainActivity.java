package com.example.membersbdapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.membersbdapp.Utilidades.Utilidades;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ConexionSQLiteHerper mDataBaseHerper;
    RecyclerView recyclerViewMiembros;
    ArrayList<Miembros> listaMiembro;
    ImageView imagen;

    SqliteHerper conn = new SqliteHerper(this, "miembrosdb", null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SqliteHerper conn;
        mDataBaseHerper=new ConexionSQLiteHerper(this,"miembrosdb",null,1);
        listaMiembro = new ArrayList<>();
        recyclerViewMiembros =(RecyclerView) findViewById(R.id.recMiembros);
        recyclerViewMiembros.setLayoutManager(new GridLayoutManager(this,2));

        consultarNuevosMiembroslistamiembros();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,listaMiembro);
        recyclerViewMiembros.setAdapter(adapter);
    }
    private void consultarNuevosMiembroslistamiembros() {
        SQLiteDatabase db = mDataBaseHerper.getReadableDatabase();
        Miembros persona = null;
        Cursor cursor = mDataBaseHerper.getData();
        while (cursor.moveToNext()){
            persona = new Miembros();
            persona.setNombre(cursor.getString(0));
            persona.setCiudad(cursor.getString(1));
            persona.setMatricula(cursor.getString(2));
            persona.setExpresion(cursor.getString(3));
            byte[] blob = cursor.getBlob(4);
            ByteArrayInputStream bais = new ByteArrayInputStream(blob);
            Bitmap bitmap = BitmapFactory.decodeStream(bais);
            persona.setImagen(bitmap);
            listaMiembro.add(persona);
        }
    }

}
