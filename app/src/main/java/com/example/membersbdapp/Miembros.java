package com.example.membersbdapp;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Miembros implements Serializable {
    private String nombre;
    private String ciudad;
    private String matricula;
    private String expresion;
    private Bitmap imagen;


    public Miembros(Bitmap imagen,String nombre, String ciudad, String matricula, String expresion) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.matricula = matricula;
        this.expresion = expresion;
        this.imagen = imagen;
    }

    public Miembros() {

    }
    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }
}
