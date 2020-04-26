package com.example.membersbdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Detalle extends AppCompatActivity {
    private Miembros Item;
    private ImageView imgFoto;
    private TextView txNombre, txCiudad, txMatricula, txExpresion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Item  = (Miembros) getIntent().getSerializableExtra("objetoData");

        imgFoto = (ImageView) findViewById(R.id.imgFoto);
        txNombre = (TextView) findViewById(R.id.txNombre);
        txCiudad = (TextView) findViewById(R.id.txciudad);
        txMatricula = (TextView) findViewById(R.id.txMatricula);
        txExpresion = (TextView) findViewById(R.id.txExpresion);


        txNombre.setText(Item.getNombre());
        txCiudad.setText(Item.getCiudad());
        txMatricula.setText(Item.getMatricula());
        txExpresion.setText(Item.getExpresion());
    }
}
