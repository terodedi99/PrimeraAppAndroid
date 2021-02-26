package com.example.androidapp;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdaptadorLista extends RecyclerView.Adapter<AdaptadorLista.ViewHolder> {

    private ArrayList<Producto> productos;
    private OnItemSelectedListener itemSelectedListener;

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, PopupMenu.OnMenuItemClickListener {

        private TextView lblNombre;
        private TextView lblAutor;
        private TextView lblCantidad;

            ViewHolder(View view) {
                super(view);
                lblNombre = view.findViewById(R.id.lblNombre);
                lblAutor = view.findViewById(R.id.lblAutor);
                lblCantidad = view.findViewById(R.id.lblCantidad);
                view.setOnClickListener(this); // Asociar el oyente
                view.setOnCreateContextMenuListener(this);
            }

            @Override
            public void onClick(View v) {
                int posicion = getAdapterPosition();
                if (itemSelectedListener != null) {
                    itemSelectedListener.onProductoSeleccionado(posicion);
                }
            }

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                PopupMenu popup = new PopupMenu(v.getContext(), v);
                popup.getMenuInflater().inflate(R.menu.menu_contextual, popup.getMenu());
                popup.setOnMenuItemClickListener(this);
                popup.show();
            }

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (itemSelectedListener != null) {
                    itemSelectedListener.onMenuContextualProducto(getAdapterPosition(), menuItem);
                }
                return true;
            }
        }

        public AdaptadorLista(ArrayList<Producto> productos) {
            this.productos = productos;
        }

        @Override
        public AdaptadorLista.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(AdaptadorLista.ViewHolder holder, int position) {

            holder.lblNombre.setText(productos.get(position).getNombre());
            holder.lblAutor.setText(productos.get(position).getAutor());
            holder.lblCantidad.setText(productos.get(position).getCantidad());

        }

        @Override
        public int getItemCount() {
            return productos.size();
        }

        public void setItemSelectedListener(OnItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
        }
    }
