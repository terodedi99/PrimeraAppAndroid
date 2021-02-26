package com.example.androidapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ConectorBD {

    static final String nombre_BD = "ListaCompraLocal";
    private ProductosSQLiteHelper dbHelper;
    private SQLiteDatabase db;

    /* Constructor de la BD*/
    public ConectorBD (Context ctx) {
        dbHelper = new ProductosSQLiteHelper(ctx, nombre_BD, null, 1);
    }

    /* Abrir la conexión con la base de datos */
    public ConectorBD abrir() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    /* Cerrar la conexión con la base de datos */
    public void cerrar() {
        if (db != null) db.close();
    }

    /* Insertar un producto en la BD */
    public void insertarProducto (String nombre, String autor, String cantidad, int id) {
        String consultaSQL = "INSERT INTO productos VALUES('" + nombre + "','" + autor + "','" + cantidad +"'," + id + ")";
        db.execSQL(consultaSQL);
    }

    /* Devolver todos los productos */
    public Cursor listarProductos() {
        return db.rawQuery("SELECT * FROM Productos", null);
    }

    /* Modificar todos los productos */
    public void modificarProducto(String nombre, String autor, String cantidad, int id) {
        String consultaSQL = "UPDATE Productos SET nombre = '" + nombre + "', autor =  '" + autor + "', cantidad = '" + cantidad + "' WHERE id = " + id;
        db.execSQL(consultaSQL);
    }

    /* Eliminar todos los productos */
    public void eliminarProducto(int id) {
        String consultaSQL = "DELETE FROM Productos WHERE id = " + id;
        db.execSQL(consultaSQL);
    }

    public int getId(){
        int id = 0;
        String consultaSQL = "SELECT MAX(id) FROM Productos";
        Cursor c = db.rawQuery(consultaSQL, null);
        if(c.moveToFirst())
            id = c.getInt(0)+1;
        return id;
    }
}

