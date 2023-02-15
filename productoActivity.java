package com.example.realtimework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.realtimework.Modelo.Productos;
import com.example.realtimework.Modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class productoActivity extends AppCompatActivity {
private Button btnGuardar,btnActualizar,btnBorrar,btnBuscar;
private EditText etCodigo,etNombreProducto,etStock,etPrecioCosto,etPrecioVenta;
DatabaseReference mDatabase;
Productos producto;
String codigoProducto;
String nombreProducto;
Double Stock;
Double precioCosto;
Double precioVenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        iniciarControles();
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerValores();

                nuevoProducto(producto);
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerValores();
                actualizarProducto();
            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=etCodigo.getText()
                        .toString();
                buscarProducto(code);
            }
        });
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=etCodigo.getText().toString();
                borrarProducto(code);
            }
        });
    }
    //Conectamos el archivo xml con nuestro activity
    private void iniciarControles(){
        btnGuardar=findViewById(R.id.btnGuardar);
        btnActualizar=findViewById(R.id.btnActualizar);
        btnBorrar=findViewById(R.id.btnBorrar);
        btnBuscar=findViewById(R.id.btnBuscar);
        etCodigo=findViewById(R.id.etcodigoProducto);
        etNombreProducto=findViewById(R.id.etnombreProducto);
        etStock=findViewById(R.id.etStockProducto);
        etPrecioCosto=findViewById(R.id.etPrecioCosto);
        etPrecioVenta=findViewById(R.id.etPrecioVenta);
    }
    //Obtenemos los valores que se ingresen en los campos de texto
    private Productos obtenerValores(){
    codigoProducto=etCodigo.getText().toString();
    nombreProducto=etNombreProducto.getText().toString();
    Stock=Double.parseDouble(etStock.getText().toString());
    precioCosto=Double.parseDouble(etPrecioCosto.getText().toString());
    precioVenta=Double.parseDouble(etPrecioVenta.getText().toString());
    producto=new Productos(codigoProducto,nombreProducto,Stock,precioCosto,precioVenta);
    return producto;
    }
    //En este metodo creamos un nuevo producto y lo almacenamos en la base de datos
    private void nuevoProducto(Productos producto){
        //Toast.makeText(getApplicationContext(),"prod: "+producto.getCodigoProducto(), Toast.LENGTH_LONG).show();
        String codetoPush=producto.getCodigoProducto();
        mDatabase=FirebaseDatabase.getInstance().getReference().child("Productos");
        HashMap<String, String> result = new HashMap<>();
        //result.put("id", usuario.getIdUsuario().toString());
        result.put("nombreProducto",producto.getNombreProducto().toString());
       // result.put("codigoProducto", producto.getCodigoProducto().toString());
        result.put("stockProducto",producto.getStock().toString());
        result.put("precioCosto", producto.getPrecioCosto().toString());
        result.put("precioVenta", producto.getPrecioVenta().toString());

        mDatabase.child(codetoPush).setValue(result);
    }
    //Este metodo nos permite actualizar un producto
    private void actualizarProducto(){
        mDatabase=FirebaseDatabase.getInstance().getReference();
        String codetoPush=producto.getCodigoProducto();
        mDatabase=FirebaseDatabase.getInstance().getReference().child("Productos");
        HashMap<String, Object> result = new HashMap<>();
        //result.put("id", usuario.getIdUsuario().toString());
        result.put("nombreProducto",producto.getNombreProducto().toString());
        // result.put("codigoProducto", producto.getCodigoProducto().toString());
        result.put("stockProducto",producto.getStock().toString());
        result.put("precioCosto", producto.getPrecioCosto().toString());
        result.put("precioVenta", producto.getPrecioVenta().toString());

        mDatabase.child(codetoPush).updateChildren(result);
    }
    //Este metodo recibe como parametro un codigo de producto y nos muestra los datos asociados a dicho codigo
    private void buscarProducto(String code){
        mDatabase=FirebaseDatabase.getInstance().getReference().child("Productos");
        mDatabase.child(code).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
              if(!task.isSuccessful()){
                  Log.e("firebase ","Error getting data",task.getException());
              }else{
                String name=String.valueOf(task.getResult().child("nombreProducto").getValue());
                  String stock=String.valueOf(task.getResult().child("stockProducto").getValue());
                  String costo=String.valueOf(task.getResult().child("precioCosto").getValue());
                  String venta=String.valueOf(task.getResult().child("precioVenta").getValue());
                  etNombreProducto.setText(name);
                  etStock.setText(stock);
                  etPrecioCosto.setText(costo);
                  etPrecioVenta.setText(venta);
                  Toast.makeText(getApplicationContext(),"Mis datos",Toast.LENGTH_LONG).show();
                  Log.d("firebase", "findUser");
              }
            }
        });
    }
    //Borramos un producto indicado mediante un codigo
    private void borrarProducto(String code){
        mDatabase=FirebaseDatabase.getInstance().getReference().child("Productos");
        mDatabase.child(code).removeValue();
        Toast.makeText(getApplicationContext(),"check the console",Toast.LENGTH_LONG).show();
    }

}