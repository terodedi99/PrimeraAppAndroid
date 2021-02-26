package com.example.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AnadirProducto extends AppCompatActivity {

    private EditText txtNombreP;
    private EditText txtAutorP;
    private EditText txtCantidadP;
    private Button btnGuardarP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_producto);

        //Obtenemos las referencias a los elementos gráficos de la GUI
        txtNombreP = findViewById(R.id.txtNombreP);
        txtAutorP = findViewById(R.id.txtAutorP);
        txtCantidadP = findViewById(R.id.txtCantidadP);
        btnGuardarP = findViewById(R.id.btnGuardarP);

        btnGuardarP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if ((txtNombreP.getText().toString().equals("") && (txtCantidadP.getText().toString().equals("")))) {
                    Intent nuevoProducto = new Intent();
                    nuevoProducto.putExtra("nombre", "Desconocido");
                    nuevoProducto.putExtra("cantidad", "1");
                    setResult(RESULT_OK, nuevoProducto);
                    finish();

                } else if (txtNombreP.getText().toString().equals("")) {
                    Intent nuevoProducto = new Intent();
                    nuevoProducto.putExtra("nombre", "Desconocido");
                    nuevoProducto.putExtra("cantidad", txtCantidadP.getText().toString());
                    setResult(RESULT_OK, nuevoProducto);
                    finish();

                } else if (txtCantidadP.getText().toString().equals("")) {
                    Intent nuevoProducto = new Intent();
                    nuevoProducto.putExtra("nombre", txtNombreP.getText().toString());
                    nuevoProducto.putExtra("cantidad", "1");
                    setResult(RESULT_OK, nuevoProducto);
                    finish();

                } else {
                    Intent nuevoProducto = new Intent();
                    nuevoProducto.putExtra("nombre", txtNombreP.getText().toString());
                    nuevoProducto.putExtra("cantidad", txtCantidadP.getText().toString());
                    setResult(RESULT_OK, nuevoProducto);
                    finish();

                }
            }

        });

    }
}