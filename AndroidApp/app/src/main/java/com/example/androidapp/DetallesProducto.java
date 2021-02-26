package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetallesProducto extends AppCompatActivity {

    private EditText txtNombreP;
    private EditText txtAutorP;
    private EditText txtCantidadP;
    private Button btnGuardarP;
    private Button btnBorrarCampos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);

        //Obtenemos las referencias a los elementos gráficos de la GUI
        txtNombreP = findViewById(R.id.txtNombreP);
        txtAutorP = findViewById(R.id.txtAutorP);
        txtCantidadP = findViewById(R.id.txtCantidadP);
        btnBorrarCampos = findViewById(R.id.btnBorrarCampos);

        //Recoger los datos enviados por la primera actividad y mostrarlos en la GUI
        Bundle bundle = getIntent().getExtras();
        txtNombreP.setText(bundle.getString("nombre"));
        txtAutorP.setText(bundle.getString("autor"));
        txtCantidadP.setText(bundle.getString("cantidad"));

        btnGuardarP = findViewById(R.id.btnGuardarP);
        btnGuardarP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent nuevoProducto = new Intent();
                nuevoProducto.putExtra("nombre", txtNombreP.getText().toString());
                nuevoProducto.putExtra("autor", txtAutorP.getText().toString());
                nuevoProducto.putExtra("cantidad", txtCantidadP.getText().toString());

                setResult(RESULT_OK, nuevoProducto);
                finish();

            }

        });

        btnBorrarCampos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                txtNombreP.setText("");
                txtCantidadP.setText("");
                txtAutorP.setText("");

            }
        });

    }
}
