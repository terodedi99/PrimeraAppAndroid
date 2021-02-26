package com.example.androidapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class ListaCompra extends AppCompatActivity implements OnItemSelectedListener {

    private ArrayList<Producto> productos;
    private RecyclerView lstProductos;
    private AdaptadorLista adaptador;
    private TextView lblSeleccionado;
    private int productoSeleccionado = -1;
    private FloatingActionButton fabAnadirProducto;
    private ConectorBD conectorBD;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compra);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        conectorBD = new ConectorBD(this);

        //Obtener una referencia a la lista gráfica
        lstProductos = findViewById(R.id.lstProductos);
        //Crear la lista de contactos
        productos = new ArrayList<Producto>();
        // Método que rellena con los datos que había anteriormente
        rellenarDatos();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        lstProductos.setLayoutManager(mLayoutManager);
        adaptador = new AdaptadorLista(productos);
        adaptador.setItemSelectedListener(this);
        lstProductos.setAdapter(adaptador);

        lstProductos.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        fabAnadirProducto = findViewById(R.id.fabAnadirProducto);

        //Obtener una referencia a la etiqueta en la que se mostrará el ítem
        //seleccionado
        lblSeleccionado = findViewById(R.id.lblSeleccionado);

        //Recoger los datos enviados por la primera actividad
        Bundle bundle=getIntent().getExtras();
        Log.d("Debug","Valor recibido:" +bundle.getString("usuario"));
        Toast notificacion;
        this.usuario = bundle.getString("usuario");
        notificacion = Toast.makeText(this, "Bienvenid@ " + bundle.getString("usuario") + "!", Toast.LENGTH_LONG);
        notificacion.show();

        fabAnadirProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Debug", "Se pulsó el botón Añadir producto");
                Intent i = new Intent(getApplicationContext(), AnadirProducto.class);
                startActivityForResult(i, 5678);

            }
        });

    }

    public void rellenarDatos() {
       conectorBD.abrir();
       Cursor c = conectorBD.listarProductos();
        if (c.moveToFirst()) {
            do {
                productos.add(new Producto(c.getString(0), c.getString(1), c.getString(2), c.getInt(3)));
            } while(c.moveToNext());
        }

        c.close();
        conectorBD.cerrar();
    }

    //Este método se ejecutará cuando se pulse el botón ”Volver"
    public void oyente_btnVolver (View view){
        Log.d("Debug","Se pulsó el botón Volver");
        finish();
    }

    public void oyente_btnPapelera (View view) {
        Log.d("Debug", "Se pulsó el botón Papelera");
        if (productos.isEmpty()) {

            lblSeleccionado.setText("Producto seleccionado: ");
            //Se muestra una ventana de diálogo
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alerta");
            builder.setMessage("No se puede borrar ningún producto si la lista está vacía.");
            builder.setPositiveButton("De acuerdo", null);
            builder.create();
            builder.show();

        } else {

            if (productoSeleccionado == -1) {
                lblSeleccionado.setText("Producto seleccionado: ");

            } else {
                conectorBD.abrir();
                conectorBD.eliminarProducto(productos.get(productoSeleccionado).getId());
                productos.remove(productoSeleccionado);
                adaptador.notifyDataSetChanged();
                lblSeleccionado.setText("Producto seleccionado: ");
                productoSeleccionado = -1;
            }
        }
    }

    @Override
    public void onProductoSeleccionado(int posicion) {
            lblSeleccionado.setText("Producto seleccionado: " + productos.get(posicion).getNombre());
            productoSeleccionado = posicion;

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.acercaDe:
                Log.d("LogCat","Pulsó la opción de menú Acerca de...");

                //Se muestra una ventana de diálogo
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(" Acerca de...");
                builder.setMessage(" Aplicación creada por Teresa Rodríguez de Dios" +
                        "\n Asignatura: Interacción Persona - Ordenador II " +
                        "\n Curso: 2019/2020 " +
                        "\n Escuela Superior de Informática");
                builder.setPositiveButton("Ok!",null);
                builder.create();
                builder.show();
                break;

            case R.id.instrucciones:
                Log.d("LogCat","Pulsó la opción de menú Acerca de...");

                //Se muestra una ventana de diálogo
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle(" Información de uso");
                builder2.setMessage(" - Si especificas tu nombre al inicio, aparecerás como autor de los productos que añadas. " +
                        "\n - Para añadir un producto debes seleccionar el botón 'Añadir'. " +
                        "\n - Si no especificas nombre de producto, su nombre será 'Desconocido'. " +
                        "\n - Si no especificas cantidad de producto, su cantidad será '1'. " +
                        "\n - Si pulsas sobre los productos podrás editarlos. " +
                        "\n - Para borrar un producto debes seleccionarlo y luego pulsar el botón 'Papelera'.");
                builder2.setPositiveButton("Ok!",null);
                builder2.create();
                builder2.show();
                break;

        }

        return true;
    }

    @Override
    public void onMenuContextualProducto(int posicion, MenuItem menu) {
        switch (menu.getItemId()) {
            case R.id.editar:
                Log.d("LogCat","Pulsó la opción del menú contextual Editar");
                Intent i = new Intent(this, DetallesProducto.class);
                i.putExtra("nombre", productos.get(posicion).getNombre());
                i.putExtra("autor", productos.get(posicion).getAutor());
                i.putExtra("cantidad", productos.get(posicion).getCantidad());
                productoSeleccionado = posicion;
                startActivityForResult(i, 1234);
                break;

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();

        // Modificar producto
        if (requestCode == 1234){
            if (resultCode == RESULT_OK){

                Producto productoModificado = new Producto(b.getString("nombre"),b.getString("autor"), b.getString("cantidad"), this.productos.get(productoSeleccionado).getId());
                conectorBD.abrir();
                conectorBD.modificarProducto(b.getString("nombre"), b.getString("autor"), b.getString("cantidad"), this.productos.get(productoSeleccionado).getId());
                productos.set(productoSeleccionado, productoModificado);
                adaptador.notifyDataSetChanged();
            }

        // Añadir producto
        } else {
            if (resultCode == RESULT_OK){

                conectorBD.abrir();
                Producto productoAnadido = new Producto(b.getString("nombre"),this.usuario, b.getString("cantidad"), conectorBD.getId());
                conectorBD.insertarProducto(b.getString("nombre"), usuario, b.getString("cantidad"), conectorBD.getId());
                productos.add(productoAnadido);
                adaptador.notifyDataSetChanged();
            }
        }
    }
}
