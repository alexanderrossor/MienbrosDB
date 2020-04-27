package com.example.membersbdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.membersbdapp.Utilidades.Utilidades;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class Ajuste extends AppCompatActivity {
    ImageView imgFoto;
    EditText nombre, ciudad, matricula, expresion;
    Button btnActualizar, btnConsultar;

    SqliteHerper conn = new SqliteHerper(this, "miembrosdb", null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuste);
        imgFoto = (ImageView) findViewById(R.id.ivFoto);
        btnActualizar = (Button) findViewById(R.id.btActualizar);
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
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Actualizar();
            }
        });
    }
    private void Actualizar() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String [] parametro = {matricula.getText().toString()};
        byte[] NewEntryIma = ImageViewtoByte(imgFoto);
        ContentValues value = new ContentValues();
        value.put(Utilidades.CAMPO_NOMBRE, nombre.getText().toString());
        value.put(Utilidades.CAMPO_CIUDAD, ciudad.getText().toString());
        value.put(Utilidades.CAMPO_MATRICULA, matricula.getText().toString());
        value.put(Utilidades.CAMPO_EXPRESION, expresion.getText().toString());
        value.put(Utilidades.CAMPO_FOTO,NewEntryIma);
        db.update(Utilidades.TABLA_MIEMBRO, value, Utilidades.CAMPO_MATRICULA+"=?",parametro);
        Toast.makeText(this, "Ya se actualizo", Toast.LENGTH_SHORT).show();
        limpiar();
        db.close();
    }

    private byte[] ImageViewtoByte(ImageView imgFoto) {
        Bitmap bimapp = ((BitmapDrawable) imgFoto.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bimapp.compress(Bitmap.CompressFormat.PNG,100, stream);
        byte[] bytesArray = stream.toByteArray();
        return  bytesArray;
    }

    private void Consultademiembros() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String [] parametro = {nombre.getText().toString()};
        try {
            Cursor cursor = db.rawQuery(" SELECT " + Utilidades.CAMPO_CIUDAD + " , " + Utilidades.CAMPO_MATRICULA + " , " + Utilidades.CAMPO_EXPRESION +" , " + Utilidades.CAMPO_FOTO +
                    " FROM " + Utilidades.TABLA_MIEMBRO + " WHERE " + Utilidades.CAMPO_NOMBRE + " =? ", parametro);
            cursor.moveToFirst();
            ciudad.setText(cursor.getString(0));
            matricula.setText(cursor.getString(1));
            expresion.setText(cursor.getString(2));
            byte[] blob = cursor.getBlob(3);
            ByteArrayInputStream bais = new ByteArrayInputStream(blob);
            Bitmap bitmap = BitmapFactory.decodeStream(bais);
            imgFoto.setImageBitmap(bitmap);

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
        imgFoto.setImageBitmap(null);
    }
}
