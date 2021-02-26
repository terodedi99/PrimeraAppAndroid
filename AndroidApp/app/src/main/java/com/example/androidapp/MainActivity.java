package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNombre = findViewById(R.id.txtNombre);

    }

    public void oyente_btnVamos(View view) {
        Log.d("Debug","Se pulsó el botón Vamos");

        //Lanzar la lista de productos
        Intent i = new Intent(this, ListaCompra.class );
        i.putExtra("usuario", txtNombre.getText().toString());
        startActivity(i);

    }



}
