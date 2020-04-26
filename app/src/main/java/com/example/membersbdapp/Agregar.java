package com.example.membersbdapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.BitmapCompat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.membersbdapp.Utilidades.Utilidades;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

public class Agregar extends AppCompatActivity {
    ConexionSQLiteHerper mDataBaseHerper;

    EditText   nombre, ciudad, matricula, expresion;
    ImageView imagen;
    Button btnGuardar, btnConsultar;
    SqliteHerper conn = new SqliteHerper(this, "miembrosdb", null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        mDataBaseHerper=new ConexionSQLiteHerper(this,"miembrosdb",null,1);
        imagen = (ImageView) findViewById(R.id.ivFoto);
        btnGuardar = (Button) findViewById(R.id.btGuardar);
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
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Newname = nombre.getText().toString();
                String NewCiudad = ciudad.getText().toString();
                String NewMatricula = matricula.getText().toString();
                String NewExpresion = expresion.getText().toString();
                byte[] NewEntryIma = ImageViewtoByte(imagen);
                AddData(Newname, NewCiudad, NewMatricula, NewExpresion, NewEntryIma);
            }
        });
    }
    private byte[] ImageViewtoByte(ImageView image) {
        Bitmap bimapp = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bimapp.compress(Bitmap.CompressFormat.PNG,100, stream);
        byte[] bytesArray = stream.toByteArray();
        return  bytesArray;
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
    public void onClick(View view)
    {
        cargarimagen();
    }
    private void cargarimagen(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(Intent.createChooser(intent,"Selecionar aplicacion"),20);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK)
        {
            Uri path = data.getData();
            imagen.setImageURI(path);
        }
    }
    private void AddData(String nombre, String ciudad, String matricula, String expresion, byte[] img){
        boolean insertData= mDataBaseHerper.addData(nombre,ciudad,matricula,expresion, img);
    }
    private void limpiar(){
        nombre.setText("");
        ciudad.setText("");
        matricula.setText("");
        expresion.setText("");
    }

}
