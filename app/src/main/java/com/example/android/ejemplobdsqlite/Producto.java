package com.example.android.ejemplobdsqlite;

import android.icu.text.NumberFormat;

/**
 * Created by Jair Barzola on 23-Jan-17.
 */

public class Producto {
    public long id;
    public String nombre;
    public int precio;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    // metodo para concatenar los datos en el listview
    public String toString(){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return nombre +"\n("+nf.format(precio)+")";
    }
}
