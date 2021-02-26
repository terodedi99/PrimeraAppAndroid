package com.example.androidapp;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductosSQLiteHelper extends SQLiteOpenHelper {

    /* Sentencia SQL para crear la tabla de Productos */
    String sqlCrearTabla = "CREATE TABLE Productos(nombre TEXT, autor TEXT, cantidad TEXT, id INT)";
    public ProductosSQLiteHelper(Context contexto, String nombreBD, SQLiteDatabase.CursorFactory factory, int versionBD) {
        super(contexto, nombreBD, factory, versionBD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            /* Se ejecuta la sentencia SQL para crear la tabla */
            db.execSQL(sqlCrearTabla);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {

            try {
                /*Se elimina la versión anterior de la table*/
                db.execSQL("DROP TABLE IF EXISTS Productos");
                /*Se crea la nueva versión de la table*/
                db.execSQL(sqlCrearTabla);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }