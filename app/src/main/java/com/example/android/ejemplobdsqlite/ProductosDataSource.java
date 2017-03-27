package com.example.android.ejemplobdsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by Jair Barzola on 23-Jan-17.
 */

public class ProductosDataSource {
    SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;
    private static final String[] allColumns = {
            ProductosDBOpenHelper.COLUMN_ID,
            ProductosDBOpenHelper.COLUMN_NAME,
            ProductosDBOpenHelper.COLUMN_PRECIO
    };

    public ProductosDataSource(Context context) {
        dbHelper = new ProductosDBOpenHelper(context);
    }

    //abrimos la base de datos
    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {

        dbHelper.close();
    }

    public Producto create(Producto producto) {
        ContentValues values = new ContentValues();
        values.put(ProductosDBOpenHelper.COLUMN_NAME, producto.getNombre());
        values.put(ProductosDBOpenHelper.COLUMN_PRECIO, producto.getPrecio());
        //ESTAMOS INSERTANDO ALA TABLA PRODUCTOS
        long insertid = database.insert(ProductosDBOpenHelper.TABLE_PRODUCTS, null, values);
        producto.setId(insertid);
        return producto;

    }

    public List<Producto> findALL() {
        Cursor cursor = database.query(ProductosDBOpenHelper.TABLE_PRODUCTS, allColumns, null, null, null, null, null);
        List<Producto> productos = cursorToList(cursor);
        return productos;
    }

    //para traer todos los datos
    public List<Producto> cursorToList(Cursor cursor) {
        List<Producto> productos = new ArrayList<Producto>();
        //preguntamos si el cursor tiene algo
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Producto producto = new Producto();
                producto.setId(cursor.getLong(cursor.getColumnIndex(ProductosDBOpenHelper.COLUMN_ID)));
                producto.setNombre(cursor.getString(cursor.getColumnIndex(ProductosDBOpenHelper.COLUMN_NAME)));
                producto.setPrecio(cursor.getInt(cursor.getColumnIndex(ProductosDBOpenHelper.COLUMN_PRECIO)));
                productos.add(producto);
            }
        }
        return productos;

    }
    //buscar
    public List<Producto> findFiltered(String selection,String orderBy){
        Cursor cursor = database.query(ProductosDBOpenHelper.TABLE_PRODUCTS,allColumns,selection,null,null,null,orderBy);
        List<Producto> productos = cursorToList(cursor);
        return productos;
    }

}
