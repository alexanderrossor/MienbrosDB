package com.example.membersbdapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Principal extends AppCompatActivity {
    private CardView primero, segundo, tercero, ajuste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        primero = (CardView) findViewById(R.id.carAgregar);
        segundo = (CardView) findViewById(R.id.carMiembros);
        tercero = (CardView) findViewById(R.id.carEliminar);
        ajuste = (CardView) findViewById(R.id.carAjuste);

        segundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(Principal.this, MainActivity.class);
                startActivity(ii);
            }
        });
        primero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(Principal.this, Agregar.class);
                startActivity(ii);
            }
        });
        tercero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(Principal.this, Eliminar.class);
                startActivity(ii);
            }
        });
        ajuste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(Principal.this, Ajuste.class);
                startActivity(ii);
            }
        });
    }
}
