package com.example.android.ejemplobdsqlite;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import static android.R.id.edit;

public class MainActivity extends AppCompatActivity {
    private static  final String LOGTAG="Data has been create";
    Button todos,menos,borrar,agregar;
    EditText nombre,precio,delete;
    ListView listView;
    ProductosDataSource dataSource;
    List<Producto> productos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todos = (Button) findViewById(R.id.traerTodos);
        menos = (Button) findViewById(R.id.menos);
        delete= (EditText) findViewById(R.id.borrarEdit);
        agregar = (Button) findViewById(R.id.agregar);
        nombre = (EditText) findViewById(R.id.nombre);
        precio= (EditText)findViewById(R.id.precio);
        listView = (ListView) findViewById(R.id.lista);
        borrar = (Button)findViewById(R.id.borrar);



        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = delete.getText().toString();
                if(nombre.isEmpty()){
                    Toast.makeText(getApplication(),"Digita el nombre a borrar",Toast.LENGTH_SHORT).show();
                }else{
                ProductosDBOpenHelper dbHelper = new ProductosDBOpenHelper(getApplication());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String where= "nombre" + "=?";
                String[] whereArgs = new String[]{delete.getText().toString()};

                db.delete("productos",where,whereArgs);
                db.execSQL("DROP TABLE IF EXISTS "+delete.getText().toString());
                db.close();
                Toast.makeText(getApplication(),"Dato eliminado corrextamente",Toast.LENGTH_SHORT).show();
                actualizar();
                refreshDisplay();}
            }
        });


        todos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
                refreshDisplay();
            }
        });
        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSource = new ProductosDataSource(getApplication());
                dataSource.open();
                productos = dataSource.findFiltered("precio < 150","precio ASC");
                dataSource.close();
                refreshDisplay();
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name= nombre.getText().toString();
                String p = precio.getText().toString();
                int price;
                if(name.isEmpty()||p.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingresa los datos para agregar",Toast.LENGTH_SHORT).show();
                }else{
                    price = Integer.parseInt(p);
                    Producto producto = new Producto();
                    producto.setNombre(name);
                    producto.setPrecio(price);
                    dataSource.open();
                    dataSource.create(producto);
                    Toast.makeText(getApplicationContext(),"Registro satisfactorio",Toast.LENGTH_SHORT).show();
                    actualizar();
                    refreshDisplay();
                }


            }
        });

        actualizar();
        refreshDisplay();



    }

    private void refreshDisplay() {
        ArrayAdapter<Producto> adapter = new ArrayAdapter<Producto>(this,android.R.layout.simple_list_item_1,productos);
        listView.setAdapter(adapter);
    }
    void actualizar(){
        dataSource = new ProductosDataSource(getApplication());
        dataSource.open();
        productos = dataSource.findALL();
        //verficamos si esta hecho o no
        if(productos.size()==0){
            createData();
            productos = dataSource.findALL();
        }
        dataSource.close();
    }


    private void createData(){
        Producto producto = new Producto();
        producto.setNombre("Playera");
        producto.setPrecio(80);
        dataSource.create(producto);
        Log.i(LOGTAG,"ID"+ producto.id);

        producto = new Producto();
        producto.setNombre("Pantalones");
        producto.setPrecio(100);
        dataSource.create(producto);
        Log.i(LOGTAG,"ID"+ producto.id);

        producto = new Producto();
        producto.setNombre("Pelotas");
        producto.setPrecio(300);
        dataSource.create(producto);
        Log.i(LOGTAG,"ID"+ producto.id);

        producto = new Producto();
        producto.setNombre("Camisas");
        producto.setPrecio(168);
        dataSource.create(producto);
        Log.i(LOGTAG,"ID"+ producto.id);

        producto = new Producto();
        producto.setNombre("Llantas");
        producto.setPrecio(78);
        dataSource.create(producto);
        Log.i(LOGTAG,"ID"+ producto.id);

        producto = new Producto();
        producto.setNombre("Mouses");
        producto.setPrecio(178);
        dataSource.create(producto);
        Log.i(LOGTAG,"ID"+ producto.id);
    }

}
