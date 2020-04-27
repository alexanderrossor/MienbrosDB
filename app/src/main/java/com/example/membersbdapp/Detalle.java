package com.example.membersbdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class Detalle extends AppCompatActivity {
    private Miembros Item;
    ImageView imgFoto;
    private TextView txNombre, txCiudad, txMatricula, txExpresion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        imgFoto = (ImageView) findViewById(R.id.imgFoto);
        txNombre = (TextView) findViewById(R.id.txNombre);
        txCiudad = (TextView) findViewById(R.id.txciudad);
        txMatricula = (TextView) findViewById(R.id.txMatricula);
        txExpresion = (TextView) findViewById(R.id.txExpresion);



    }
}
