package com.example.android.ejemplobdsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.R.attr.id;

/**
 * Created by Jair Barzola on 23-Jan-17.
 */

public class ProductosDBOpenHelper extends SQLiteOpenHelper {

    //constructor
   public ProductosDBOpenHelper(Context context){
       super(context,DATABASE_NAME,null,DATABASE_VERSION);
   }
    //creamos el LOGTAG porque nos pide mandar mensajes a la consola
    private static  final String LOGTAG= "LOGTAG";
    //por convencion se pone el db
    private static final String DATABASE_NAME="productos.db";
    private static final int DATABASE_VERSION=1;
    //Creamos la tabla
    public static  final  String TABLE_PRODUCTS= "productos";
    public static final String COLUMN_ID="productoID";
    public static  final String COLUMN_NAME="nombre";
    public static final String COLUMN_PRECIO="precio";
    //CONSTANTE PARA CREAR LA BASE DE DATOS
    public static final String TABLE_CREATE="CREATE TABLE "+TABLE_PRODUCTS+" ("+
            COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COLUMN_NAME+" TEXT, "+
            COLUMN_PRECIO+ " NUMERIC "+
            ")";
    // onCreate para crear la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        Log.i(LOGTAG,"Table has been create");
    }
    // onUpgrade para verificar la version de la bd
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+ TABLE_PRODUCTS);
        onCreate(db);
    }
}
