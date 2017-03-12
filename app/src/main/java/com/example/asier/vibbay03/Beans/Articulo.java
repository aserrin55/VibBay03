package com.example.asier.vibbay03.Beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asier on 07/03/2017.
 */

public class Articulo {

    @SerializedName("id")
    private int id;
    @SerializedName("titulo")
    private String titulo;
    @SerializedName("email")
    private String email;
    @SerializedName("estado")
    private boolean estado;
    @SerializedName("imagen")
    private String imagen;
    @SerializedName("precio")
    private int precio;

    public Articulo(int id, String titulo, String email, boolean estado, String imagen, int precio) {
        this.id = id;
        this.titulo = titulo;
        this.email = email;
        this.estado = estado;
        this.imagen = imagen;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
