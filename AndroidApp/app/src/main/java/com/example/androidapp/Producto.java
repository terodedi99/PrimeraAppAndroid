package com.example.androidapp;

class Producto {

    private String nombre;
    private String autor;
    private String cantidad;
    private int id;

    public Producto(String nombre, String autor, String cantidad, int id) {
        this.nombre = nombre;
        this.autor = autor;
        this.cantidad = cantidad;
        this.id = id; // Clave de la tabla
    }

    public static void set(int productoSeleccionado, Producto productoModificado) {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}