package com.example.membersbdapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.membersbdapp.Utilidades.Utilidades;

public class Eliminar extends AppCompatActivity {
    ImageView imgFoto;
    EditText nombre, ciudad, matricula, expresion;
    Button btnEliminar, btnConsultar;

    SqliteHerper conn = new SqliteHerper(this, "miembrosdb", null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        imgFoto = (ImageView) findViewById(R.id.ivFoto);
        btnEliminar = (Button) findViewById(R.id.btEliminar);
        btnConsultar = (Button) findViewById(R.id.btConsultar);
        nombre = (EditText) findViewById(R.id.etNombre);
        ciudad = (EditText) findViewById(R.id.etCiudad);
        matricula = (EditText) findViewById(R.id.etMatricula);
        expresion = (EditText) findViewById(R.id.etExpresion);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Consultademiembros();
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarMiembro();
            }
        });
    }

    private void eliminarMiembro() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String [] parametro = {nombre.getText().toString()};
        db.delete(Utilidades.TABLA_MIEMBRO, Utilidades.CAMPO_NOMBRE+"=?", parametro);
        Toast.makeText(getApplicationContext(), "Registro de Miembro Eliminado", Toast.LENGTH_SHORT).show();
        db.close();

    }

    private void Consultademiembros() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String [] parametro = {nombre.getText().toString()};
        try {
            Cursor cursor = db.rawQuery(" SELECT " + Utilidades.CAMPO_CIUDAD + " , " + Utilidades.CAMPO_MATRICULA + " , " + Utilidades.CAMPO_EXPRESION +
                    " FROM " + Utilidades.TABLA_MIEMBRO + " WHERE " + Utilidades.CAMPO_NOMBRE + " =? ", parametro);
            cursor.moveToFirst();
            ciudad.setText(cursor.getString(0));
            matricula.setText(cursor.getString(1));
            expresion.setText(cursor.getString(2));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Ese miembro no existe", Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    private void limpiar(){
        nombre.setText("");
        ciudad.setText("");
        matricula.setText("");
        expresion.setText("");
    }
}
