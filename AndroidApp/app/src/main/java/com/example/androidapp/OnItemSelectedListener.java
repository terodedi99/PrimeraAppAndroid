package com.example.androidapp;

import android.view.MenuItem;

public interface OnItemSelectedListener {
    void onProductoSeleccionado(int posicion);
    void onMenuContextualProducto(int posicion, MenuItem menu);
}
